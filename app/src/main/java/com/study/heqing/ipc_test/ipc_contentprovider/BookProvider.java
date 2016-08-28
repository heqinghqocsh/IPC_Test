package com.study.heqing.ipc_test.ipc_contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by HeQing on 2016/8/28 0028.
 */
public class BookProvider extends ContentProvider{
    private static final String TAG = "BookProvider";

    @Override
    public boolean onCreate() {
        Log.i(TAG,"onCreate ,current thread : "+Thread.currentThread().getName());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Log.i(TAG,"query ,current thread : "+Thread.currentThread().getName());
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.i(TAG,"getType ,current thread : "+Thread.currentThread().getName());
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        Log.i(TAG,"insert ,current thread : "+Thread.currentThread().getName());
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        Log.i(TAG,"delete ,current thread : "+Thread.currentThread().getName());
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        Log.i(TAG,"update ,current thread : "+Thread.currentThread().getName());
        return 0;
    }
}
