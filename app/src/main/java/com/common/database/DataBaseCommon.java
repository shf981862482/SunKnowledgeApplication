package com.common.database;


import com.just.sun.multiwindow.data.Content;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by diaosi on 2015/12/7.
 */
public class DataBaseCommon {

    public static final String CONTENT_TABLE = "ContentTable";
    //http缓存数据库
    public static final String HTTP_CACHE_TABLE = "httpCacheTable";

    //db名称
    public static final String DB_NAME = "sun.db";
    //db version
    public static final int DB_VERSION = 1;

    public static final Map<Class<?>, TableInfo> mMap = new HashMap<>();

    static {
        //table ContentTable version 1
        mMap.put(Content.class, new TableInfo(CONTENT_TABLE, 1));
    }
}
