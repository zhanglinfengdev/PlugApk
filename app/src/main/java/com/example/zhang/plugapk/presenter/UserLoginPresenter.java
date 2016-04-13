package com.example.zhang.plugapk.presenter;

import android.os.Handler;

import com.example.zhang.plugapk.biz.IUserLoginBiz;
import com.example.zhang.plugapk.biz.UserLoginBiz;
import com.example.zhang.plugapk.view.UserLoginView;

/**
 * Created by zhang on 16-4-13.
 */
public class UserLoginPresenter {

    private UserLoginView mUserLoginView;
    private IUserLoginBiz mUserLoginBiz;
    private Handler handler = new Handler();

    public UserLoginPresenter(UserLoginView mUserLoginView) {
        this.mUserLoginView = mUserLoginView;
        mUserLoginBiz = new UserLoginBiz();
    }

    public void login(String username, String password)
    {
        mUserLoginView.showLoginBacground(true);
        mUserLoginView.showProgressBar();
        mUserLoginView.setLoginBtnState("登陆中......");
        mUserLoginBiz.userLogin(username, password, new CallBack() {
            @Override
            public void onSuccess(final String msg) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mUserLoginView.showLoginBacground(false);
                        mUserLoginView.hideProgressBar();
                        mUserLoginView.setLoginBtnState("LOGIN");
                        mUserLoginView.showToast(msg);
                        mUserLoginView.toHomeActivity();
                    }
                });


            }

            @Override
            public void onFailure(final String msg) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mUserLoginView.showLoginBacground(false);
                        mUserLoginView.hideProgressBar();
                        mUserLoginView.setLoginBtnState("LOGIN");
                        mUserLoginView.showToast(msg);
                    }
                });


            }
        });
    }

    public void logout()
    {
        mUserLoginBiz.userLogout(new CallBack() {
            @Override
            public void onSuccess(String msg) {
                mUserLoginView.showToast(msg);

            }

            @Override
            public void onFailure(String msg) {
                mUserLoginView.showToast(msg);

            }
        });
    }




}
