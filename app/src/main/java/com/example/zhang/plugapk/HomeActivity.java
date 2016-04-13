package com.example.zhang.plugapk;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zhang.plugapk.bean.Contact;

import java.util.List;

/**
 * Created by zhang on 16-4-11.
 */
public class HomeActivity extends BaseActivity {
    private ListView listView;
    private List<Contact> mContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        XmlResourceParser layout = super.getResources().getLayout(mResources.getIdentifier("activity_home", "layout", "com.example.zhang.plugapk"));
        View inflate = LayoutInflater.from(mProxyActivity).inflate(layout, null);
        setContentView(inflate);
        listView = ((ListView) inflate.findViewById(mResources.getIdentifier("lv_plugin_showContact", "id", "com.example.zhang.plugapk")));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
