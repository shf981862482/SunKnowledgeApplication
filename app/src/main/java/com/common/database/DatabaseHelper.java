package com.common.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.common.common.Global;
import com.common.utils.Logger;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by sun on 2015/11/27.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();
    public static volatile DatabaseHelper instance;

    /**
     * 获取单例
     * @return
     */
    public static synchronized DatabaseHelper getInstance(){
        Context context = Global.mContext;
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null) {
                    instance = new DatabaseHelper(context);
                }
            }
        }
        return instance;
    }

    public DatabaseHelper(Context context) {
        super(context, DataBaseCommon.DB_NAME, null, DataBaseCommon.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            Map<Class<?>,TableInfo> mMap = DataBaseCommon.mMap;
            DataBaseSharePre mSharePre = new DataBaseSharePre(Global.mContext);
            for(Map.Entry<Class<?>, TableInfo> entry : mMap.entrySet()){
                Class<?> clazz = entry.getKey();
                TableInfo tbInfo = entry.getValue();
                //create table
                TableUtils.createTable(connectionSource,clazz);
                //save version
                String name = tbInfo.tbName;
                int ver = tbInfo.tbVer;
                mSharePre.saveTBVer(name,ver);
            }
        } catch (SQLException e){
            Logger.e(TAG,e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            Map<Class<?>,TableInfo> mMap = DataBaseCommon.mMap;
            DataBaseSharePre mSharePre = new DataBaseSharePre(Global.mContext);
            for(Map.Entry<Class<?>, TableInfo> entry : mMap.entrySet()){
                Class<?> clazz = entry.getKey();
                TableInfo tbInfo = entry.getValue();
                int oldVer = mSharePre.getTBVerByName(tbInfo.tbName);
                int newVer = tbInfo.tbVer;
                if(newVer != oldVer){
                    TableUtils.dropTable(connectionSource, clazz, true);
//                    TableUtils.clearTable(connectionSource,clazz);
                    TableUtils.createTable(connectionSource, clazz);

                    //save table version
                    mSharePre.saveTBVer(tbInfo.tbName,tbInfo.tbVer);

                    //log info
                    if(Logger.isDebug()){
                        Logger.d(TAG,"[onUpgrade]" + " table:" + clazz.getName() + " upgrade...");
                    }
                }
            }
        } catch (SQLException e) {
            Logger.e(TAG,e);
        }
    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        Exception exception = new Exception("严重警告,数据库发生降级,请纠正该问题...");
        Logger.e(TAG, "", exception);
    }

    @Override
    public void close() {
        super.close();
    }
}
