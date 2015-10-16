package com.likeios.fuhao.tang.likeiosapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.likeios.fuhao.tang.ui.UITextView;
import com.likeios.fuhao.tang.ui.UIView;

/**
 * Created by tang on 15-10-14.
 */
public class MainActivity extends IosActivty{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIView mUiView = getRootView();
        UITextView mSubView = new UITextView(this);
        mSubView.frame.set(200,0,500,100);
        mSubView.setBackgroundColor(Color.GREEN);
        mSubView.setText_NEW("aaaaa");
        mUiView.addSubViewNew(mSubView);
    }
}
