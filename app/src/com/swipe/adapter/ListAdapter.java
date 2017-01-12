package com.swipe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swipe.R;

/**
 * @author 任益波
 * @date 2017/1/10
 * @description
 */
public class ListAdapter extends CommonAdapter<String> {

    public ListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (null == convertView) {
            convertView = getInflateView(R.layout.item_list);
            holder = new ViewHolder();
            holder.mItem = getElement(R.id.item_list);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mItem.setText("这是第" + (position + 1) + "条数据~");
        return super.getView(position, convertView, parent);
    }

    private class ViewHolder {
        public TextView mItem;
    }
}
