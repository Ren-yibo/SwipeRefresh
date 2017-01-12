package com.swipe.fragment;

import android.os.Handler;

import com.swipe.R;
import com.swipe.adapter.ExpandAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 任益波
 * @date 2017/1/11
 * @description
 */
public class ExpandFragment extends SwipeFragment {

    private ExpandAdapter mAdapter;
    private List<String> mGroups;
    private List<List<String>> mChilds;

    @Override
    protected void initFragmentResId() {
        mFragmentresId = R.layout.fragment_expand;
    }

    @Override
    protected void initView() {
        getRefreshExpand();
        mAdapter = new ExpandAdapter(mContext);
        mExpandLv.setAdapter(mAdapter);
        mGroups = new ArrayList<>();
        mChilds = new ArrayList<>();
        mGroups.add("");
        mGroups.add("");
        List<String> mChild = new ArrayList<>();
        mChild.add("");
        mChild.add("");
        mChild.add("");
        mChilds.add(mChild);
        mChilds.add(mChild);
        mAdapter.invalidateData(mGroups, mChilds);
    }

    @Override
    protected void onNetRefresh() {
        super.onNetRefresh();
        onResponseSuccess("", "", 0);
    }

    @Override
    protected void onNetLoad() {
        super.onNetLoad();
        onResponseSuccess("", "", 1);
    }

    @Override
    protected void onResponseSuccess(String msg, String data, int code) {
        super.onResponseSuccess(msg, data, code);
        if (code == 0) {
            //刷新
            if (null == mGroups || null == mChilds) {
                return;
            }
            mGroups.clear();
            mChilds.clear();
            int mNum = getRandom();
            List<String> mChild = new ArrayList<>();
            mChild.add("");
            mChild.add("");
            for (int i = 0; i < mNum; i++) {
                mGroups.add("");
                mChilds.add(mChild);
            }
        } else if (code == 1) {
            //加载
            showToast("load Over");
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.invalidateData(mGroups, mChilds);
            }
        }, 400);
    }

    private int getRandom() {
        return new Random().nextInt(10) + 1;
    }
}
