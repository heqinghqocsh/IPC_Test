package com.study.heqing.ipc_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.study.heqing.ipc_test.ipc_aidl.aidl.Book;

import java.util.List;

/**
 * Created by HeQing on 2016/8/27 0027.
 */
public class BookAdapter extends BaseAdapter{
    private List<Book> mBookList;
    private LayoutInflater mInflater;

    public BookAdapter(Context context,List<Book> bookList){
        mInflater = LayoutInflater.from(context);
        mBookList = bookList;
    }

    @Override
    public int getCount() {
        return mBookList.size();
    }

    @Override
    public Object getItem(int i) {
        return mBookList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = mInflater.inflate(R.layout.activity_book_manager_item,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        Book book = mBookList.get(i);
        viewHolder.id.setText(book.getBookId()+"");
        viewHolder.name.setText(book.getBookName()+"");
        viewHolder.price.setText(book.getBookPrice()+"");
        return view;
    }

    static class ViewHolder{
        public TextView id;
        public TextView name;
        public TextView price;
        public ViewHolder(View v){
            id = (TextView)v.findViewById(R.id.book_id);
            name = (TextView)v.findViewById(R.id.book_name);
            price = (TextView)v.findViewById(R.id.book_price);
        }
    }

}
