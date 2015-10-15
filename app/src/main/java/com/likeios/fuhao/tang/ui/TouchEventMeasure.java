package com.likeios.fuhao.tang.ui;

import android.view.MotionEvent;

/**
 * Created by tang on 15-10-14.
 * 用来辅助事件分发查找和计算
 */
public interface TouchEventMeasure {
    /**
     * 返回处理事件的View
     * @param event
     * @return
     */
    UIView hitTest(MotionEvent event);

    /**
     * 判断触摸点是否在当前视图内
     * @param event
     * @return
     */
    boolean pointInside(MotionEvent event);
}
