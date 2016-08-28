package com.study.heqing.ipc_test.ipc_binder_pool;

import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.study.heqing.ipc_test.R;

/**
 * Created by HeQing on 2016/8/28 0028.
 */
public class BinderPoolActivity extends Activity{
    private static final String TAG = "BinderPoolActivity";

    private ISecurityCenter mSecurityCenter;
    private IComputer mComputer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);
        new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
            }
        }).start();
    }

    private void doWork(){
        BinderPool binderPool = BinderPool.getInstance(BinderPoolActivity.this);
        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);

        mSecurityCenter = SecurityCenterImpl.asInterface(securityBinder);
        String msg = "helloworld 安卓";
        try {
            String password = mSecurityCenter.encrypt(msg);
            Log.i(TAG,"加密："+password);
            Log.i(TAG,"解密："+mSecurityCenter.decrypt(password));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        IBinder computerBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        mComputer = ComputerImpl.asInterface(computerBinder);
        try {
            Log.i(TAG,"计算3+5 = "+mComputer.add(3,5));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

}
