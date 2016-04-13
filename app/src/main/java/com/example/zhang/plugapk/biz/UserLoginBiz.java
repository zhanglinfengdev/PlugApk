package com.example.zhang.plugapk.biz;

import android.os.Environment;
import android.text.TextUtils;

import com.example.zhang.plugapk.bean.User;
import com.example.zhang.plugapk.presenter.CallBack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.Properties;

/**
 * Created by zhang on 16-4-13.
 */
public class UserLoginBiz implements IUserLoginBiz {

    private String USER_NAME = "userName";
    private String USER_PASSWORD = "userPassword";
    private String USER_ID = "userID";
    private User currentUser = null;
    private File target;
    private Properties properties;
    private MessageDigest md;

    public UserLoginBiz()
    {
        try {
            properties = new Properties();
            md = MessageDigest.getInstance("MD5");
                target = new File(Environment.getExternalStorageDirectory(),"userInfo");
                if(!target.exists())
                    target.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void userLogin(final String username, final String password, final CallBack callBack) {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //模拟根据userId判断用户是否已经在线
        if(currentUser != null && TextUtils.equals(String.valueOf(md.digest(user.getUsername().getBytes())),currentUser.get_id()))
        {
            callBack.onSuccess("您已经在线，无需重复登陆");
            return;
        }

        new Thread(){
            @Override
            public void run() {
                //模拟耗时操作
                try {Thread.sleep(2000);} catch (InterruptedException e) { e.printStackTrace(); }

                if(TextUtils.equals(user.getUsername(),"linfeng") && TextUtils.equals(user.getPassword(),"123456"))
                {
                    try {
                            currentUser = user;
                            callBack.onSuccess("登陆成功");
                            properties.load(new FileInputStream(target));
                            properties.put(USER_NAME,user.getUsername());
                            properties.put(USER_PASSWORD,user.getPassword());
                            byte[] digest = md.digest(user.getUsername().getBytes());
                            //模拟userId
                            user.set_id(String.valueOf(digest));
                            properties.put(USER_ID,user.get_id());
                            properties.store(new FileOutputStream(target),"by:fengge");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else
                {
                    callBack.onFailure(" error: please check username and password ! ");
                }

            }
        }.start();

    }



    @Override
    public void userLogout(CallBack callBack) {
        currentUser = null;
        if(currentUser == null)
        {
            callBack.onSuccess("登出成功！");
        }else
        {
            callBack.onFailure("登出失败!");
        }
    }
}
