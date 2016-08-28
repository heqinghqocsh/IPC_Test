package com.study.heqing.ipc_test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.study.heqing.ipc_test.R;
import com.study.heqing.ipc_test.ipc_aidl.aidl.BookManagerActivity;
import com.study.heqing.ipc_test.ipc_contentprovider.ProviderActivity;
import com.study.heqing.ipc_test.ipc_messenger.MessengerActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View v){
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.messenger_ipc:
                intent.setClass(this, MessengerActivity.class);
                startActivity(intent);
                break;
            case R.id.messenger_aidl:
                intent.setClass(this, BookManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.ipc_provider:
                intent.setClass(this, ProviderActivity.class);
                startActivity(intent);
                break;
        }
    }

}
