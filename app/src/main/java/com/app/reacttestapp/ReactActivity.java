package com.app.reacttestapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

/**
 * Created by evangelinewong on 2018/6/25.
 */

public class ReactActivity extends Activity implements DefaultHardwareBackBtnHandler {

    private ReactRootView reactRootView;
    private ReactInstanceManager reactInstanceManager;


    public static void startActivity(Context context){
        Intent it = new Intent(context,ReactActivity.class);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            reactRootView = new ReactRootView(this);
            reactInstanceManager = ReactInstanceManager.builder()
                    .setApplication(getApplication())
                    .setBundleAssetName("index.android.bundle")
                    .addPackage(new MainReactPackage())
                    .setJSMainModulePath("index.android")
//                    .setJSMainModuleName("index.android")
                    .setUseDeveloperSupport(BuildConfig.DEBUG)
                    .setInitialLifecycleState(LifecycleState.RESUMED)
                    .build();


            reactRootView.startReactApplication(reactInstanceManager, "MyReactNativeApp", null);
            setContentView(reactRootView);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    @Override
    public void invokeDefaultOnBackPressed() {
        if(reactInstanceManager!=null){
            reactInstanceManager.onBackPressed();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_MENU && reactInstanceManager!=null){
            reactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(reactInstanceManager !=null){
            reactInstanceManager.onHostPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(reactInstanceManager!=null){
            reactInstanceManager.onHostResume(this,this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(reactInstanceManager!=null){
            reactInstanceManager.onHostDestroy();
        }
    }
}
