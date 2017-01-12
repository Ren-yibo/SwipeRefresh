package com.swipe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.swipe.R;

/**
 * @author 任益波
 * @date 2017/1/11
 * @description
 */
public class ExpandAdapter extends CommonExpandAdapter<String, String> {

    public ExpandAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder mGroup;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expand_group, null);
            mGroup = new GroupViewHolder();
            mGroup.mGroupIv = (ImageView) convertView.findViewById(R.id.expandGroupIv);
            mGroup.mGroupTv = (TextView) convertView.findViewById(R.id.expandGroupTv);
            convertView.setTag(mGroup);
        } else {
            mGroup = (GroupViewHolder) convertView.getTag();
        }
        mGroup.mGroupIv.setSelected(isExpanded);
        mGroup.mGroupTv.setText("这是第" + (groupPosition + 1) + "条Group~");
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder mChild;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expand_child, null);
            mChild = new ChildViewHolder();
            mChild.mChildTv = (TextView) convertView.findViewById(R.id.item_expand_child);
            convertView.setTag(mChild);
        } else {
            mChild = (ChildViewHolder) convertView.getTag();
        }
        mChild.mChildTv.setText("这是第" + (childPosition + 1) + "条Child");
        return convertView;
    }

    private class GroupViewHolder {
        public ImageView mGroupIv;
        public TextView mGroupTv;
    }

    private class ChildViewHolder {
        public TextView mChildTv;
    }
}
