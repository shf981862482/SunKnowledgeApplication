package com.just.sun.multiwindow.database;

import com.common.database.DatabaseHelper;
import com.j256.ormlite.dao.Dao;
import com.just.sun.multiwindow.data.Content;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by walkingMen on 2016/6/2.
 */
public class ContentDao {
    public static final String TAG = ContentDao.class.getSimpleName();
    private Dao<Content, Long> mContentDao;

    public ContentDao() {
        try {
            mContentDao = DatabaseHelper.getInstance().getDao(Content.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savaContent(Content content) {
        try {
            mContentDao.createOrUpdate(content);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllContent() {
        try {
            mContentDao.delete(mContentDao.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Content> queryAllContent() {
        try {
            List<Content> contents = mContentDao.queryForAll();
            return contents;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

/*    public Content queryContent(String content){
        QueryBuilder<Content, Long> builder = mContentDao.queryBuilder();
        try {
            builder.where().ge("content",content);
            GenericRawResults<String[]> strings = mContentDao.queryRaw(builder.prepareStatementString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


}
