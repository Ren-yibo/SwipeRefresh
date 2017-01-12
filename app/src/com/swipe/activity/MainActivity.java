package com.swipe.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;

import com.swipe.R;
import com.swipe.adapter.ScrollPagerAdapter;
import com.swipe.fragment.ExpandFragment;
import com.swipe.fragment.ListFragment;
import com.swipe.fragment.ScrollFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout mListFrame;
    private FrameLayout mExpandFrame;
    private FrameLayout mScrollFrame;
    private ViewPager mPager;
    private ScrollPagerAdapter mAdapter;
    private ListFragment mListFragment;
    private ExpandFragment mExpendFragment;
    private ScrollFragment mScrollFragment;

    @Override
    protected void beforeOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void afterOnCreate(Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initView() {
        mPager = getElemetView(R.id.homePagePager);
        mListFrame = getElemetView(R.id.homePageListLay);
        mListFrame.setOnClickListener(this);
        mExpandFrame = getElemetView(R.id.homePageExpandLay);
        mExpandFrame.setOnClickListener(this);
        mScrollFrame = getElemetView(R.id.homePageScrollLay);
        mScrollFrame.setOnClickListener(this);
    }

    private void initData() {
        mListFragment = new ListFragment();
        mExpendFragment = new ExpandFragment();
        mScrollFragment = new ScrollFragment();
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(mListFragment);
        mFragments.add(mExpendFragment);
        mFragments.add(mScrollFragment);
        mAdapter = new ScrollPagerAdapter(getSupportFragmentManager(), null, mFragments);
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(2);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTabSelect(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mListFrame.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homePageListLay:
                setTabSelect(0);
                break;
            case R.id.homePageExpandLay:
                setTabSelect(1);
                break;
            case R.id.homePageScrollLay:
                setTabSelect(2);
                break;
            default:
                break;
        }
    }

    private void setTabSelect(int position) {
        mPager.setCurrentItem(position);
        switch (position) {
            case 0:
                mListFrame.setSelected(true);
                mExpandFrame.setSelected(false);
                mScrollFrame.setSelected(false);
                break;
            case 1:
                mListFrame.setSelected(false);
                mExpandFrame.setSelected(true);
                mScrollFrame.setSelected(false);
                break;
            case 2:
                mListFrame.setSelected(false);
                mExpandFrame.setSelected(false);
                mScrollFrame.setSelected(true);
                break;
            default:
                break;
        }
    }

}
