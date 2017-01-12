package com.swipe.fragment;

import com.swipe.R;

/**
 * @author 任益波
 * @date 2017/1/12
 * @description
 */
public class ScrollFragment extends SwipeFragment {

    @Override
    protected void initFragmentResId() {
        this.mFragmentresId = R.layout.fragment_scroll;
    }

    @Override
    protected void initView() {
        getRefreshScroll(R.layout.layout_scroll);
    }

    @Override
    protected void onNetRefresh() {
        super.onNetRefresh();
        onResponseSuccess("", "", 0);
        showToast("refresh Over");
    }

    @Override
    protected void onNetLoad() {
        super.onNetLoad();
        onResponseSuccess("", "", 1);
        showToast("load Over");
    }
}
