package com.likeios.fuhao.tang.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.likeios.fuhao.tang.global.PhoneInfoManager;

/**
 * Created by tang on 15-10-14.
 * 管理所有touch事件
 * 参考资料 http://www.cnblogs.com/CHONGCHONG2008/archive/2012/08/03/2621410.html UIKIT框架类之视图动画和坐标系介绍！
 *         http://www.cnblogs.com/Quains/p/3369132.html iOS触摸事件处理
 */
public class TouchEventLayout extends UIView{
    private UIView TouchEventHolder;
    public TouchEventLayout(Context context) {
        super(context);
    }

    public TouchEventLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEventLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TouchEventLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        isUseAndroidMeasure = true;
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed){
            PhoneInfoManager.getInstance().setContentLayout(l,t,r,b);
        }

        super.onLayout(changed,l,t,r,b);
    }

    /**
     * 拦截所有touch事件 统一分配
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    /**
     * 分发事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN){
            //获取事件响应者 TouchEventHolder
            UIView UITouchEventHolder = null;
            int num = getChildCount();
            for (int i = 0 ; i < num ; i++){
                UIView mUIUView = (UIView)getChildAt(i);
                if( (UITouchEventHolder = mUIUView.hitTest(event)) != null) break;
            }

            if (UITouchEventHolder!=null){
                TouchEventHolder = UITouchEventHolder;
                TouchEventHolder.onTouchEvent(event);
                return true;
            }
            return false;
        }else{
            return TouchEventHolder.onTouchEvent(event);
        }
    }
}
