package com.anjuxing.platform.code.entity;

import java.util.List;
import java.util.Vector;

/**
 * 数据信息
 */
public class DataModule {

    private String tableName;
    private String tableComment;
    private List<DbTableColumn> columnList;
    private Vector<Object> columns;
    private Vector<Vector<Object>> datas;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<DbTableColumn> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<DbTableColumn> columnList) {
        this.columnList = columnList;
    }

    public Vector<Object> getColumns() {
        return columns;
    }

    public void setColumns(Vector<Object> columns) {
        this.columns = columns;
    }

    public Vector<Vector<Object>> getDatas() {
        return datas;
    }

    public void setDatas(Vector<Vector<Object>> datas) {
        this.datas = datas;
    }
}
