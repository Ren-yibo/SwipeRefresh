package com.swipe.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

/**
 * @author 任益波
 * @date 2016/12/12
 * @description
 */
public class SwipeRefreshExpandLv extends SwipeRefeshBase<ExpandableListView> {

    public SwipeRefreshExpandLv(Context context) {
        super(context);
    }

    public SwipeRefreshExpandLv(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    View getChildView() {
        if (childView == null) {
            int mChildCount = getChildCount();
            if (mChildCount < 1) {
                Log.e("SwipeRefreshExpandLv", "SwipeRefreshExpandLv not contain ExpandLv as childView");
                return null;
            } else {
                for (int i = 0; i < mChildCount; i++) {
                    View view = getChildAt(i);
                    if (view instanceof ExpandableListView) {
                        childView = (ExpandableListView) view;
                        // 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
                        childView.setOnScrollListener(this);
                        break;
                    }
                }
                if (childView == null) {
                    Log.e("SwipeRefreshExpandLv", "SwipeRefreshExpandLv not find ExpandLv as childView");
                }
            }
        }
        return childView;
    }

    boolean isBottom() {
//        if (null != childView && null != childView.getAdapter()) {
//            return childView.getLastVisiblePosition() == (childView.getAdapter().getCount() - 1);
//        }
//        return false;
        if (null == childView) {
            return false;
        }
        boolean result = false;
        if (childView.getLastVisiblePosition() == (childView.getCount() - 1)) {
            final View bottomChildView = childView.getChildAt(childView.getLastVisiblePosition() - childView.getFirstVisiblePosition());
            if (null == bottomChildView) {
                return false;
            }
            result = (childView.getHeight() >= bottomChildView.getBottom());
        }
        return result;
    }

    @Override
    protected void measureFoot() {
        super.measureFoot();
        if (mFootView == null || childView == null || mFootHeight > 0) {
            return;
        }
        mFootView.measure(0, 0);
        mFootHeight = mFootView.getMeasuredHeight();
        mFootView.setPadding(0, -mFootHeight, 0, 0);
        childView.addFooterView(mFootView, null, false);
    }

    @Override
    protected void addFootView() {
        super.addFootView();
        if (null != childView && null != mFootView) {
            childView.setSelection(childView.getCount());
        }
    }
}
