package com.tscloud.resource.utils;


import com.tscloud.common.framework.Constants;
import com.tscloud.common.utils.FileUtil;
import com.tscloud.domain.resource.entity.AttrEdit;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *操作表的属性字段
 */
public class OperateDBUtil {
    //系统参数配置
    public static Properties jdbcProperties = loadProperties();

    public static boolean getResult(AttrEdit attr){
        boolean result = false;
        String sql = "";
        Connection conn = null;
        String operate = attr.getOperate();
        if (operate.equals("add")) {
            sql = addCol(attr);

        } else if (operate.equals("modify")) {
            sql = modifyCol(attr);

        } else if (operate.equals("remove")) {
            sql = removeCol(attr);

        }
        try{
            conn = getConn();
            Statement stmt = conn.createStatement();
            int res  = stmt.executeUpdate(sql);
            if(res != -1){
                result = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null!=conn){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 获得connection
     * @return
     * @throws Exception
     */
    private static Connection getConn() throws Exception{
        Connection conn = null;
        String user =getJDBCValue("jdbc.username");
        String passwd = getJDBCValue("jdbc.password");
        String url = getJDBCValue("jdbc.url")+"&user="+user+"&password="+passwd;
        Class.forName(getJDBCValue("jdbc.driverClassName"));
        conn = DriverManager.getConnection(url);
        return conn;
    }

    /**
     * 删除表字段sql
     * @param attr
     * @return
     */
    private static String removeCol(AttrEdit attr){
        String sql = "alter table " + attr.getTableName() + " drop column " + attr.getColName();
        return sql;
    }

    /**
     * 修改表字段sql
     * @param attr
     * @return
     */
    private static String modifyCol(AttrEdit attr){
        String sql = "alter table " + attr.getTableName() + " change column "
                + attr.getColName() + " " + attr.getNewColName() + " " + attr.getColType();
        return sql;
    }

    /**
     * 添加表字段sql
     * @param attr
     * @return
     */
    private static String addCol(AttrEdit attr){
        String sql = "alter table " + attr.getTableName() + " add column " + attr.getColName() + " " + attr.getColType();
        return sql;
    }

    private static Properties loadProperties() {
        Properties prop = new Properties();
        try {
            String filePath = FileUtil.getFilePath("jdbc.properties",Constants.Env.BASE_HOME);
            InputStream input = new FileInputStream(filePath);
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prop;
    }

    /**
     * 获取jdbc配置文件参数
     * @param key
     * @return
     */
    private static String getJDBCValue(String key){
        String value = null;
        try {
            value = jdbcProperties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}
