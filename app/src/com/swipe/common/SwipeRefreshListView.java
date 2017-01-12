package com.swipe.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

/**
 * @author 任益波
 * @date 2016/12/12
 * @description
 */
public class SwipeRefreshListView extends SwipeRefeshBase<ListView> {

    public SwipeRefreshListView(Context context) {
        super(context);
    }

    public SwipeRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    View getChildView() {
        if (childView == null) {
            int mChildCount = getChildCount();
            if (mChildCount < 1) {
                Log.e("SwipeRefreshListView", "SwipeRefreshListView not contain Listview as childView");
                return null;
            } else {
                for (int i = 0; i < mChildCount; i++) {
                    View view = getChildAt(i);
                    if (view instanceof ListView) {
                        childView = (ListView) view;
                        // 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
                        childView.setOnScrollListener(this);
                        break;
                    }
                }
                if (childView == null) {
                    Log.e("SwipeRefreshListView", "SwipeRefreshListView can not find ListView");
                }
            }
        }
        return childView;
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

    boolean isBottom() {
//        if (null != childView && null != childView.getAdapter()) {
//            return childView.getLastVisiblePosition() == (childView.getAdapter().getCount() - 1);
//        }
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
    protected void addFootView() {
        super.addFootView();
        if (null != childView && null != mFootView) {
            childView.setSelection(childView.getCount());
        }
    }
}
