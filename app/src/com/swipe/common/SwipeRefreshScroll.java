package com.swipe.common;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author 任益波
 * @date 2016/12/12
 * @description
 */
public class SwipeRefreshScroll extends SwipeRefeshBase<NestedScrollView> {

    public SwipeRefreshScroll(Context context) {
        super(context);
    }

    public SwipeRefreshScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    View getChildView() {
        if (childView == null) {
            int mChildCount = getChildCount();
            if (mChildCount < 1) {
                Log.e("SwipeRefreshScroll", "SwipeRefreshScroll not contain ScrollView as childView");
                return null;
            } else {
                for (int i = 0; i < mChildCount; i++) {
                    View view = getChildAt(i);
                    if (view instanceof NestedScrollView) {
                        childView = (NestedScrollView) view;
//                        childView.setOnScrollListener(this);
                        break;
                    }
                }
                if (childView == null) {
                    Log.e("SwipeRefreshScroll", "SwipeRefreshScroll can not find ScrollView");
                }
            }
        }
        return childView;
    }

    @Override
    protected void measureFoot() {
        return;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (childView.getMeasuredHeight() <= getScrollY() + childView.getHeight()) {
            Log.d("SwipeRefreshScroll", "SwipeRefreshScroll is touch bottom");
        }
    }

    @Override
    protected void addFootView() {
        return;
    }

    @Override
    protected void removeFootView() {
        return;
    }
}
