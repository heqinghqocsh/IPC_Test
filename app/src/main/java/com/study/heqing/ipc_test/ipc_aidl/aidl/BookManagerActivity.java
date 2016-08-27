package com.study.heqing.ipc_test.ipc_aidl.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.widget.ListView;

import com.study.heqing.ipc_test.BookAdapter;
import com.study.heqing.ipc_test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeQing on 2016/8/27 0027.
 */
public class BookManagerActivity extends Activity{
    private static final String TAG = "BookManagerActivity";
    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;

    private IBookManager mRemoteBookManager;

    private ListView mListView;
    private BookAdapter mAdapter;
    private List<Book> mBookList = new ArrayList<>();

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_NEW_BOOK_ARRIVED:
                    mBookList.add((Book) msg.obj);
                    mAdapter.notifyDataSetChanged();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mRemoteBookManager = IBookManager.Stub.asInterface(iBinder);
            try {
                List<Book> list = mRemoteBookManager.getBookList();
                mBookList.addAll(list);
                mAdapter.notifyDataSetChanged();
                mRemoteBookManager.registerListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mRemoteBookManager = null;
        }
    };

    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub(){
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            //注意：此方法运行在客户端的binder线程池中，不能直接操作UI
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED,newBook).sendToTarget();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        initView();
        Intent intent = new Intent(this,BookManagerService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);
    }

    private void initView(){
        mListView = (ListView) findViewById(R.id.book_list);
        mAdapter = new BookAdapter(this,mBookList);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()){
            try {
                mRemoteBookManager.unRegisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mConnection);
        super.onDestroy();
    }
}
