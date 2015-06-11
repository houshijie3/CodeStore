package com.king.codestore;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Created by houshijie on 15/6/11.
 */
public class UIUtils {
    /** 获取资源 */
    public static Resources getResources() {
        return CodeStoreApplication.getInstance().getResources();
    }

    /** 获取文字 */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /** 获取文字数组 */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /** 获取dimen */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /** 获取drawable */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /** 获取颜色 */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }
}
