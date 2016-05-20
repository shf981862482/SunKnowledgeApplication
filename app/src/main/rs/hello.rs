#pragma version(1)
#pragma rs java_package_name(com.just.sun.rs.hello)


#include "rs_graphics.rsh"

int gTouchX;
int gTouchY;

void init(){//一些初始化动作
    gTouchX=50.0f;
    gTouchY=50.0f;
}

//application main 
int root(void){//程序入口，根据返回值(ms)作刷新。比如上面写的20相当于每20毫秒刷新一次。
    rsgClearColor(0.0f,1.0f,0.0f,0.0f);//颜色更改为no red,full green,no blue,no opacity ，的RGBA值
    rsgFontColor(1.0f,0.0f,1.0f,1.0f);//设置字体颜色
    rsgDrawText("my first renderscript application",gTouchX,gTouchY);//根据应用传上来的x,y 将字画在屏幕对应的坐标上
    rsDebug("======my renderscript debug========",rsUptimeMillis());//打印日志  rsUptimeMillis则定义在rs_time.rsh头文件中。
    return 20;
}