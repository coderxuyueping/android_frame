package com.example.admin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by xyp on 2018/3/15.
 */

public class PreferencesHelper {
    private static SharedPreferences sharedPreferences = null;
    private static SharedPreferences.Editor editor = null;

    private PreferencesHelper(){}

    private static SharedPreferences getSharedPreferences(Context context){
        if (sharedPreferences == null){
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return sharedPreferences;
    }

    private static SharedPreferences.Editor getEditor(Context context){
        if (editor == null){
            editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        }
        return editor;
    }

    public static String getStringValue(Context context,String key,String defaultValue){
        return getSharedPreferences(context).getString(key,defaultValue);
    }

    public static void setStringValue(Context context,String key,String value){
        getEditor(context).putString(key, value).commit();
    }

    public static int getIntValue(Context context,String key,int defaultValue){
        return getSharedPreferences(context).getInt(key,defaultValue);
    }

    public static void setIntValue(Context context,String key,int value){
        getEditor(context).putInt(key, value).commit();
    }

    public static boolean getBooleanValue(Context context,String key,boolean defaultValue){
        return getSharedPreferences(context).getBoolean(key,defaultValue);
    }

    public static void setBooleanValue(Context context,String key,boolean value){
        getEditor(context).putBoolean(key, value).commit();
    }

    public static long getLongValue(Context context,String key,long defaultValue){
        return getSharedPreferences(context).getLong(key, defaultValue);
    }

    public static void setLongValue(Context context,String key,long value){
        getEditor(context).putLong(key, value).commit();
    }
}
