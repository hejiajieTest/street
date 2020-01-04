package com.tscloud.domain.resource.entity;

import com.tscloud.common.framework.domain.TrackableEntity;

import java.io.Serializable;

/**
 * Created by shm on 2015/10/24.
 */
public class AttrEdit extends TrackableEntity implements Serializable {

    private static final long serialVersionUID = 3500975802063529070L;
    /**
     * 表名称
     */
    private String tableName;

    /**
     * 字段名
     */
    private String colName;

    /**
     *  新的列名
     */
    private String newColName;

    /**
     * 字段类型
     */
    private String colType;

    /**
     * html标签类型
     */
    private String htmlType;

    /**
     * 操作 add添加，remove删除，modify修改
     */
    private String operate;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getNewColName() {
        return newColName;
    }

    public void setNewColName(String newColName) {
        this.newColName = newColName;
    }

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType;
    }

    public String getHtmlType() {
        return htmlType;
    }

    public void setHtmlType(String htmlType) {
        this.htmlType = htmlType;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }
}
