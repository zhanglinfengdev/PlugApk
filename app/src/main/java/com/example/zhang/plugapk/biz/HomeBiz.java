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

    @Override
    public List<Contact> getContactData(Context context)
    {
        List<Contact> ret = new ArrayList<Contact>();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor query = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID,
                },
                null, null, null);
        while(query.moveToNext())
        {
            Contact contact = new Contact();
            String contact_id = query.getString(query.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor rawContact = contentResolver.query(ContactsContract.RawContacts.CONTENT_URI, new String[]{ContactsContract.RawContacts._ID},
                    ContactsContract.RawContacts.CONTACT_ID + "=?",
                    new String[]{contact_id}, null
            );
            while(rawContact.moveToNext())
            {
                String raw_id = rawContact.getString(rawContact.getColumnIndex(ContactsContract.RawContacts._ID));
                Cursor phone_c = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID + "=?",
                        new String[]{raw_id}, null);
                while(phone_c.moveToNext())
                {
                    String phone = phone_c.getString(phone_c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));
                    String name = phone_c.getString(phone_c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    contact.setName(name);
                    contact.setPhone(phone);
                }
                phone_c.close();


                Cursor email_c = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Email.RAW_CONTACT_ID + "=?",
                        new String[]{raw_id}, null
                );
                while(email_c.moveToNext())
                {
                    String email = email_c.getString(email_c.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    contact.setEmail(email);
                }
                email_c.close();

            }
            rawContact.close();
            ret.add(contact);
        }
        query.close();
        return ret;
    }


    @Deprecated
    public List<Contact> getContactData_1(Context context) {
        List<Contact> ret = new ArrayList<Contact>();

        ContentResolver contentResolver = context.getContentResolver();
        Cursor query = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID}, null, null, null
        );
        while(query.moveToNext())
        {
            Contact contact = new Contact();

            String name = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            long raw_contact_id = query.getLong(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID));

            Cursor email = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    new String[]{
                            ContactsContract.CommonDataKinds.Email.DATA
                    }, ContactsContract.CommonDataKinds.Email.RAW_CONTACT_ID + "=?",
                    new String[]{raw_contact_id + ""}, null
            );
            while(email.moveToNext())
            {
                String data = email.getString(email.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                contact.setEmail(data);
            }
            email.close();

            contact.setName(name);
            contact.setPhone(number);
            ret.add(contact);
        }
        query.close();
        return ret;
    }
}
