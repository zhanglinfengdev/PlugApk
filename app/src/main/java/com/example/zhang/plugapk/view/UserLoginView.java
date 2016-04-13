package com.example.zhang.plugapk.view;

/**
 * Created by zhang on 16-4-13.
 */
public interface UserLoginView {

    public void showToast(String msg);
    public void showProgressBar();
    public void hideProgressBar();
    public String getUsername();
    public String getPassword();
    public void setLoginBtnState(String msg);
    public void toHomeActivity();
    public void showLoginBacground(boolean flag);

}
