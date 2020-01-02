package com.tscloud.portal.utils;

import com.alibaba.fastjson.JSON;
import com.tscloud.common.framework.rest.view.JsonViewObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * Author: LiuWangChenG
 * Description: FTP文件上传servlet
 * Date: 2015/4/3
 * Mail: liuwangcheng@leador.com.cn
 */
@WebServlet(name = "TempDirUploadHandleServlet")
public class TempDirUploadHandleServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(TempDirUploadHandleServlet.class);

    private static final String SPLIT_FLAG = "$_@@_$";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //上传时生成的临时文件保存目录
        String realName = "";
        String fileName = "";
        String fileExtName = "";

        //临时文件存储路径
        String filePath = TempDirUploadHandleServlet.class.getResource("").getPath();
        if (filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator + "temp";
        } else {
            filePath = filePath + "temp";
        }

        File tmpFile = new File(filePath);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }

        //消息提示
        String msg = "写入临时文件失败.";

        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
        //设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
        factory.setSizeThreshold(1024 * 100);
        factory.setRepository(tmpFile);

        //文件上传解析器,监听文件上传进度
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setProgressListener(new ProgressListener() {
            public void update(long pBytesRead, long pContentLength, int arg2) {
                log.info("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
            }
        });

        upload.setHeaderEncoding("UTF-8");
        //单个文件的大小的最大值,单位为字节
        upload.setFileSizeMax(1024 * 1024 * 500);
        //文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为500MB
        upload.setSizeMax(1024 * 1024 * 500);
        try {
            InputStream in = null;
            FileOutputStream out = null;

            //文件上传处理标识
            boolean flag = true;

            //使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                if (!item.isFormField()) {
                    fileName = item.getName();

                    if (fileName == null || fileName.trim().equals("")) {
                        continue;
                    }

                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);

                    realName = makeFileName() + "." + fileExtName;
                    filePath += File.separator + realName;

                    try {
                        in = item.getInputStream();
                        out = new FileOutputStream(filePath);

                        int len = 0;
                        byte[] buffer = new byte[1024];

                        while ((len = in.read(buffer)) > 0) {
                            out.write(buffer, 0, len);
                        }

                        out.flush();
                    } catch (IOException e) {
                        flag = false;
                        msg += fileName + ",";
                        log.error("Read file to tmpFile fail.", e);
                    } finally {
                        if (out != null) {
                            try {
                                out.close();
                            } catch (IOException e) {
                                //ignore
                            }
                        }

                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException e) {
                                //ignore
                            }
                        }
                    }
                }
            }
            msg = flag ? "文件上传成功！" : msg;
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            msg = "单个文件超出最大值.";
            log.error(msg, e);
        } catch (FileUploadBase.SizeLimitExceededException e) {
            msg = "上传文件的总的大小超出限制的最大值.";
            log.error(msg, e);
        } catch (Exception e) {
            msg = "文件上传失败！";
            log.error(msg, e);
        }

        JsonViewObject jsonView = new JsonViewObject();
        jsonView.successPack(fileName + SPLIT_FLAG + filePath + SPLIT_FLAG + realName + SPLIT_FLAG + fileExtName);

        jsonView.setMessage(msg);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        PrintWriter outs = response.getWriter();
        outs.print(JSON.toJSONString(jsonView));
        outs.flush();
        outs.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    /**
     * @Method: makeFileName
     * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
     * @return uuid+"_"+文件的原始名称
     */
    private String makeFileName(){  //2.jpg
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名,防止中文
        return UUID.randomUUID().toString() + "_uploadfile" ;
    }
}
