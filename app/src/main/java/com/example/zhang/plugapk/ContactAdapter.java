package com.example.zhang.plugapk;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zhang.plugapk.bean.Contact;

import java.util.List;

/**
 * Created by zhang on 16-4-13.
 */
public class ContactAdapter extends BaseAdapter{

    private List<Contact> data;
    private Context context;

    public ContactAdapter(List<Contact> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null)
        {
            XmlResourceParser layout = context.getResources().getLayout(context.getResources().getIdentifier("listview_item", "layout", "com.example.zhang.plugapk"));
            view = LayoutInflater.from(context).inflate(layout, null);
            holder = new ViewHolder();
            holder.mName = (TextView) view.findViewById(context.getResources().getIdentifier("tv_home_showName","id","com.example.zhang.plugapk"));
            holder.mPhone = (TextView) view.findViewById(context.getResources().getIdentifier("tv_home_showPhone","id","com.example.zhang.plugapk"));
            holder.email = (TextView) view.findViewById(context.getResources().getIdentifier("tv_home_showEmail", "id", "com.example.zhang.plugapk"));
            view.setTag(holder);
        }else
        {
            holder = (ViewHolder) view.getTag();
        }
        holder.mName.setText(String.format("联系人姓名 : %s",data.get(i).getName()));
        holder.mPhone.setText(String.format("联系人电话 : %s",data.get(i).getPhone()));
        holder.email.setText(String.format("联系人邮箱 : %s",data.get(i).getEmail()));
        return view;
    }

    private static class ViewHolder
    {
        private TextView mName;
        private TextView mPhone;
        private TextView email;
    }
}
