package com.tscloud.resource.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


public class CodeHelper {

	// Byte[]转换Int
	public static int BytesToInt(byte[] arrByte, int nStart) {
		if (arrByte.length < nStart + 4)
			return 0;
		return arrByte[0] & 0xFF | (arrByte[1] & 0xFF) << 8
				| (arrByte[2] & 0xFF) << 16 | (arrByte[3] & 0xFF) << 24;
	}

	// Int转换Byte[]
	public static boolean PutInt(byte[] arrByte, int value, int nStart) {
		if (arrByte.length < nStart + 4)
			return false;
		for (int i = 0; i < 4; i++) {
			arrByte[i + nStart] = (byte) (value >> 8 * i & 0xFF);
		}
		return true;
	}

	// Byte[]转换long
	public static long BytesToLong(byte[] arrByte, int nStart) {
		if (arrByte.length < nStart + 8)
			return 0;
		long l;
		l = arrByte[nStart];
		l &= 0xff;
		l |= ((long) arrByte[nStart + 1] << 8);
		l &= 0xffff;
		l |= ((long) arrByte[nStart + 2] << 16);
		l &= 0xffffff;
		l |= ((long) arrByte[nStart + 3] << 24);
		l &= 0xffffffffl;
		l |= ((long) arrByte[nStart + 4] << 32);
		l &= 0xffffffffffl;
		l |= ((long) arrByte[nStart + 5] << 40);
		l &= 0xffffffffffffl;
		l |= ((long) arrByte[nStart + 6] << 48);
		l &= 0xffffffffffffffl;
		l |= ((long) arrByte[nStart + 7] << 56);

		return l;
	}

	// long转换byte[]
	public static boolean PutLong(byte[] arrByte, long value, int nStart) {
		if (arrByte.length < nStart + 8)
			return false;
		long val = value;
		for (int i = 0; i < 8; i++) {
			arrByte[i + nStart] = new Long(val).byteValue();
			val = val >> 8;
		}
		return true;
	}

	// Byte[]转换double
	public static double BytesToDouble(byte[] arrByte, int nStart) {
		if (arrByte.length < nStart + 8)
			return 0.0;
		long l = BytesToLong(arrByte, nStart);
		return Double.longBitsToDouble(l);
	}

	public static boolean PutDouble(byte[] arrByte, double x, int nStart) {
		if (arrByte.length < nStart + 8)
			return false;
		long val = Double.doubleToLongBits(x);
		for (int i = 0; i < 8; i++) {
			arrByte[nStart + i] = new Long(val).byteValue();
			val = val >> 8;
		}
		return true;
	}
	
	// 解析标注扩展属性
	public static Map<String, String> ParseAttribute(String attribute) {
		Map<String, String> mapAttr = new HashMap<String, String>();
		String[] arrTemp = attribute.split(";|,");
		for (int i = 0; i < arrTemp.length - 1; ++i) {
			String sKey = arrTemp[i];
			String sValue = arrTemp[++i];
			mapAttr.put(sKey, sValue);
		}
		return mapAttr;
	}

	// 解析坐标串
	public static byte[] ParseShape(String shape){
		if (shape == null)
			return null;
		String[] arrTemp = shape.split("\\|");
		if (arrTemp.length < 3)
			return null;
		int nPt = (int)(arrTemp.length / 3);
		int len = 4 + 24 * nPt;
		byte[] arrByte = new byte[len];
		int nPos = 0;
		PutInt(arrByte,nPt,nPos);
		nPos += 4;
		int index = 0;
		for (int i = 0; i < nPt*3; i++) {
			PutDouble(arrByte,Double.parseDouble(arrTemp[index++]),nPos);
			nPos += 8;
	    }
		return arrByte;
	}
	
	// 解析相邻测站信息
	public static List<NearItem> ParseNearItems(String sExtend) {
		List<NearItem> items = new ArrayList<NearItem>();
		String[] arrStr = StringUtils.split(sExtend, "{|}");
		// String[] arrStr = sExtend.split("{");
		for (int i = 0; i < arrStr.length; ++i) {
			String temp = arrStr[i];
			if (temp.length() > 0) {
				String[] arrTmp = temp.split(",");
				if (arrTmp.length == 6) {
					NearItem item = new NearItem();
					item.setGuid(arrTmp[0]);
					item.setRoadName(arrTmp[4]);
					item.setX(Double.parseDouble(arrTmp[1]));
					item.setY(Double.parseDouble(arrTmp[2]));
					item.setYaw(Double.parseDouble(arrTmp[5]) * 0.001);
					items.add(item);
				}
			}
		}
		return items;
	}
}
