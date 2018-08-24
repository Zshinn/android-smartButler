package net.zengxin.easytalker.smartbutler.utils;

import android.util.Log;

/**
 * @author zengxin
 *         创建时间：2018/08/24
 *         描述：log封装类
 */

public class L {
    public static final boolean DEBUG=true;
    public static final String TAG="SmartButler";

    //WDEI
    public static void w(String text){
        if(DEBUG){
            Log.w(TAG,text);
        }
    }

    public static void d(String text){
        if(DEBUG){
            Log.d(TAG,text);
        }
    }

    public static void e(String text){
        if(DEBUG){
            Log.e(TAG,text);
        }
    }

    public static void i(String text){
        if(DEBUG){
            Log.i(TAG,text);
        }
    }

}
