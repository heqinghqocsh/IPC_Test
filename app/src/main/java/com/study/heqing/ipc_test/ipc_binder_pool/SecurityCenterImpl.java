package com.study.heqing.ipc_test.ipc_binder_pool;

import android.os.RemoteException;

/**
 * Created by HeQing on 2016/8/28 0028.
 */
public class SecurityCenterImpl extends ISecurityCenter.Stub{
    public static final char SECRET_CODE = '^';

    @Override
    public String encrypt(String content) throws RemoteException {
        char[] chars = content.toCharArray();
        for (int i = 0;i < chars.length;i++){
            chars[i] ^= SECRET_CODE;
        }
        return new String(chars);
    }

    @Override
    public String decrypt(String password) throws RemoteException {
        return encrypt(password);
    }
}
