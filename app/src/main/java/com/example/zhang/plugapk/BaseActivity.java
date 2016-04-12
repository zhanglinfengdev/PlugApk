package com.example.zhang.plugapk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhang on 16-4-11.
 */
public class BaseActivity extends Activity {


    private static final String TAG = "Client-BaseActivity";

    public static final String FROM = "extra.from";
    public static final int FROM_EXTERNAL = 0;
    public static final int FROM_INTERNAL = 1;
    public static final String EXTRA_DEX_PATH = "extra.dex.path";
    public static final String EXTRA_CLASS = "extra.class";

    public static final String PROXY_VIEW_ACTION =
            "com.ryg.dynamicloadhost.VIEW";
    public static final String DEX_PATH = "/mnt/sdcard/DynamicLoadPlug/app-debug.apk";

    protected Activity mProxyActivity;
    protected int mFrom = FROM_INTERNAL;

    public void setProxy(Activity proxyActivity)
    {
        Log.d(TAG,"setProxy: proxyAcitvity= "+proxyActivity);
        this.mProxyActivity = proxyActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null)
        {
            mFrom = savedInstanceState.getInt(FROM,FROM_INTERNAL);
        }
        if(mFrom == FROM_INTERNAL)
        {
            super.onCreate(savedInstanceState);
            mProxyActivity = this;
        }

        Log.d(TAG,"onCreate: from= "+mFrom);

    }

    @Override
    protected void onStart() {
        if(mProxyActivity == this)
        {
            super.onStart();
        }
        Log.d("flag","插件得onStart被执行了");
    }

    @Override
    protected void onRestart() {
        if(mProxyActivity == this)
        {
            super.onRestart();
        }

        Log.d("flag","插件得OnRestart被执行了");
    }

    @Override
    protected void onResume() {
        if(mProxyActivity == this)
        {
            super.onResume();
        }
        Log.d("flag","插件得onResume被执行了");
    }

    @Override
    protected void onPause() {
        if(mProxyActivity == this)
        {
            super.onPause();
        }
        Log.d("flag","插件得onPause被执行了");
    }

    @Override
    protected void onStop() {
        if(mProxyActivity == this)
        {
            super.onStop();
        }
        Log.d("flag","插件得onStop被执行了");
    }

    @Override
    protected void onDestroy() {
        if(mProxyActivity == this)
        {
            super.onDestroy();
        }
        Log.d("flag","插件得onDestroy被执行了");
    }

    protected void startActivityByProxy(String className)
    {
        if(mProxyActivity == this)
        {
            Intent intent = new Intent();
            intent.setClassName(this,className);
            this.startActivity(intent);
        }else {
            Intent intent = new Intent(PROXY_VIEW_ACTION);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.putExtra(EXTRA_DEX_PATH,DEX_PATH);
            intent.putExtra(EXTRA_CLASS,className);
            Log.d(TAG,className);
           mProxyActivity.startActivity(intent);
        }
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if(mProxyActivity == this)
        {
            super.setContentView(view,params);
        }else {
            mProxyActivity.setContentView(view,params);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if(mProxyActivity == this)
        {
            super.setContentView(layoutResID);
        }else
        {
            mProxyActivity.setContentView(layoutResID);
        }
    }

    @Override
    public void setContentView(View view) {
        if(mProxyActivity == this)
        {
            super.setContentView(view);
        }else
        {
            mProxyActivity.setContentView(view);
        }
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        if(mProxyActivity == this)
        {
            super.addContentView(view,params);
        }else
        {
            mProxyActivity.addContentView(view,params);
        }
    }
}
