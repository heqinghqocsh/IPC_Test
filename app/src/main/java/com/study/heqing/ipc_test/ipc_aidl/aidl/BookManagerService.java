package com.study.heqing.ipc_test.ipc_aidl.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by HeQing on 2016/8/27 0027.
 */
public class BookManagerService extends Service{
    private static final String TAG = "BookManagerService";

    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<>();


    private Binder mBinder = new IBookManager.Stub(){
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListenerList.register(listener);
        }

        @Override
        public void unRegisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListenerList.unregister(listener);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1,"Android",45.9f));
        mBookList.add(new Book(2,"iOS",45.9f));
        new Thread(new ServiceWorker()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private void onNewBookArrived(Book book) throws RemoteException{
        mBookList.add(book);
        final int N = mListenerList.beginBroadcast();
        for (int i = 0;i < N;i++){
            IOnNewBookArrivedListener listener = mListenerList.getBroadcastItem(i);
            if (listener != null){
                listener.onNewBookArrived(book);
            }
        }
        mListenerList.finishBroadcast();
    }


    private class ServiceWorker implements Runnable{
        @Override
        public void run() {
            while (!mIsServiceDestoryed.get()){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size()+1;
                Book book = new Book(bookId,"new Book #"+bookId,49.5f+bookId);
                try {
                    onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
