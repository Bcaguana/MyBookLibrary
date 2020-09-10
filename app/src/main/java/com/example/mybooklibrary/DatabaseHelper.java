package com.example.mybooklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String BOOK_TABLE = "BOOK_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_BOOK_NAME = "BOOK_NAME";
    public static final String COLUMN_BOOK_AUTHOR = "BOOK_AUTHOR";
    public static final String COLUMN_BOOK_PAGES = "BOOK_PAGES";
    public static final String COLUMN_IMAGE = "BOOK_IMAGE";
    public static final String COLUMN_SHORT_DESC = "BOOK_SHORT_DESC";
    public static final String COLUMN_LONG_DESC = "BOOK_LONG_DESC";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "library.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + BOOK_TABLE + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_BOOK_NAME + " TEXT, "
                + COLUMN_BOOK_AUTHOR + " TEXT, "
                + COLUMN_BOOK_PAGES + " INT, "
                + COLUMN_IMAGE + " TEXT, "
                + COLUMN_SHORT_DESC + " TEXT, "
                + COLUMN_LONG_DESC + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TODO: LEARN WHATEVER THIS DOES/IS SUPPOSED TO DO
    }

    public boolean addOne(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BOOK_NAME, book.getName());
        cv.put(COLUMN_BOOK_AUTHOR, book.getAuthor());
        cv.put(COLUMN_BOOK_PAGES, book.getPages());
        cv.put(COLUMN_IMAGE, book.getImageUrl());
        cv.put(COLUMN_SHORT_DESC, book.getShortDesc());
        cv.put(COLUMN_LONG_DESC, book.getLongDesc());

        long insert = db.insert(BOOK_TABLE, null, cv);
        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }
    //TODO: DELETE FUNCTION

    public ArrayList<Book> getEveryone(){
        ArrayList<Book> books = new ArrayList<>();
        String queryString  = "Select * from " + BOOK_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do{
                int bookId = cursor.getInt(0);
                String bookName = cursor.getString(1);
                String bookAuthor = cursor.getString(2);
                int bookPages = cursor.getInt(3);
                String bookImage = cursor.getString(4);
                String bookShortDesc = cursor.getString(5);
                String bookLongDesc = cursor.getString(6);

                Book newBook = new Book(bookId, bookName, bookAuthor, bookPages, bookImage, bookShortDesc, bookLongDesc);
                books.add(newBook);

            }while(cursor.moveToNext());
        }else {
            //failure
        }
        cursor.close();
        db.close();
        return books;
    }
}
