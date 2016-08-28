package com.study.heqing.ipc_test.ipc_binder_pool;

import android.os.RemoteException;

/**
 * Created by HeQing on 2016/8/28 0028.
 */
public class ComputerImpl extends IComputer.Stub{

    @Override
    public int add(int a, int b) throws RemoteException {
        return a+b;
    }
}
