package com.likeios.fuhao.tang.ui;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by tang on 15-10-14.
 */
public class UIView implements TouchEventMeasure{
    private View actualView;
    private ArrayList<UIView> subViews;
    @Override
    public UIView hitTest(MotionEvent event) {
        UIView holderView = null;
        if (pointInside(event)){
            holderView = this;
            if (subViews!=null && subViews.size() != 0){
                holderView = null;
                for (UIView mView : subViews){
                    holderView = mView.hitTest(event);
                    if(holderView!=null) break;
                }
            }
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

    protected ArrayList<UIView> getSubView(){
        return subView;
    }

    public View getActualView(){
        return actualView;
    }
}
