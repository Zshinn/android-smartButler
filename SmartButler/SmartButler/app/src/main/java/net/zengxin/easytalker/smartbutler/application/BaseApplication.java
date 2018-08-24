package net.zengxin.easytalker.smartbutler.application;

import android.app.Application;

/**
 * @author zengxin
 *         创建时间：2018/08/24
 *         描述：
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //bugly
        //CrashReport.initCrashReport(getApplicationContext(), "注册时申请的APPID", false);
    }
}
