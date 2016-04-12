package com.example.zhang.plugapk;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private static final String TAG = "Client-MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        mProxyActivity.setContentView(gennerateContentView(mProxyActivity));
    }

    private View gennerateContentView(final Activity mProxyActivity) {

        LinearLayout layout = new LinearLayout(mProxyActivity);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        layout.setBackgroundColor(Color.parseColor("#F79AB5"));
        Button button = new Button(mProxyActivity);
        button.setText("button");
        layout.addView(button, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mProxyActivity, "you clicked button",
                        Toast.LENGTH_SHORT).show();
                startActivityByProxy("com.example.zhang.plugapk.PluginTextActivity");
            }
        });
        return layout;
    }
}
