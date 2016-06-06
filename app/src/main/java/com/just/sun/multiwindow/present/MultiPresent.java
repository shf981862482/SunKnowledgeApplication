package com.just.sun.multiwindow.present;

import android.content.Intent;

import com.just.sun.multiwindow.contract.MultiContract;
import com.just.sun.multiwindow.data.Content;
import com.just.sun.multiwindow.database.ContentDao;

/**
 * Created by walkingMen on 2016/6/2.
 */
public class MultiPresent implements MultiContract.Presenter {
    private final ContentDao contentDao;
    private final MultiContract.View multiView;

    public MultiPresent(MultiContract.View multiView) {
        contentDao = new ContentDao();
        this.multiView = multiView;
        this.multiView.setPresenter(this);
    }

    @Override
    public void saveContent(String content) {
        contentDao.savaContent(new Content(content));
    }

    @Override
    public void deleContent(String key) {

    }

    @Override
    public void deleAllContent() {
        contentDao.deleteAllContent();
    }

    @Override
    public String getTxtContent() {
        return null;
    }

    @Override
    public void start() {
    }
}
