package com.swipe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

/**
 * @author 任益波
 * @date 2016/11/23
 * @description
 */
public class CommonExpandAdapter<T, W> extends BaseExpandableListAdapter {

    protected Context mContext;
    protected List<T> mGroupList;
    protected List<List<W>> mChildList;

    public CommonExpandAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void invalidateData(List<T> mGroup, List<List<W>> mChild) {
        this.mGroupList = mGroup;
        this.mChildList = mChild;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return mGroupList == null ? 0 : mGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildList.get(groupPosition) == null ? 0 : mChildList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList == null ? null : mGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
