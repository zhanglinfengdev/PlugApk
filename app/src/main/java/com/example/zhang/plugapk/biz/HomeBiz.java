package com.example.zhang.plugapk.biz;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.zhang.plugapk.bean.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 16-4-13.
 */
public class HomeBiz implements IHomeBiz{

    public List<Contact> getContactData(Context context)
    {
        List<Contact> ret = new ArrayList<Contact>();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor query = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME},
                null, null, null);
        while(query.moveToNext())
        {
            Contact contact = new Contact();

            //得到id和名字
            String id = query.getString(query.getColumnIndex(ContactsContract.Contacts._ID));
            String name = query.getString(query.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            contact.setName(name);


            //得到phone
            Cursor phone_c = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Email.DATA},
                    ContactsContract.CommonDataKinds.Phone._ID+"=?",
                    new String[]{id}, null);
            while(phone_c.moveToNext())
            {
                String phone = phone_c.getString(phone_c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));
                contact.setPhone(phone);
            }


            //得到email
            Cursor email_c = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Email.DATA},
                    ContactsContract.CommonDataKinds.Email._ID + "=?",
                    new String[]{id}, null);
            while(email_c.moveToNext())
            {
                String email = email_c.getString(email_c.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                contact.setEmail(email);
            }
            ret.add(contact);
        }
        return ret;
    }
}
