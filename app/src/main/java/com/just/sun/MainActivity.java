package com.just.sun;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;

import com.just.sun.activitys.VideoActivity;
import com.just.sun.fragments.CardViewFragment;
import com.just.sun.fragments.OtherFragment;
import com.just.sun.fragments.RxDBindFragment;
import com.just.sun.fragments.TabLayoutFragment;
import com.just.sun.fragments.VitamioFragment;
import com.just.sun.utils.StatusBarCompat;

import io.vov.vitamio.Vitamio;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private int selectedCur = R.id.tab_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.dismissPopupMenus();//ToolBar右侧的菜单项消失
        toolbar.setTitle("首页");
        //toolbar.setNavigationIcon(R.mipmap.btn_title_back); //最左侧的图标，可以设置返回键
        //获取工具栏的高度
        /*Rect rect = new Rect();
        Window window = getWindow();
        int statusBarHeight = rect.top;
        int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight = contentViewTop - statusBarHeight;
        toolbar.setPadding(0, titleBarHeight, 0, 0);*/


        setSupportActionBar(toolbar);
        StatusBarCompat.compat(this, getResources().getColor(R.color.status_bar_color));
        //        StatusBarCompat.compat(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "欢迎来到Android的世界", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        /**
         * 这一块是左上角的菜单按钮
         */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(0).setChecked(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, TabLayoutFragment.newInstance(null, null));
        transaction.commit();
        selectedCur = R.id.tab_layout;

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // 选中的字体颜色就是style中的colorPrimary颜色
        int id = item.getItemId();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (id == R.id.tab_layout) {
            if (selectedCur != id) {
                selectedCur = id;
                transaction.replace(R.id.container, TabLayoutFragment.newInstance(null, null));
            }
        } else if (id == R.id.card_view) {
            if (selectedCur != id) {
                selectedCur = id;
                transaction.replace(R.id.container, CardViewFragment.newInstance(null, null));
            }

        } else if (id == R.id.txt_rx_dbing) {
            if (selectedCur != id) {
                selectedCur = id;
                transaction.replace(R.id.container, RxDBindFragment.newInstance(null, null));
            }
        } else if (id == R.id.other) {
            if (selectedCur != id) {
                selectedCur = id;
                transaction.replace(R.id.container, OtherFragment.newInstance(null, null));
            }
        } else if (id == R.id.vitamio) {
            Intent intent = new Intent(MainActivity.this, VideoActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {
        }
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
