package com.study.heqing.ipc_test.ipc_contentprovider;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.study.heqing.ipc_test.R;
import com.study.heqing.ipc_test.ipc_aidl.aidl.Book;

/**
 * Created by HeQing on 2016/8/28 0028.
 */
public class ProviderActivity extends Activity{
    private static final String TAG = "ProviderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_provider);

        ContentResolver resolver = getContentResolver();

        Uri bookUri = Uri.parse("content://com.study.heqing.ipc_test.book.provider/book");
        ContentValues values = new ContentValues();
        values.put("bookid",4);
        values.put("bookname","jQuery");
        values.put("bookprice",45.9f);

        resolver.insert(bookUri,values);
        Cursor bookCursor = resolver.query(bookUri,new String[]{"bookid","bookname","bookprice"}
                ,null,null,null);
        while (bookCursor.moveToNext()){
            Book book = new Book();
            book.setBookId(bookCursor.getInt(0));
            book.setBookName(bookCursor.getString(1));
            book.setBookPrice(bookCursor.getFloat(2));
            Log.i(TAG,book.toString());
        }

        Uri userUri = Uri.parse("content://com.study.heqing.ipc_test.book.provider/user");
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",4);
        contentValues.put("name","Jack");
        contentValues.put("sex",1);

        resolver.insert(userUri,contentValues);
        Cursor userCursor = resolver.query(userUri,new String[]{"id","name","sex"}
                ,null,null,null);
        while (userCursor.moveToNext()){
            User user = new User();
            user.setId(userCursor.getInt(0));
            user.setName(userCursor.getString(1));
            user.setSex(userCursor.getInt(2));
            Log.i(TAG,user.toString());
        }

    }
}
