package com.tscloud.portal.utils;


import com.tscloud.common.framework.config.ConfigHelper;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StreetXmlConfig {
    private static final Logger log = LoggerFactory.getLogger(StreetXmlConfig.class);
    /**
     *修改Street中的配置文件中TrueVisionServerUrl属性的值
     */
    public static void updateServerUrl(){
        String  webapp=ConfigHelper.getJettyParameter("server.resource");
        String confingPath=ConfigHelper.getValue("street.config.file.path");
        String serverurl=ConfigHelper.getValue("street.true.vision.server.url");

        Document read=null;
        SAXReader reader=new SAXReader();
        XMLWriter writer=null;
        try {
            String  path=webapp+confingPath;
            log.info("街景配置文件路径为{}",path);
            File file = new File(path);
            read = reader.read(file);
            //获取根目录
            Element rootElement = read.getRootElement();
            //获取指定结点
            Element node=(Element)read.selectSingleNode("root/TrueVisionData/TrueVisionServerUrl");
            //修改结点数据
            node.setText(serverurl.trim());

            //格式化为缩进格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            writer = new XMLWriter(new FileWriter(path),format);
            writer.write(read);
            writer.flush();
            log.info("街景文件修改完成");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            if (writer!=null)
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}
