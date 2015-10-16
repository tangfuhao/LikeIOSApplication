package com.likeios.fuhao.tang.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tang on 15-10-14.
 */
public class UIView extends ViewGroup implements TouchEventMeasure{

    /**
     * frame：描述当前视图在其父视图中的位置和大小。
     * bounds：描述当前视图在其自身坐标系统中的位置和大小。
     * center：描述当前视图的中心点在其父视图中的位置。
     */
    public Rect frame = new Rect(0,0,0,0);
    public Rect bounds = new Rect(0,0,0,0);;
    public Point center = new Point(0,0);
    protected View actualView;
    private boolean isTouchable = true;

    protected boolean isUseAndroidMeasure = false;

    /////////////////////////////////////视图方法
    public UIView(Context context) {
        super(context);
    }

    public UIView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UIView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UIView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isUseAndroidMeasure){
            super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        }else{
            setMeasuredDimension(frame.width(), frame.height());
            if (actualView!=null){
                int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(frame.width(),MeasureSpec.EXACTLY);
                int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(frame.height(),MeasureSpec.EXACTLY);
                actualView.measure(childWidthMeasureSpec,childHeightMeasureSpec);
            }
        }

        final int count = getChildCount();

        for (int i = 0; i < count; i++) {
            UIView child = (UIView) getChildAt(i);
            if (child.getVisibility() != GONE) {
                int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(child.frame.width(),MeasureSpec.EXACTLY);
                int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(child.frame.height(),MeasureSpec.EXACTLY);
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            }
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed){
            if (actualView!=null){
                actualView.layout(frame.left,frame.top,frame.right,frame.bottom);
            }

            final int count = getChildCount();
            for (int i = 0; i < count; i++) {
                UIView child = (UIView) getChildAt(i);
                if (child.getVisibility() != GONE) {
                    child.layout(child.frame.left,child.frame.top,child.frame.right,child.frame.bottom);
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(actualView!=null)
            actualView.draw(canvas);
    }

    ///////////////////////////////////////////////消息传递
    @Override
    public UIView hitTest(MotionEvent event) {
        UIView holderView = null;
        if (pointInside(event)){
            int childNum = getChildCount();
            if (childNum != 0){
                for (int i = 0 ; i < childNum ; i++){
                    UIView mUIUView = (UIView)getChildAt(i);
                    if( (holderView = mUIUView.hitTest(event)) != null) break;
                }
            }

            if(holderView==null && isTouchable)
                holderView = this;
        }
        return holderView;
    }

    @Override
    public boolean pointInside(MotionEvent event) {
        if(actualView!=null){
            Rect mR = new Rect();
            actualView.getGlobalVisibleRect(mR);
            return mR.contains((int)event.getX(),(int)event.getY());
        }
        return false;
    }

    ///////////////////////////////////////////////UIView绑定
    public View getActualView(){
        return actualView;
    }


    ///////////////////////////////////////////////

    /**
     * 添加子View
     * @param mSubView
     */
    public void addSubViewNew(UIView mSubView) {
        this.addView(mSubView);
    }


}
