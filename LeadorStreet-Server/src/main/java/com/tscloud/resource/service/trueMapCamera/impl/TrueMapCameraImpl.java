package com.tscloud.resource.service.trueMapCamera.impl;



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tscloud.common.framework.Exception.ServiceException;
import com.tscloud.common.framework.mapper.BaseInterfaceMapper;
import com.tscloud.common.framework.service.impl.BaseInterfaceServiceImpl;
import com.tscloud.domain.resource.entity.trueMapCamera.MeasurePara;
import com.tscloud.domain.resource.entity.trueMapCamera.TrueMapCamera;
import com.tscloud.domain.resource.entity.trueMapStation.MemIndex;
import com.tscloud.domain.resource.entity.trueMapStation.NameOffset;
import com.tscloud.domain.resource.entity.trueMapStation.TrueMapStation;
import com.tscloud.domain.resource.service.trueMapCamera.ITrueMapCameraService;
import com.tscloud.resource.mapper.trueMapCamera.TrueMapCameraMapper;
import com.tscloud.resource.mapper.trueMapStation.TrueMapStationMapper;
import com.tscloud.resource.utils.CodeHelper;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbRandomAccessFile;

@Service 
public class TrueMapCameraImpl extends BaseInterfaceServiceImpl<TrueMapCamera> implements ITrueMapCameraService{
	// 影像索引信息map
	private Map<MemIndex, NameOffset> mapData = new TreeMap<MemIndex, NameOffset>();
	// 大文件名称map
	private Map<String, Short> mapFileIndex = new HashMap<String, Short>();
	// 大文件路径map
	private Map<Short, String> mapFileFullPath = new HashMap<Short, String>();

	public TrueMapCameraImpl() {
		InitData();
	}
    @Resource
	public TrueMapCameraMapper trueMapCameraMapper ;

    @Resource
	public TrueMapStationMapper trueMapStationMapper ;
	@Override
	public BaseInterfaceMapper<TrueMapCamera> getBaseInterfaceMapper() {
		return trueMapCameraMapper;
	}


	// 获取影像切片
	@Override
	public byte[] getImageTile(String imageId, int zoom, int col, int row) throws ServiceException {
		String[] arrT = imageId.split("-");
		MemIndex memIndex = new MemIndex();
		memIndex.vid = Integer.parseInt(arrT[0]);
		memIndex.camera = arrT[1].charAt(0);
		memIndex.time = Long.parseLong(arrT[2]);
		memIndex.level = (short) zoom;
		//Set<MemIndex> set  = mapData.keySet();
		/*for(MemIndex m :set){
			System.out.println(m.camera);
		}*/
		// 从影像索引信息中查询所在大文件及偏移地址
		NameOffset nameOffset = mapData.get(memIndex);
		if (nameOffset == null)
			return null;
		// 获取大文件路径
		String filePath = mapFileFullPath.get(nameOffset.fileIndex);
		if (filePath == null)
			return null;
		long nTileIndexOff = nameOffset.offSet + 4 + 12
				* (row * ((int) Math.pow(2.0, zoom)) + col);
		return GetImageData(filePath, nameOffset.offSet, nTileIndexOff);
	}

