package com.study.heqing.ipc_test.ipc_aidl.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HeQing on 2016/8/27 0027.
 */
public class Book implements Parcelable {

    private int bookId;
    private String bookName;
    private float bookPrice;

    public Book(){

    }

    public Book(int bookId, String bookName, float bookPrice) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(bookId);
        parcel.writeString(bookName);
        parcel.writeFloat(bookPrice);
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel parcel) {
            return new Book(parcel);
        }

        @Override
        public Book[] newArray(int i) {
            return new Book[0];
        }
    };

    private Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
        bookPrice = in.readFloat();
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookPrice=" + bookPrice +
                '}';
    }
}
