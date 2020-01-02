//package com.tscloud.resource.utils;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
////import com.tscloud.domain.resource.entity.GisInfo;
////import com.tscloud.resource.utils.analyzer.ObjectUtil;
////import com.tscloud.resource.utils.analyzer.StringUtil;
//
//import javax.xml.stream.XMLInputFactory;
//import javax.xml.stream.XMLStreamConstants;
//import javax.xml.stream.XMLStreamReader;
//import java.io.*;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.*;
//
///**
// * Created by Administrator on 2015/4/16.
// */
//public class ServiceAnalyzer {
//
//    private HttpURLConnection connectHttp(String url) throws IOException, MalformedURLException {
//        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
//        // 设置请求时间
//        conn.setConnectTimeout(5000);
//        conn.connect();
//        return conn;
//    }
//
//    private Boolean isJSON(String url) {
//        Boolean isXML = false;
//        try {
//            HttpURLConnection conn = connectHttp(url);
//            isXML = 200 == conn.getResponseCode();
//            conn.disconnect();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return isXML;
//    }
//
//    private Boolean isXML(String url, String root) {
//        Boolean isXML = false;
//        String line = null, temp = null;
//        try {
//            HttpURLConnection conn = connectHttp(url);
//            int code = conn.getResponseCode();
//            if (code == 200) {
//                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                while ((temp = in.readLine()) != null) {
//                    if (!StringUtil.empty(temp))
//                        line = temp.toLowerCase();
//                }
//                isXML = StringUtil.contains(line, root.toLowerCase());
//                in.close();
//            }
//            conn.disconnect();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return isXML;
//    }
//
//    /**
//     * 方法用途： 验证资源服务地址<br>
//     * 人员：崔文科<br>
//     * 时间：2015-4-16<br>
//     * 操作：创建<br>
//     * 操作 内容：创建方法
//     * <p/>
//     * 参数说明：
//     *
//     * @param serviceURL =服务地址
//     * @param serType    =服务类型
//     * @return
//     * @throws Throwable
//     */
//    public String[] validateLinkage(String serviceURL, String serType) throws Throwable {
//        // 总的配置资源文件
//        String propertiesFileUrl = "/analyzer/" + serType + "/" + serType + ".properties";
//        // 获取总的配置资源文件
//        Properties properties = ObjectUtil.getPropertiesInfo(propertiesFileUrl);
//        // 获取服务支持的类型
//        String serviceType = properties.getProperty("servicetype");
//        // 获取返回类型
//        String backType = properties.getProperty("backtype");
//        // 获取服务版本
//        String version = properties.getProperty("serviceTypeVersion");
//        switch (serviceType.charAt(0)) {
//            // 如果服务为OGC标准服务
//            case 'O':
//                String service = StringUtil.contains(serType, "WFS") ? "WFS" : serType;
//                serviceURL += "?SERVICE=" + service + "&REQUEST=GetCapabilities";
//                String[] versions = version.split(",");
//                // 版本,异常
//                for (String vs : versions) {
//                    if (StringUtil.equals(backType, "document")) {
//                        Properties pr = checkServiceCapabilities(serType, vs);
//                        if (isXML(serviceURL + "&version=" + vs, pr.getProperty("rootName"))) {
//                            version = vs;
//                            serviceURL += "&version=" + vs;
//                            break;
//                        } else {
//                            return new String[]{serType, backType};
//                        }
//                    } else if (StringUtil.equals(backType, "json")) {
//                        if (isJSON(serviceURL + "&version=" + vs)) {
//                            version = vs;
//                            serviceURL += "&version=" + vs;
//                            break;
//                        } else {
//                            return new String[]{serType, backType};
//                        }
//                    }
//                }
//                break;
//            // 如果服务为瓦片服务
//            case 'T':
//                serviceURL += "/GetCapabilities";
//                break;
//            // 如果返回数据类型为JSON的服务
//            case 'J':
//                serviceURL += "?f=json&pretty=true";
//                break;
//            // 如果返回数据类型为吉奥的服务
//            case 'G':
//                serviceURL += "?REQUEST=GetCapabilities";
//                break;
//            // 如果返回数据类型为ArcGIS服务
//            case 'A':
//                serviceURL += "?f=pjson";
//                break;
//        }
//        return new String[]{serType, version, serviceURL, backType};
//    }
//
//    public static GisInfo getParse(String serviceURL, String serType) throws Throwable {
//        ServiceAnalyzer analyzer = new ServiceAnalyzer();
//        GisInfo metadata = null;
//        if (StringUtil.equals("OTHER", serType)) {
//            metadata = new GisInfo();
//            metadata.setOriginalLinkage(serviceURL);
//        } else {
//            String[] analyzerv = analyzer.validateLinkage(serviceURL, serType);
//            if (analyzerv.length == 4) {
//                if (StringUtil.contains("json", analyzerv[3])) {
//                    metadata = analyzer.getParseJSON(analyzerv);
//                }
//                if (StringUtil.contains("text", analyzerv[3])) {
//                    metadata = analyzer.getParseText(analyzerv);
//                }
//                if (StringUtil.contains("document", analyzerv[3])) {
//                    metadata = analyzer.getParseXML(analyzerv);
//                }
//            }
//        }
//        return metadata;
//    }
//
//    // 获取功能点
//    public GisInfo getParseText(String[] result) throws Throwable {
//        GisInfo metadata = new GisInfo();
//        Properties res = checkServiceCapabilities(result[0], result[1]);
//        for (Object fieldName : res.keySet()) {
//            setValue(metadata, getFieldByName(metadata.getClass(), fieldName.toString()), res.getProperty(fieldName.toString()));
//        }
//        return metadata;
//    }
//
//    public GisInfo getParseJSON(String[] result) throws Throwable {
//        GisInfo metadata = new GisInfo();
//        try{
//            metadata.setSerVersion(result[1]);
//            InputStream input = new URL(result[2]).openStream();
//            StringBuffer buff = new StringBuffer();
//            BufferedReader response = new BufferedReader(new InputStreamReader(new BufferedInputStream(input), "UTF-8"));
//            String line = null;
//            while (null != (line = response.readLine())) {
//                buff.append(line);
//            }
//            JSONObject json = JSON.parseObject(buff.toString());
//            Properties res = checkServiceCapabilities(result[0], result[1]);
//            for (Object fieldName : res.keySet()) {
//                setValue(metadata, getFieldByName(metadata.getClass(), fieldName.toString()), StringUtil.getNode(res.getProperty(fieldName.toString()), json).toString());
//            }
//        }catch (Exception e){
//            metadata.setApplyStatus("false");
//        }
//        return metadata;
//    }
//
//    private Field getFieldByName(Class<?> classz, String name) {
//        Field f = null;
//        for (Field field : classz.getDeclaredFields()) {
//            if (StringUtil.equals(name, field.getName())) {
//                f = field;
//                break;
//            }
//        }
//        return f;
//    }
//
//    private void setValue(Object object, Field field, Object val) throws Exception {
//        String methodName = "set" + StringUtil.upperFirst(field.getName());
//        if (ObjectUtil.containsMethod(object.getClass(), methodName)) {
//            object.getClass().getMethod(methodName, field.getType()).invoke(object, val);
//        }
//    }
//
//    /**
//     * 方法用途： info<br>
//     * 人员：崔文科<br>
//     * 时间：2015-4-16<br>
//     * 操作：创建<br>
//     * 操作 内容：创建方法
//     * <p/>
//     * 参数说明：
//     *
//     * @param result =服务地址验证结果
//     * @return
//     * @throws Throwable
//     */
//    public GisInfo getParseXML(String[] result) throws Throwable {
//        // 声明节点路径同内容的键值对
//        Map<String, StringBuilder> map = new HashMap<String, StringBuilder>();
//        Properties res = checkServiceCapabilities(result[0], result[1]);
//
//        // 读取文件流
//        InputStream input = new URL(result[2]).openStream();
//        XMLStreamReader xsr = XMLInputFactory.newInstance().createXMLStreamReader(input);
//
//        StringBuilder sb = new StringBuilder();
//        try {
//            String sname = null;
//            String ename = null;
//            String nname = null;
//            while (xsr.hasNext()) {
//                switch (xsr.next()) {
//                    case XMLStreamConstants.START_ELEMENT:
//                        sname = xsr.getLocalName();
//                        nname = "/" + sname;
//                        sb.append(nname);
//                        String key = sb.toString();
//                        for (int i = 0, n = xsr.getAttributeCount(); i < n; ++i) {
//                            String attributeName = "/@" + xsr.getAttributeName(i);
//                            key = sb + attributeName;
//                            if (res.containsValue(key)) {
//                                sb.append(attributeName);
//                                analyzerIterance(map, sb);
//                                if (map.containsKey(sb.toString())) {
//                                    map.get(sb.toString()).append("&" + xsr.getAttributeValue(i));
//                                } else {
//                                    map.put(sb.toString(), new StringBuilder(xsr.getAttributeValue(i)));
//                                }
//                                sb.delete(sb.lastIndexOf(attributeName), sb.length());
//                            }
//                        }
//                        key = sb.substring(0, sb.lastIndexOf("/")) + "/$";
//                        if (res.containsValue(key)) {
//                            if (map.containsKey(key)) {
//                                map.get(key).append("&" + sname);
//                            } else {
//                                map.put(key, new StringBuilder(sname));
//                            }
//                            sb.delete(sb.lastIndexOf(sname), sb.length());
//                            sb.append("$");
//                        }
//                        key = sb + "/#";
//                        if (res.containsValue(key)) {
//                            if (map.containsKey(key)) {
//                                map.get(key).append("&" + sname);
//                            } else {
//                                map.put(key, new StringBuilder(sname));
//                            }
//                        }
//                        break;
//                    case XMLStreamConstants.CHARACTERS:
//                        if (!xsr.isWhiteSpace()) {
//                            // 整理出节点路径
//                            if (res.containsValue(sb.toString())) {
//                                analyzerIterance(map, sb);
//                                if (map.containsKey(sb.toString())) {
//                                    map.get(sb.toString()).append(sname.equals(ename) ? "," : "&").append(xsr.getText());
//                                } else {
//                                    map.put(sb.toString(), new StringBuilder(xsr.getText()));
//                                }
//                            }
//                        }
//                        break;
//                    case XMLStreamConstants.END_ELEMENT:
//                        ename = xsr.getLocalName();
//                        nname = "/" + ename;
//                        if (sb.lastIndexOf("$") + 1 == sb.length())
//                            sb.delete(sb.lastIndexOf("/$"), sb.length());
//                        if (sb.lastIndexOf(nname) > 0 ? (sb.lastIndexOf(nname) + nname.length()) == sb.length() : false)
//                            sb.delete(sb.lastIndexOf(nname), sb.length());
//                        break;
//                }
//            }
//        } finally {
//            input.close();
//            xsr.close();
//        }
//        return parseServiceDoc(map, res);
//    }
//
//    private void analyzerIterance(Map<String, StringBuilder> map, StringBuilder sb) {
//        String nname = sb.substring(0, sb.lastIndexOf("/"));
//        if (null == map.get(nname + "/#")) {
//            nname = nname.substring(0, nname.lastIndexOf("/"));
//        }
//        if (null != map.get(nname + "/#")) {
//            int e = map.get(nname + "/#").toString().split("&").length;
//            if (e > 1) {
//                if (!map.containsKey(sb.toString())) {
//                    map.put(sb.toString(), new StringBuilder());
//                }
//                int s = map.get(sb.toString()).toString().split("&").length;
//                for (int j = s + 1; j < e; j++) {
//                    map.get(sb.toString()).append("&");
//                }
//            }
//        }
//    }
//
//    /**
//     * 方法用途： 根据服务类型和版本获取资源配置文件 <br>
//     * 人员：崔文科<br>
//     * 时间：2015-4-16<br>
//     * 操作：创建<br>
//     * 操作 内容：创建方法<br>
//     * <p/>
//     * 参数说明：
//     *
//     * @param method  =服务类型
//     * @param version =版本号
//     */
//    private Properties checkServiceCapabilities(String method, String version) throws Throwable {
//        // 总的配置资源文件
//        String propertiesFileUrl = "/analyzer/" + method + "/" + method;
//        // 获取总的配置资源文件
//        Properties properties = ObjectUtil.getPropertiesInfo(propertiesFileUrl + ".properties");
//        if (StringUtil.empty(version)) {
//            version = properties.getProperty("serviceTypeVersion");
//        }
//        propertiesFileUrl = "/analyzer/" + method + "/" + method;
//        // 获取总的配置资源文件
//        properties = ObjectUtil.getPropertiesInfo(propertiesFileUrl + version + ".properties");
//        return properties;
//    }
//
//    /**
//     * 方法用途： 解析出来的内容封装到实体类里面 <br>
//     * 人员：崔文科<br>
//     * 时间：2015-4-16<br>
//     * 操作：创建<br>
//     * 操作 内容：创建方法<br>
//     * <p/>
//     * 参数说明：
//     *
//     * @param map    =键值集合
//     * @param pres   =资源描述
//     * @param =返回的对象
//     */
//    private GisInfo parseServiceDoc(Map<String, StringBuilder> map, Properties pres) {
//        GisInfo object = new GisInfo();
//        for (Object fname : pres.keySet()) {
//            for (Field field : object.getClass().getDeclaredFields()) {
//                if (null != map.get(pres.get(fname)) && (StringUtil.contains(fname.toString(), field.getName() + "*") || StringUtil.equals(fname.toString(), field.getName()))) {
//                    try {
//                        Method method = object.getClass().getMethod("set" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1), field.getType());
//                        String fieldValue = map.get(pres.get(fname)).toString();
//                        if (!StringUtil.empty(fieldValue)) {
//                            // 如果是字段名坐标点
//                            Boolean contains = false;
//                            contains = contains || StringUtil.contains(fname.toString(), "westBL");
//                            contains = contains || StringUtil.contains(fname.toString(), "southBL");
//                            contains = contains || StringUtil.contains(fname.toString(), "eastBL");
//                            contains = contains || StringUtil.contains(fname.toString(), "northBL");
//                            if (contains) {
//                                fieldValue = shapeInfo(fname, fieldValue);
//                            }
//                            Object valueObj = typeConvert(field.getType().getSimpleName(), fieldValue);
//                            method.invoke(object, valueObj);
//                        }
//                    } catch (Exception e) {
//                        continue;
//                    }
//                }
//            }
//        }
//        Map<String, List<String>> listMap = new HashMap<String, List<String>>();
//        for (Object fname : pres.keySet()) {
//            int index = fname.toString().lastIndexOf(".");
//            if (index > 0) {
//                String beanName = fname.toString().substring(0, index);
//                String beanFiel = fname.toString().substring(index + 1);
//                if (listMap.containsKey(beanName)) {
//                    listMap.get(beanName).add(beanFiel);
//                } else {
//                    List<String> list = new ArrayList<String>();
//                    list.add(beanFiel);
//                    listMap.put(beanName, list);
//                }
//            }
//        }
//        return object;
//    }
//
//    private String shapeInfo(Object fname, String fieldValue) {
//        String[] strs = fieldValue.split("&");
//        // 定义最终值
//        Double lv = 0.0;
//        for (String v : strs) {
//            switch (fname.toString().charAt(0)) {
//                case 'w':
//                    // 最小值
//                    v = (2 == v.split(",").length) ? v.split(",")[0].toString().trim() : v.trim();
//                    v = (2 == v.split(" ").length) ? v.split(" ")[0].toString().trim() : v.trim();
//                    lv = lv == 0.0 ? Double.parseDouble(v) : (lv > Double.parseDouble(v)) ? Double.parseDouble(v) : lv;
//                    break;
//                case 's':
//                    // 最小值
//                    v = (2 == v.split(",").length) ? v.split(",")[0].toString().trim() : v.trim();
//                    v = (2 == v.split(" ").length) ? v.split(" ")[1].toString().trim() : v.trim();
//                    lv = lv == 0.0 ? Double.parseDouble(v) : (lv > Double.parseDouble(v)) ? Double.parseDouble(v) : lv;
//                    break;
//                case 'e':
//                    // 最大值
//                    v = (2 == v.split(",").length) ? v.split(",")[1].toString().trim() : v.trim();
//                    v = (2 == v.split(" ").length) ? v.split(" ")[0].toString().trim() : v.trim();
//                    lv = lv == 0.0 ? Double.parseDouble(v) : (lv < Double.parseDouble(v)) ? Double.parseDouble(v) : lv;
//                    break;
//                case 'n':
//                    // 最大值
//                    v = (2 == v.split(",").length) ? v.split(",")[1].toString().trim() : v.trim();
//                    v = (2 == v.split(" ").length) ? v.split(" ")[1].toString().trim() : v.trim();
//                    lv = lv == 0.0 ? Double.parseDouble(v) : (lv < Double.parseDouble(v)) ? Double.parseDouble(v) : lv;
//                    break;
//            }
//        }
//        return lv.toString();
//    }
//
//    /**
//     * 方法用途： 分析实体类的属性值 <br>
//     * 人员：崔文科<br>
//     * 时间：2015-4-16<br>
//     * 操作：创建<br>
//     * 操作 内容：创建方法<br>
//     * <p/>
//     * 参数说明：
//     *
//     * @param className =类名
//     * @param value     =属性值
//     */
//    private Object typeConvert(String className, String value) {
//        if (className.equals("String")) {
//            return value;
//        } else if (className.equals("Integer") || className.equals("int")) {
//            return Integer.valueOf(value);
//        } else if (className.equals("long") || className.equals("Long")) {
//            return Long.valueOf(value);
//        } else if (className.equals("boolean") || className.equals("Boolean")) {
//            return Boolean.valueOf(value);
//        } else if (className.equals("float") || className.equals("Float")) {
//            return Float.valueOf(value);
//        } else if (className.equals("double") || className.equals("Double")) {
//            return Double.valueOf(value);
//        } else {
//            return null;
//        }
//    }
//}
