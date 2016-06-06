package com.common.database;

/**
 * Created on 2015/12/7.
 */
public class TableInfo {
    //table name
    public String tbName;
    //table version
    public int tbVer;

    public TableInfo(String tbName,int tbVer) {
        this.tbName = tbName;
        this.tbVer = tbVer;
    }

}
