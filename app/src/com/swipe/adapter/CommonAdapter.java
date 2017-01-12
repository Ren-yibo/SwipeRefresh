package com.swipe.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 任益波
 * @date 2016/8/11 16:42
 * @description
 */
public class CommonAdapter<T> extends BaseAdapter {

    protected List<T> list;
    protected Context mContext;
    protected View convertView;
    protected IOnChildClickListener listener;
    protected IOnChildClickWithTypeListener typeListener;

    public CommonAdapter() {
    }

    public CommonAdapter(Context context) {
        this.mContext = context;
        list = new ArrayList<T>();
    }

    public void invalidateData(List<T> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获得inflate后的view
     *
     * @param resource
     * @return
     */
    public View getInflateView(int resource) {
        convertView = LayoutInflater.from(mContext).inflate(resource, null);
        return convertView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        return convertView;
    }

    /**
     * @param convertView
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public <V extends View> V getElement(int id) {
        return (V) convertView.findViewById(id);
    }

    public int getResourceColor(int id) {
        return mContext.getResources().getColor(id);
    }

    public static boolean isNullString(String str) {
        return TextUtils.isEmpty(str) || str.equals("null");
    }

    public interface IOnChildClickListener<W> {
        public void onChildClickListener(W target);
    }

    public void setOnChildClickListener(IOnChildClickListener listener) {
        this.listener = listener;
    }

    protected void setOnChildClickListener(View view, final Object target) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onChildClickListener(target);
                } else {
                    Log.e("Error", "CommonAdapter Child Listener Not Null");
                }
            }
        });
    }

    public interface IOnChildClickWithTypeListener<T, W> {
        public void onChildClickWithTypeListener(T type, W target);
    }

    public void setOnChildClickWithTypeListener(IOnChildClickWithTypeListener listener) {
        this.typeListener = listener;
    }

    protected void setOnChildClickWithTypeListener(View view, final Object type, final Object target) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != typeListener) {
                    typeListener.onChildClickWithTypeListener(type, target);
                } else {
                    Log.e("Error", "CommonAdapter Child TypeListener Not Null");
                }
            }
        });
    }
}
