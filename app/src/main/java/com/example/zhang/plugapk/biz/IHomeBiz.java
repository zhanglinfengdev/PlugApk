package com.example.zhang.plugapk.biz;

import android.content.Context;

import com.example.zhang.plugapk.bean.Contact;

import java.util.List;

/**
 * Created by zhang on 16-4-14.
 */
public interface IHomeBiz {
    public List<Contact> getContactData(Context context);
}
