// IBookManager.aidl
package com.study.heqing.ipc_test.ipc_aidl.aidl;

import com.study.heqing.ipc_test.ipc_aidl.aidl.Book;
import com.study.heqing.ipc_test.ipc_aidl.aidl.IOnNewBookArrivedListener;

interface IBookManager {

    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unRegisterListener(IOnNewBookArrivedListener listener);
}
