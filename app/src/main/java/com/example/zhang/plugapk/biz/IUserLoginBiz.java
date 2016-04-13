package com.example.zhang.plugapk.biz;

import com.example.zhang.plugapk.presenter.CallBack;

/**
 * Created by zhang on 16-4-13.
 */
public interface IUserLoginBiz {

    public void userLogin( String username, String password, CallBack callBack );
    public void userLogout( CallBack callback );

}
