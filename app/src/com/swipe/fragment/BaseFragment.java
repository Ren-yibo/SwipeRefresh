package com.swipe.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * @author 任益波
 * @date 2016/8/15 9:14
 * @description
 */
public class BaseFragment extends Fragment{

    protected View rootView;
    protected Activity mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        return rootView;
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
            Toast.makeText(mContext, getString((Integer) resContent), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, (String) resContent, Toast.LENGTH_SHORT).show();
        }
    }

    protected <W extends View> W getElemetView(View view, int id) {
        return (W) view.findViewById(id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    public void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(mContext, cls);
        startActivity(intent);
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
