package com.likeios.fuhao.tang.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by tang on 15-10-16.
 */
public class UITextView extends UIView{
    public UITextView(Context context) {
        super(context);
        actualView = new TextView(context);
    }

    public UITextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        actualView = new TextView(context,attrs);
    }

    public UITextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        actualView = new TextView(context,attrs,defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UITextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        actualView = new TextView(context,attrs,defStyleAttr,defStyleRes);
    }

    public void setText_NEW(String param){
        ((TextView)actualView).setText(param);
    }

}
