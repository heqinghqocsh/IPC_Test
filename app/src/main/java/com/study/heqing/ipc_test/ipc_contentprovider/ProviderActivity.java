package com.study.heqing.ipc_test.ipc_contentprovider;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;

import com.study.heqing.ipc_test.R;

/**
 * Created by HeQing on 2016/8/28 0028.
 */
public class ProviderActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_provider);
        Uri uri = Uri.parse("content://com.study.heqing.ipc_test.book.provider");
        getContentResolver().query(uri,null,null,null,null);
        ContentValues contentValues = new ContentValues();
        getContentResolver().insert(uri,contentValues);
        getContentResolver().update(uri,contentValues,null,null);
        getContentResolver().delete(uri,null,null);
        getContentResolver().getType(uri);

    }
}
