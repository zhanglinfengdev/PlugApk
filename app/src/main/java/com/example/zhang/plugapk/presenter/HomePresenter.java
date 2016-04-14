package com.example.zhang.plugapk.presenter;

import android.content.Context;

import com.example.zhang.plugapk.bean.Contact;
import com.example.zhang.plugapk.biz.HomeBiz;
import com.example.zhang.plugapk.biz.IHomeBiz;
import com.example.zhang.plugapk.view.HomeView;

import java.util.List;

/**
 * Created by zhang on 16-4-13.
 */
public class HomePresenter {

    private HomeView mHomeView;
    private IHomeBiz mHomeBiz;

    public HomePresenter(HomeView mHomeView ){
        this.mHomeView = mHomeView;
        this.mHomeBiz = new HomeBiz();
    }

    public void setListData(Context context)
    {
        List<Contact> contactData = this.mHomeBiz.getContactData(context);
        mHomeView.setListData(contactData);
        mHomeView.showToast("load finish!");
    }

    public void refreshListData(Context context)
    {
        setListData(context);
        this.mHomeView.showToast("refresh finish!");
    }


}
