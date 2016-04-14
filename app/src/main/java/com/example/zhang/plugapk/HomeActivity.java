package com.example.zhang.plugapk;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zhang.plugapk.bean.Contact;
import com.example.zhang.plugapk.presenter.HomePresenter;
import com.example.zhang.plugapk.view.HomeView;

import java.util.List;

/**
 * Created by zhang on 16-4-11.
 */
public class HomeActivity extends BaseActivity implements HomeView {
    private ListView listView;
    private List<Contact> mContacts;
    private ContactAdapter adapter;
    private HomePresenter mHomePresenter;
    private Button btn_loadData;
    private Button btn_refreshData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomePresenter = new HomePresenter(this);
        initView();
        initListener();
    }

    private void initListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog(mContacts.get(i));
                return true;
            }
        });

        btn_loadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHomePresenter.setListData(mProxyActivity);
            }
        });
        btn_refreshData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHomePresenter.refreshListData(mProxyActivity);
            }
        });
    }

    private void initView() {
        Resources resources = super.getResources();
        XmlResourceParser layout = resources.getLayout(resources.getIdentifier("activity_home", "layout", "com.example.zhang.plugapk"));
        View inflate = LayoutInflater.from(mProxyActivity).inflate(layout, null);
        setContentView(inflate);
        listView = ((ListView) inflate.findViewById(resources.getIdentifier("lv_plugin_showContact", "id", "com.example.zhang.plugapk")));
        btn_loadData = ((Button) inflate.findViewById(resources.getIdentifier("btn_homeview_loadData", "id", "com.example.zhang.plugapk")));
        btn_refreshData = ((Button) inflate.findViewById(resources.getIdentifier("btn_homeview_refreshData", "id", "com.example.zhang.plugapk")));
    }


    @Override
    public void setListData(List<Contact> data) {
        this.mContacts = data;
        adapter = new ContactAdapter(this.mContacts, mProxyActivity);
        listView.setAdapter(adapter);
    }

    @Override
    public void refreshListData(List<Contact> data) {
        this.mContacts = data;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showDialog(final Contact contact) {
        final String[] options = {"拨打电话", "发送短信"};
        AlertDialog ad = null;
        final AlertDialog finalAd = ad;
        ad = new AlertDialog.Builder(mProxyActivity)
                .setTitle("操作选项")
                .setAdapter(new ArrayAdapter<String>(mProxyActivity, android.R.layout.simple_list_item_1, options),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i) {
                                    case 0:
                                        showToast(options[0]);
                                        Intent intent = new Intent();
                                        intent.setAction(Intent.ACTION_CALL);
                                        intent.setData(Uri.parse("tel:"+contact.getPhone()));
                                        mProxyActivity.startActivity(intent);
                                        break;
                                    case 1:
                                        showToast(options[1]);
                                        Intent intent1 = new Intent();
                                        intent1.setAction(Intent.ACTION_SENDTO);
                                        intent1.setData(Uri.parse("smsto:"+contact.getPhone()));
                                        intent1.putExtra("sms_body", "继续输入短信内容");
                                        mProxyActivity.startActivity(intent1);
                                        break;
                                }
                            }
                        }).create();
        ad.show();
        ad.setCanceledOnTouchOutside(true);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mProxyActivity, msg, Toast.LENGTH_SHORT).show();
    }

}
