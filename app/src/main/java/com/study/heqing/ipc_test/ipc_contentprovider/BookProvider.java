package com.study.heqing.ipc_test.ipc_contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SelectFormat;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by HeQing on 2016/8/28 0028.
 */
public class BookProvider extends ContentProvider{
    private static final String TAG = "BookProvider";

    public static final String AUTHORITY = "com.study.heqing.ipc_test.book.provider";
    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(AUTHORITY,"book",BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY,"user",USER_URI_CODE);
    }

    private Context mContext;
    private SQLiteDatabase mDb;

    @Override
    public boolean onCreate() {
        Log.i(TAG,"onCreate ,current thread : "+Thread.currentThread().getName());
        mContext = getContext();
        mDb = new DbOpenHelper(mContext).getWritableDatabase();
        initProviderData();
        return true;
    }

    private void initProviderData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mDb.execSQL("delete from "+DbOpenHelper.BOOK_TABLE_NAME);
                mDb.execSQL("delete from "+DbOpenHelper.USER_TABLE_NAME);

                mDb.execSQL("insert into book (bookid,bookname,bookprice) values(1,'Android',33.8)");
                mDb.execSQL("insert into book (bookid,bookname,bookprice) values(2,'iOS',43.8)");
                mDb.execSQL("insert into book (bookid,bookname,bookprice) values(3,'HTML5',53.8)");

                mDb.execSQL("insert into user (id,name,sex) values(1,'何清',1)");
                mDb.execSQL("insert into user (id,name,sex) values(2,'小雪',0)");
            }
        }).start();
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection
            , String[] selectionArgs, String sortOrder) {
        Log.i(TAG,"query ,current thread : "+Thread.currentThread().getName());
        String table = getTableName(uri);
        if (table == null){
            throw new IllegalArgumentException("Unsupported uri: "+uri);
        }
        return mDb.query(table,projection,selection,selectionArgs,null,null,sortOrder,null);
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
        String table = getTableName(uri);
        if (table == null){
            throw new IllegalArgumentException("Unsupported uri: "+uri);
        }
        mDb.insert(table,null,contentValues);
        mContext.getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.i(TAG,"delete ,current thread : "+Thread.currentThread().getName());
        String table = getTableName(uri);
        if (table == null){
            throw new IllegalArgumentException("Unsupported uri: "+uri);
        }
        int count = mDb.delete(table,selection,selectionArgs);
        if (count > 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        Log.i(TAG,"update ,current thread : "+Thread.currentThread().getName());
        String table = getTableName(uri);
        if (table == null){
            throw new IllegalArgumentException("Unsupported uri: "+uri);
        }
        int row = mDb.update(table,contentValues,selection,selectionArgs);
        if (row > 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return row;
    }

    private String getTableName(Uri uri){
        String tableName = null;
        switch (sUriMatcher.match(uri)){
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = DbOpenHelper.USER_TABLE_NAME;
                break;
            default:
                break;
        }
        return tableName;
    }
}
