package com.likeios.fuhao.tang.global;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by tang on 15-10-16.
 */
public class PhoneInfoManager {
    private static PhoneInfoManager sInstance;
    private static final Object sLock = new Object();

    public boolean isInit = false;

    public int screenWidth;
    public int screenHeight;
    public int statusBarHeight;
    public int titleBarHeight;
    public int contentHeight;

    public static PhoneInfoManager getInstance() {
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = new PhoneInfoManager();
            }
            return sInstance;
        }
    }

    public void init(Activity mContext){
        WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;


        isInit = true;
    }

    public void setContentLayout(int l, int t, int r, int b) {
        contentHeight = b - t;
        statusBarHeight = screenHeight - contentHeight;
    }
}
