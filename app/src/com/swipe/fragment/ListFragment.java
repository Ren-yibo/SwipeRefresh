package com.swipe.fragment;

import android.os.Handler;

import com.swipe.R;
import com.swipe.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 任益波
 * @date 2016/12/22
 * @description
 */
public class ListFragment extends SwipeFragment {

    private ListAdapter mAdapter;
    private List<String> mList;

    @Override
    protected void initFragmentResId() {
        mFragmentresId = R.layout.fragment_list;
    }

    @Override
    protected void initView() {
        getRefreshListview();
        mAdapter = new ListAdapter(mContext);
        mListview.setAdapter(mAdapter);
        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mAdapter.invalidateData(mList);
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
        if (null == mList) {
            return;
        }
        if (code == 0) {
            //刷新
            mList.clear();
        } else if (code == 1) {
            //加载
        }
        int mNum = getRandom();
        for (int i = 0; i < mNum; i++) {
            mList.add("");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.invalidateData(mList);
            }
        }, 300);
    }

    private int getRandom() {
        return new Random().nextInt(10) + 1;
    }
}
