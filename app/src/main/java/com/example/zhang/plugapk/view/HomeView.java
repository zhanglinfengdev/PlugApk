package com.example.zhang.plugapk.view;

import com.example.zhang.plugapk.bean.Contact;

import java.util.List;

/**
 * Created by zhang on 16-4-13.
 */
public interface HomeView {

    public void setListData(List<Contact> data);
    public void refreshListData(List<Contact> data);
    public void showDialog(Contact contact);
    public void showToast(String msg);
}
