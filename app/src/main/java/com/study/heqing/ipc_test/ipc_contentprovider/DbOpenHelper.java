package com.study.heqing.ipc_test.ipc_contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HeQing on 2016/8/28 0028.
 */
public class DbOpenHelper extends SQLiteOpenHelper{
    private static final String TAG = "DbOpenHelper";

    private static final String DB_NAME = "book_provider.db";
    public static final String BOOK_TABLE_NAME = "book";
    public static final String USER_TABLE_NAME = "user";

    private static final int DB_VERSION = 4;

    private String CREATE_BOOK_TABLE = "create table if not exists "+BOOK_TABLE_NAME
            +"(_id integer primary key autoincrement, bookid integer,bookname text,bookprice real)";
    private String CREATE_USER_TABLE = "create table if not exists "+USER_TABLE_NAME
            +"(_id integer primary key autoincrement,id integer,name text,sex int)";

    public DbOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK_TABLE);
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
