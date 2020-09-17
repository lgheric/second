package com.example.second;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

public class MyAdapter extends BaseAdapter {
    private LinkedList<Data> mData;
    private LayoutInflater inflater;

    public MyAdapter() {}

    public MyAdapter(Context context, LinkedList<Data> mData) {
        this.mData = mData;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item,parent,false);
            holder = new ViewHolder();
            holder.img_icon = convertView.findViewById(R.id.img_icon);
            holder.txt_content = convertView.findViewById(R.id.txt_content);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.img_icon.setImageResource(mData.get(position).getImgId());
        holder.txt_content.setText(mData.get(position).getContent());
        return convertView;
    }

    public void add(Data data) {
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }

    //往特定位置，添加一个元素
    public void add(int position,Data data){
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.add(position,data);
        notifyDataSetChanged();
    }

    public void remove(Data data) {
        if(mData != null) {
            mData.remove(data);
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if(mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }

    public void removeAll() {
        if(mData != null) {
            mData.clear();
        }
        notifyDataSetChanged();
    }

    public void update(int position, Data mData, ListView listview){
        //可视区域的第一个item的index(index可能是0也可能是其它)
        int firstVisiblePosition = listview.getFirstVisiblePosition();

        //可视区域的最后一个item的index
        int lastVisiblePosition = listview.getLastVisiblePosition();

        //如果更新的数据位于可见区域则立即更新，否则由getView()更新
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            //可视区域的第几个item(不管listView滑动到哪,可见的第一个item从0开始算起)
            View v = listview.getChildAt(position - firstVisiblePosition);

//            ImageView img = v.findViewById(R.id.img_icon);
//            TextView tv = v.findViewById(R.id.txt_content);
//            img.setImageResource(mData.getImgId());
//            tv.setText(mData.getContent());

            ViewHolder holder = (ViewHolder) v.getTag();
            holder.img_icon.setImageResource(mData.getImgId());
            holder.txt_content.setText(mData.getContent());

        }
    }

    private static class ViewHolder{
        ImageView img_icon;
        TextView txt_content;
    }

}
