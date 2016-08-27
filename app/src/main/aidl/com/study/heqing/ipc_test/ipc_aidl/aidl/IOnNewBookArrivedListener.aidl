// IOnNewBookArrivedListener.aidl
package com.study.heqing.ipc_test.ipc_aidl.aidl;

import com.study.heqing.ipc_test.ipc_aidl.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
