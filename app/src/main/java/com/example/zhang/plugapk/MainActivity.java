package com.example.zhang.plugapk;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends BaseActivity {

    private static final String TAG = "Client-MainActivity";
    private AssetManager mAssetManager;
    private Resources mResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createResources(DEX_PATH);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
    //    mProxyActivity.setContentView(gennerateContentView(mProxyActivity));
        if(mProxyActivity == this)
        {
            setContentView(R.layout.activity_main);
        }else
        {
            Resources resources = getResources();
            resources.getIdentifier("activity_main","","com.example.zhang.plugapk");

        }
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

    private void createResources(String dexPath)
    {
        try {
            mAssetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getDeclaredMethod("addAssetPath", new Class[]{});
            addAssetPath.setAccessible(true);
            addAssetPath.invoke(mAssetManager,dexPath);
            mResources = new Resources(mAssetManager,
                    mProxyActivity.getResources().getDisplayMetrics(),
                    mProxyActivity.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {

        return mResources==null?super.getResources():mResources;
    }
}