	// 初始化影像数据
	private void InitData() {
		try {
			/*
			 * String classPath = this.getClass().getClassLoader()
			 * .getResource("/").getPath(); classPath =
			 * java.net.URLDecoder.decode(classPath, "utf-8"); File file = new
			 * File(classPath + "/config.properties"); InputStream is = new
			 * FileInputStream(file);
			 */
			//读取配置文件config.properties中大文件索引路径和大文件目录；
			InputStream inputStream = this.getClass().getResourceAsStream(
					"/configs/config.properties");
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));
			Properties props = new Properties();
			props.load(bf);
			String indexFile = props.getProperty("indexFile");
			String imageDirs = props.getProperty("dataDirs");
			// 初始化大文件目录
			InitImageFiles(imageDirs);
			// 读取索引文件
			if (indexFile.indexOf("smb:") == -1) {
				//初始化索引文件
				InitImageIndex(indexFile);
			} else {
				System.setProperty("jcifs.smb.client.dfs.disabled", "true");
				//初始化网络索引文件
				InitSmbImageIndex(indexFile);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// 初始化大文件目录
	private void InitImageFiles(String imageDirs) {
		String[] dirs = imageDirs.split("&");
		short index = 0;
		for (int i = 0; i < dirs.length; ++i) {
			String dir = dirs[i];
			if (dir.indexOf("smb:") == -1) {
				File file = new File(dir);
				if (!file.isDirectory())
					continue;
				File[] tempList = file.listFiles();
				if (tempList == null)
					continue;
				for (int j = 0; j < tempList.length; ++j) {
					File f = tempList[j];
					if (f.isFile()) {
						mapFileFullPath.put(index, f.getPath());
						mapFileIndex.put(f.getName(), index++);
					}
				}
			} else {
				try {
					SmbFile file = new SmbFile(dir);
					if (!file.isDirectory())
						continue;
					SmbFile[] tempList = file.listFiles();
					if (tempList == null)
						continue;
					for (int j = 0; j < tempList.length; ++j) {
						SmbFile f = tempList[j];
						if (f.isFile()) {
							mapFileFullPath.put(index, f.getPath());
							mapFileIndex.put(f.getName(), index++);
						}
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	// 初始化索引文件
	private void InitImageIndex(String sIndexFile) {
		try {
			FileInputStream sfis = new FileInputStream(new File(sIndexFile));
			// int tt = sfis.available();
			BufferedInputStream bis = new BufferedInputStream(sfis);

			// int aa = bis.available();

			BufferedReader in = new BufferedReader(new InputStreamReader(bis,
					"utf-8"), 10 * 1024 * 1024);// 10M缓存

			while (in.ready()) {

				String line = in.readLine();
				// 获取单条索引记录
				GetOneIndexInfo(line);
			}
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// 初始化网络索引文件
	private void InitSmbImageIndex(String sIndexFile) {
		try {
			SmbFile file = new SmbFile(sIndexFile);
			if (!file.isFile())
				return;
			long len = file.length();
			SmbFileInputStream sfis = new SmbFileInputStream(file);
			int tt = sfis.available();
			int bufferLen = 10 * 1024 * 1024;
			byte[] buffer = new byte[bufferLen];
			int nRdLen = sfis.read(buffer);
			while (nRdLen > 0) {
				String temp = new String(buffer, "UTF-8");
				int nn = temp.lastIndexOf("\r\n");
				String[] arrStr = temp.split("\r|\n");
				int aa = arrStr.length;
				for (int i = 0; i < aa; ++i)
					GetOneIndexInfo(arrStr[i]);
				if (nRdLen < bufferLen)
					break;
				System.arraycopy(buffer, nn, buffer, 0, nRdLen - nn);
				for (int j = nRdLen - nn; j < nRdLen; ++j)
					buffer[j] = 0;
				nRdLen = sfis.read(buffer, nRdLen - nn, nn);
			}

			sfis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// 解析单条索引记录
	private void GetOneIndexInfo(String line) {
		if (line.length() < 27)
			return;
		String[] arrTmp = line.split(" ");
		if (arrTmp.length < 3 || arrTmp[0].length() != 27)
			return;
		String[] arrT = arrTmp[0].split("-");
		MemIndex memIndex = new MemIndex();
		memIndex.vid = Integer.parseInt(arrT[0]);
		memIndex.camera = arrT[1].charAt(0);
		memIndex.time = Long.parseLong(arrT[2]);

		String[] arrF = arrTmp[1].split("_");
		memIndex.level = Short.parseShort(arrF[arrF.length - 2]);

		if (mapFileIndex.containsKey(arrTmp[1])) {
			NameOffset nameOffset = new NameOffset();
			nameOffset.fileIndex = mapFileIndex.get(arrTmp[1]);
			nameOffset.offSet = Long.parseLong(arrTmp[2]);
			mapData.put(memIndex, nameOffset);
			
		}
	}

	//获取影像切哦数据
	private byte[] GetImageData(String filePath, long nOffSet,
			long nTileIndexOff) {
		// 获取切片信息
		if (filePath.indexOf("smb:") == -1)
			//获取本地影像数据
			return GetLocalImageData(filePath, nOffSet, nTileIndexOff);
		else
			//获取网络影像数据
			return GetSmbImageData(filePath, nOffSet, nTileIndexOff);
	}

	//获取本地影像数据
	private byte[] GetLocalImageData(String filePath, long nOffSet,
			long nTileIndexOff) {
		// 获取切片信息
		try {
			RandomAccessFile rand = new RandomAccessFile(new File(filePath),
					"r");
			// 获取切片数据流大小
			byte[] buf1 = new byte[4];
			rand.seek(nTileIndexOff + 8);
			int rSize = rand.read(buf1);
			int imgSize = CodeHelper.BytesToInt(buf1, 0);
			if (imgSize > 2 * 1024 * 1024 || imgSize < 10) {
				rand.close();
				return null;
			}
			// 获取切片偏移地址
			byte[] buf2 = new byte[8];
			rand.seek(nTileIndexOff);
			rSize = rand.read(buf2);
			long nTileOff = CodeHelper.BytesToLong(buf2, 0);
			// 获取切片数据流
			byte[] buf3 = new byte[imgSize];
			rand.seek(nTileOff + nOffSet);
			rSize = rand.read(buf3);
			rand.close();
			return buf3;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	//获取网络影像数据
	private byte[] GetSmbImageData(String filePath, long nOffSet,
			long nTileIndexOff) {
		// 获取切片信息
		try {
			SmbRandomAccessFile rand = new SmbRandomAccessFile(new SmbFile(
					filePath), "r");
			// 获取切片数据流大小
			byte[] buf1 = new byte[4];
			rand.seek(nTileIndexOff + 8);
			int rSize = rand.read(buf1);
			int imgSize = CodeHelper.BytesToInt(buf1, 0);
			if (imgSize > 2 * 1024 * 1024 || imgSize < 10) {
				rand.close();
				return null;
			}
			// 获取切片偏移地址
			byte[] buf2 = new byte[8];
			rand.seek(nTileIndexOff);
			rSize = rand.read(buf2);
			long nTileOff = CodeHelper.BytesToLong(buf2, 0);
			// 获取切片数据流
			byte[] buf3 = new byte[imgSize];
			rand.seek(nTileOff + nOffSet);
			rSize = rand.read(buf3);
			rand.close();
			return buf3;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public String queryMatchImage(String vid,String cameraNo,String dateFormat,String stationGuid)
			throws ServiceException {
		String result = "" ;
		try{
			Map<String, Object> map = new HashMap<String,Object>() ;
			map.put("vid", vid) ;
			map.put("cameraNo", cameraNo) ;
			map.put("dateFormat", dateFormat.substring(0,14)) ;
			TrueMapCamera trueMapCamera = trueMapCameraMapper.SearchCameraByMap(map) ;
			if(trueMapCamera != null){
				String newCameraNo = "" ;
				String pair = trueMapCamera.getPair() ;
				String[] pairArr = pair.split("-") ;
				//根据"-"比较，if这几个判断没有搞懂原因
				String pair1 = pairArr[0];
				String pair2 = pairArr[1];
				if(pair1.compareTo(cameraNo) == 0) newCameraNo = pair2;
				else newCameraNo = pair1;
				if(newCameraNo.compareTo(cameraNo) == 0){
					map.put("stationID", stationGuid) ;
					List<TrueMapStation> list = trueMapStationMapper.getLastGuidByMap(map) ;
					if(list != null && list.size() > 0){
						String guid = list.get(0).getGuid() ;
						//拆分guid，然后在重组返回结果级
						String[] returnArr = guid.split("-") ;
						String returnVID = returnArr[0] ;
						String time = returnArr[2] ;
						result =returnVID+"-"+newCameraNo+"-"+time ;
					}
				}else{
					//返回影像id
					result =vid+"-"+newCameraNo+"-"+dateFormat ;
				}
			}
		}catch(Exception e){
			throw new ServiceException(e.getMessage(), e);
		}
		return result;
	}


	@Override
	public List<Map<String, Object>> pixel2Coord( String coortype, String prjtype,List<MeasurePara> list ) throws ServiceException {
			//取出guid
		MeasurePara leftMeasurePara = list.get(0);
		MeasurePara rightMeasurePara = list.get(1);
		//获取相机位置、参数以及车的位置   获取相应的测站(左边)
		Map<String,Object> leftReturnMap = GetParamByImageName(leftMeasurePara);
		TrueMapStation leftTrueMapStation = (TrueMapStation)leftReturnMap.get("trueMapStation");
		TrueMapCamera leftTrueMapCamera = (TrueMapCamera)leftReturnMap.get("trueMapCamera");
		
		//获取相机位置、参数以及车的位置   获取相应的测站(右边)
		Map<String,Object> rightReturnMap = GetParamByImageName(rightMeasurePara);
		TrueMapStation rightTrueMapStation = (TrueMapStation)rightReturnMap.get("trueMapStation");
		TrueMapCamera rightTrueMapCamera = (TrueMapCamera)rightReturnMap.get("trueMapCamera");
		ImageXYZ2MapXYZ(leftMeasurePara,rightMeasurePara,leftTrueMapStation,leftTrueMapCamera,rightTrueMapStation,rightTrueMapCamera,coortype,prjtype) ;
		return null;
	}
	private void ImageXYZ2MapXYZ(MeasurePara leftMeasurePara, MeasurePara rightMeasurePara,
			TrueMapStation leftTrueMapStation, TrueMapCamera leftTrueMapCamera, TrueMapStation rightTrueMapStation,
			TrueMapCamera rightTrueMapCamera, String coortype, String prjtype) {
		
	}

	private Map<String,Object> GetParamByImageName(MeasurePara measurePara) {
		//拆分后组装成查询条件
		measurePara = parseImageId(measurePara) ;
		TrueMapStation trueMapStation = trueMapStationMapper.getByStationID(measurePara.getZeroImageId());
		Map<String,Object> pamams = new HashMap<String,Object>() ;
		pamams.put("vid", measurePara.getVid()) ;
		pamams.put("dateFormat", measurePara.getDateFormat().substring(0,14)) ;
		pamams.put("cameraNo",measurePara.getCameraNo()) ;
		//查询所有参数
		TrueMapCamera trueMapCamera = trueMapCameraMapper.SearchCameraByMap(pamams);
		//
		Map<String,Object> returnMap = new HashMap<String,Object>() ;
		//防止类型转换失败
		if(trueMapStation == null ){
			trueMapStation = new TrueMapStation() ;
		}
		if(trueMapCamera == null ){
			trueMapCamera = new TrueMapCamera() ;
		}
		returnMap.put("trueMapCamera", trueMapCamera) ;
		returnMap.put("trueMapStation", trueMapStation) ;
		return returnMap;
	}
	
	/**
	 * 
	 * 功能描述：拆分后组装成查询条件
	 *
	 * @author 何佳杰
	 * 创建日期 ：2019年12月16日 上午9:58:10
	 *
	 * @param measurePara
	 * @return
	 *
	 * 修改历史 ：(修改人，修改时间，修改原因/内容)
	 */
	private MeasurePara parseImageId(MeasurePara measurePara){
		String imageId = measurePara.getImageid() ;
		String[] ss = imageId.split("-") ;
		if(ss.length == 3){
			String vid =ss[0] ;
			String cameraNo =ss[1] ;
			String dateFormat =ss[2] ;
			String stationGuid = vid+"-0-"+dateFormat ;
			//组装查询条件查询sql
			measurePara.setZeroImageId(stationGuid);
			measurePara.setVid(vid);
			measurePara.setCameraNo(cameraNo);
			measurePara.setDateFormat(dateFormat);
		}
		return measurePara;
	}


	@Override
	public String getImageSize(String vid, String cameraNo, String dateFormat) throws ServiceException {
		Map<String, Object> map = new HashMap<String,Object>() ;
		String size = "" ;
		try{
		map.put("vid", vid) ;
		map.put("cameraNo", cameraNo) ;
		map.put("dateFormat", dateFormat.substring(0,6)) ;
		TrueMapCamera trueMapCamera = trueMapCameraMapper.SearchCameraByMap(map) ;
		size= trueMapCamera.getPixel() ;
		}catch(Exception e){
			throw new ServiceException(e.getMessage(), e);
		}
		return size;
	}
}
