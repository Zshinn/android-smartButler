package net.zengxin.easytalker.smartbutler.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author zengxin
 *         创建时间：2018/08/24
 *         描述：
 */

public class ShareUtils {

    public static final String NAME="config";

    public static void putString(Context context,String key,String value){
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    public static String getString(Context context,String key,String value){
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getString(key,value);
    }

    public static void putInt(Context context,String key,int value){
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }

    public static int getInt(Context context,String key,int value){
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getInt(key,value);
    }

    public static void putBoolean(Context context,String key,boolean value){
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    public static boolean getBoolean(Context context,String key,boolean value){
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getBoolean(key,value);
    }

    //删除单个
    public static void deletShare(Context context,String key){
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    //删除全部
    public static void deletAll(Context context){
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

}
