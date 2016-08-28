// ISecurityCenter.aidl
package com.study.heqing.ipc_test.ipc_binder_pool;


interface ISecurityCenter {

    String encrypt(String content);
    String decrypt(String password);

}
