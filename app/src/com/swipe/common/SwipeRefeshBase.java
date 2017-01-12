package com.swipe.common;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;

import com.swipe.R;


/**
 * @author 任益波
 * @date 2016/12/12
 * @description
 */
public abstract class SwipeRefeshBase<T extends View> extends SwipeRefreshLayout implements AbsListView.OnScrollListener {

    private Context mContext;
    /**
     * 滑动距离
     */
    private int mTouchSlop;

    /**
     * 包含的子view
     */
    protected T childView;

    /**
     * 加载更多的视图
     */
    protected View mFootView;

    /**
     * 按下时的y坐标
     */
    private int mYDown;

    /**
     * 抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
     */
    private int mLastY;

    /**
     * 是否在加载中 ( 上拉加载更多 )
     */
    private boolean isLoading = false;

    private boolean canLoadMore = true;

    /**
     * 是否在底部
     */
    private boolean isLast = false;

    protected int mFootHeight;

    private onLoadListener iLoadListener;

    public SwipeRefeshBase(Context context) {
        super(context);
        this.mContext = context;
        initView(context);
    }

    public SwipeRefeshBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(context);
    }

    private void initView(Context context) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mFootView = LayoutInflater.from(mContext).inflate(R.layout.layout_foot, null, false);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        getChildView();
        measureFoot();
    }

    abstract View getChildView();

    /**
     * 测量FootView的高度
     */
    protected void measureFoot() {
        if (mFootView == null || childView == null || mFootHeight > 0) {
            return;
        }
    }

    /**
     * 设置加载更多的等待视图
     *
     * @param id
     */
    protected void setFootViewResId(int id) {
        if (mContext != null) {
            mFootView = LayoutInflater.from(mContext).inflate(id, null, false);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 按下
                mYDown = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                // 移动
//                mLastY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                mLastY = (int) event.getRawY();
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    /**
     * 判断条件：到达最底部，上拉操作，不在加载状态
     *
     * @return
     */
    private boolean canLoad() {
        if (!canLoadMore) {
            //不支持加载更多
            return false;
        }
        return isLast && !isLoading && isPullUp();
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        setLoading(true);
        if (iLoadListener != null) {
            iLoadListener.onLoad();
        }
    }

    /**
     * 设置是否加载更多
     *
     * @param loading
     */
    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading) {
            if (mFootView != null) {
                addFootView();
            }
        } else {
            if (mFootView != null) {
                removeFootView();
            }
            mYDown = 0;
            mLastY = 0;
        }
    }

    /**
     * 添加加载等待视图
     */
    protected void addFootView(){
        if (null != childView && null != mFootView) {
            mFootView.setPadding(0, 0, 0, 0);
        }
    }

    /**
     * 移除加载等待视图
     */
    protected void removeFootView(){
        if (null != childView && null != mFootView) {
            mFootView.setPadding(0, -mFootHeight, 0, 0);
        }
    }

    /**
     * 加载更多完毕
     */
    public void loadComplete() {
        setLoading(false);
    }

    /**
     * 是否是上拉操作
     *
     * @return
     */
    private boolean isPullUp() {
//        return (mYDown - mLastY) >= mTouchSlop;
        int mMinHeight = 0;
        if (null != mFootView) {
            mMinHeight = mFootView.getMeasuredHeight();
        }
        if (mMinHeight <= 0) {
            mMinHeight = 200;
        }
        return (mYDown - mLastY) >= mMinHeight;
    }

    public interface onLoadListener {
        void onLoad();
    }

    /**
     * 设置加载更多的监听
     *
     * @param listener
     */
    public void setOnLoadListener(onLoadListener listener) {
        this.iLoadListener = listener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (canLoad() && scrollState == SCROLL_STATE_IDLE) {
            loadMore();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //判断是否是最后一个
        isLast = (totalItemCount == firstVisibleItem + visibleItemCount) && totalItemCount > 0;
    }

    public void setCanLoadMore(boolean canLoadMore) {
        this.canLoadMore = canLoadMore;
    }
}
