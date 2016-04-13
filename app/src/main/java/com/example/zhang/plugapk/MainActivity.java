package com.example.zhang.plugapk;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zhang.plugapk.presenter.UserLoginPresenter;
import com.example.zhang.plugapk.view.UserLoginView;

public class MainActivity extends BaseActivity implements UserLoginView{

    private static final String TAG = "Client-MainActivity";

    private View layout_root;
    private EditText et_username;
    private EditText et_password;
    private ProgressBar pb_progress;
    private Button btn_login;

    private UserLoginPresenter mUserLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(savedInstanceState);
        initListener();

        mUserLoginPresenter = new UserLoginPresenter(this);
    }

    private void initListener() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserLoginPresenter.login(et_username.getText().toString(),
                        et_password.getText().toString());
            }
        });
    }

    private void initView(Bundle savedInstanceState) {
        int identifier = super.getResources().getIdentifier("activity_main","layout","com.example.zhang.plugapk");
        Resources resources = super.getResources();
        XmlResourceParser layout = resources.getLayout(identifier);
        LayoutInflater from = LayoutInflater.from(mProxyActivity);
        View rootLayout = from.inflate(layout, null);
        setContentView(rootLayout);

        layout_root = rootLayout.findViewById(resources.getIdentifier("layout_loginUI_root","id","com.example.zhang.plugapk"));
        et_username = (EditText) rootLayout.findViewById(resources.getIdentifier("et_loginUI_inputName","id","com.example.zhang.plugapk"));
        et_password = (EditText) rootLayout.findViewById(resources.getIdentifier("et_loginUI_inputPass","id","com.example.zhang.plugapk"));
        btn_login = (Button) rootLayout.findViewById(resources.getIdentifier("btn_loginUI_login","id","com.example.zhang.plugapk"));
        pb_progress = ((ProgressBar) rootLayout.findViewById(resources.getIdentifier("pb_loginUI_progress", "id", "com.example.zhang.plugapk")));
    }



    @Override
    public void showProgressBar() {
        pb_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pb_progress.setVisibility(View.GONE);
    }

    @Override
    public String getUsername() {
        return et_username.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_password.getText().toString();
    }

    @Override
    public void setLoginBtnState(String msg) {
        btn_login.setText(msg);
    }

    @Override
    public void toHomeActivity() {
        startActivityByProxy("com.example.zhang.plugapk.PluginTextActivity");
    }

    @Override
    public void showLoginBacground(boolean flag) {
        if(flag)
        {
            layout_root.setBackgroundColor(Color.argb(88,0,0,0));
        }else
        {
            layout_root.setBackgroundColor(Color.WHITE);
        }

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }









//    private View gennerateContentView(final Activity mProxyActivity) {
//
//        LinearLayout layout = new LinearLayout(mProxyActivity);
//        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT));
//        layout.setBackgroundColor(Color.parseColor("#F79AB5"));
//        Button button = new Button(mProxyActivity);
//        button.setText("button");
//        layout.addView(button, LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mProxyActivity, "you clicked button",
//                        Toast.LENGTH_SHORT).show();
//                startActivityByProxy("com.example.zhang.plugapk.PluginTextActivity");
//            }
//        });
//
//
//        return layout;
//    }
}
