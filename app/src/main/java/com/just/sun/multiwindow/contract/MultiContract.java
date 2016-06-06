package com.just.sun.multiwindow.contract;

import com.common.base.BasePresenter;
import com.common.base.BaseView;

/**
 * Created by walkingMen on 2016/6/1.
 */
public class MultiContract {
    public interface View extends BaseView<Presenter> {

        void showTxt(String content);

        void hideTxt();
    }

    public interface Presenter extends BasePresenter {

        void saveContent(String content);

        void deleContent(String key);

        void deleAllContent();


        String getTxtContent();
    }
}
