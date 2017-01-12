package com.swipe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.swipe.R;
import com.swipe.common.SwipeRefeshBase;
import com.swipe.common.SwipeRefreshExpandLv;
import com.swipe.common.SwipeRefreshListView;
import com.swipe.common.SwipeRefreshScroll;

/**
 * @author 任益波
 * @date 2016/12/22
 * @description
 */
public abstract class SwipeFragment extends BaseFragment {

    protected boolean isPullRefresh = false;// 是否已经下拉刷新
    protected boolean isPullLoad = false;// 是否在上拉加载
    protected SwipeRefreshListView mSwipeListview;
    protected ListView mListview;
    protected SwipeRefreshExpandLv mSwipeExpandLv;
    protected ExpandableListView mExpandLv;
    protected SwipeRefreshScroll mSwipeScroll;
    protected NestedScrollView mScroll;
    protected long mExitTime;
    protected int mFragmentresId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initFragmentResId();
        if (mFragmentresId != 0) {
            rootView = inflater.inflate(mFragmentresId, null);
        }
        initView();
        return rootView;
    }

    protected void initFragmentResId() {
    }

    protected abstract void initView();

    /**
     * 下拉刷新
     */
    protected void onNetRefresh() {
    }

    /**
     * 上拉加载
     */
    protected void onNetLoad() {
    }

    public void getRefreshListview() {
        mSwipeListview = (SwipeRefreshListView) rootView.findViewById(R.id.swipe_refresh_lv);
        mSwipeListview.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light);
        mListview = (ListView) rootView.findViewById(R.id.swipe_lv);
        mSwipeListview.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                long startTime = System.currentTimeMillis();
                if ((startTime - mExitTime) < 3000) {
                    mSwipeListview.setRefreshing(false);
                    return;
                } else {
                    // 如果两次按键时间间隔大于3000毫秒，则不退出
                    mExitTime = System.currentTimeMillis();// 更新mExitTime
                }
                if (isPullRefresh) {// 如果正在刷新就返回
                    return;
                }
                isPullRefresh = true;
                onNetRefresh();
            }
        });
        mSwipeListview.setOnLoadListener(new SwipeRefeshBase.onLoadListener() {
            @Override
            public void onLoad() {
                if (isPullLoad) {
                    return;
                }
                isPullLoad = true;
                onNetLoad();
            }
        });
    }

    public void getRefreshExpand() {
        mSwipeExpandLv = (SwipeRefreshExpandLv) rootView.findViewById(R.id.swipe_refresh_expand);
        mSwipeExpandLv.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light);
        mExpandLv = (ExpandableListView) rootView.findViewById(R.id.swipe_expand);
        mSwipeExpandLv.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                long startTime = System.currentTimeMillis();
                if ((startTime - mExitTime) < 3000) {
                    mSwipeExpandLv.setRefreshing(false);
                    return;
                } else {
                    // 如果两次按键时间间隔大于3000毫秒，则不退出
                    mExitTime = System.currentTimeMillis();// 更新mExitTime
                }
                if (isPullRefresh) {// 如果正在刷新就返回
                    return;
                }
                isPullRefresh = true;
                onNetRefresh();
            }
        });
        mSwipeExpandLv.setOnLoadListener(new SwipeRefeshBase.onLoadListener() {
            @Override
            public void onLoad() {
                if (isPullLoad) {
                    return;
                }
                isPullLoad = true;
                onNetLoad();
            }
        });
    }

    public void getRefreshScroll(int resId) {
        mSwipeScroll = (SwipeRefreshScroll) rootView.findViewById(R.id.swipe_refresh_scroll);
        mSwipeScroll.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light);
        mScroll = (NestedScrollView) rootView.findViewById(R.id.swipe_scroll);
        mSwipeScroll.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                long startTime = System.currentTimeMillis();
                if ((startTime - mExitTime) < 3000) {
                    mSwipeScroll.setRefreshing(false);
                    return;
                } else {
                    // 如果两次按键时间间隔大于3000毫秒，则不退出
                    mExitTime = System.currentTimeMillis();// 更新mExitTime
                }
                if (isPullRefresh) {// 如果正在刷新就返回
                    return;
                }
                isPullRefresh = true;
                onNetRefresh();
            }
        });
        View mView = LayoutInflater.from(mContext).inflate(resId, null);
        mScroll.addView(mView);
    }

    protected void onResponseSuccess(String msg, String data, int code) {
        resetSwipeView();
    }

    protected void onResponseFailure(String msg, int code) {
        resetSwipeView();
    }

    private void resetSwipeView() {
        isPullRefresh = false;
        isPullLoad = false;
        if (null != mSwipeExpandLv) {
            mSwipeExpandLv.setRefreshing(false);
            mSwipeExpandLv.loadComplete();
        }
        if (null != mSwipeListview) {
            mSwipeListview.setRefreshing(false);
            mSwipeListview.loadComplete();
        }
        if (null != mSwipeScroll) {
            mSwipeScroll.setRefreshing(false);
            mSwipeScroll.loadComplete();
        }
    }

}
