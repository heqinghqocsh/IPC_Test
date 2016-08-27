package com.study.heqing.ipc_test.ipc_messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.study.heqing.ipc_test.utils.MyConstants;


/**
 * Created by HeQing on 2016/8/27 0027.
 * 使用Messenger进行IPC,服务端service
 */
public class MessengerService extends Service{
    private static final String TAG = "MessengerService";

    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MyConstants.MSG_FROM_CLIENT:
                    Log.i(TAG,"receive msg from Client: "+msg.getData().getString("msg"));
                    Messenger client = msg.replyTo;
                    Message replyMsg = Message.obtain(null,MyConstants.MSG_FROM_SERVER);
                    Bundle data = new Bundle();
                    data.putString("reply","你好，我已经接收到了你的消息");
                    replyMsg.setData(data);
                    try {
                        client.send(replyMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
