package com.likeios.fuhao.tang.likeiosapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.likeios.fuhao.tang.global.PhoneInfoManager;
import com.likeios.fuhao.tang.ui.TouchEventLayout;
import com.likeios.fuhao.tang.ui.UIView;

/**
 * Created by tang on 15-10-15.
 */
public class IosActivty extends Activity {
    private UIView rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ioslayout);
        rootView = (UIView) findViewById(R.id.rootView);
        PhoneInfoManager.getInstance().init(this);
    }



    protected UIView getRootView(){
        return rootView;
    }
}
