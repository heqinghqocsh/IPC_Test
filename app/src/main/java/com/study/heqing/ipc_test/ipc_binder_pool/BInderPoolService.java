package com.study.heqing.ipc_test.ipc_binder_pool;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by HeQing on 2016/8/28 0028.
 */
public class BInderPoolService extends Service{
    private static final String TAG = "BInderPoolService";

    private Binder mBinderPool = new BinderPool.BinderPoolImpl();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinderPool;
    }
}
