package com.swipe.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author 任益波
 * @date 2016/8/10 9:03
 * @description
 */
public abstract class BaseActivity extends FragmentActivity {

    protected Context mContext;
    protected TextView mActionBarTitleTv, mActionBarLeftTv, mActionBarRightTv;
    protected ImageView mActionBarRightIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        beforeOnCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        mContext = this;
        afterOnCreate(savedInstanceState);
    }

    /**
     * 用于视图文件的加载
     *
     * @param savedInstanceState
     */
    protected abstract void beforeOnCreate(Bundle savedInstanceState);

    /**
     * 用于View的加载和初始化事件
     *
     * @param savedInstanceState
     */
    protected abstract void afterOnCreate(Bundle savedInstanceState);

    protected <W extends View> W getElemetView(int id) {
        return (W) getWindow().getDecorView().findViewById(id);
    }

    protected <W extends View> W getElemetView(View view, int id) {
        return (W) view.findViewById(id);
    }

    protected boolean isNullString(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return false;
    }

    protected void showToast(Object resContent) {
        if (resContent == null) {
            return;
        }
        if (resContent instanceof Integer) {
            Toast.makeText(this, getString((Integer) resContent), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, (String) resContent, Toast.LENGTH_SHORT).show();
        }
    }

    protected void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    protected void startNewActivityForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    /**
     * 点击返回
     */
    protected void launchBack() {
    }

    /**
     * 点击完成
     */
    protected void launchGo() {
    }

}