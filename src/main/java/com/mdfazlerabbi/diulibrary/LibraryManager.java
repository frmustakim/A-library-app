package com.mdfazlerabbi.diulibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Md. Fazle Rabbi on 4/12/2016.
 */
public class LibraryManager {
    private DatabaseHelper helper;
    private SQLiteDatabase database;
    private ArrayList<Book> bookList;

    private Book book;

    public LibraryManager(Context context) {
        helper = new DatabaseHelper(context);
    }

    public void open() {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public Book getBookDetails(int bookId) {
        this.open();
        try {
            Cursor cursor = database.query(DatabaseHelper.TABLE_BOOK, null, DatabaseHelper.TABLE_BOOK_ID + " = " + bookId, null, null, null, null, null);
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_ID));
            String isbn = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_ISBN));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_NAME));
            String category = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_CATEGORY));
            String author = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_AUTHOR));
            int quantity = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_QUANTITY));
            String publisher = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_PUBLISHER));
            String edition = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_EDITION));


            book = new Book(id, isbn, name, category, author, edition, publisher, quantity);

        } catch (Exception e) {
            book = new Book();
            Log.d("error", e.getMessage().toString());
        }
        this.close();
        return book;
    }

    public Book getBookDetails(String isbnCode) {
        this.open();
        try {
            Cursor cursor = database.query(DatabaseHelper.TABLE_BOOK, null, DatabaseHelper.TABLE_BOOK_ISBN + " = \"" + isbnCode + "\"", null, null, null, null, null);
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_ID));
            String isbn = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_ISBN));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_NAME));
            String category = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_CATEGORY));
            String author = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_AUTHOR));
            int quantity = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_QUANTITY));
            String publisher = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_PUBLISHER));
            String edition = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_EDITION));

            book = new Book(id, isbn, name, category, author, edition, publisher, quantity);

        } catch (Exception e) {
            book = new Book();
            Log.d("error", e.getMessage().toString());
        }
        this.close();
        return book;
    }

    public ArrayList<Book> getAllBookList() {
        this.open();
        bookList = new ArrayList<Book>();
        try {
            Cursor cursor = database.query(DatabaseHelper.TABLE_BOOK, null, null, null, null, null, null);
            if (cursor.getColumnCount() > 0) {
                cursor.moveToFirst();
                for (int i = 1; i < cursor.getColumnCount(); i++) {
                    int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_ID));
                    String isbn = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_ISBN));
                    String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_NAME));
                    String category = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_CATEGORY));
                    String author = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_AUTHOR));
                    int quantity = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_QUANTITY));
                    String publisher = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_PUBLISHER));
                    String edition = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_EDITION));
                    Book dbBook = new Book(id, isbn, name, category, author, edition, publisher, quantity);
                    bookList.add(dbBook);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.d("error", e.getMessage().toString());
        }
        this.close();
        return bookList;
    }

    public boolean addBookToLibrary(Book book) {
        this.open();
        boolean result;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.TABLE_BOOK_ISBN, book.getIsbn());
            contentValues.put(DatabaseHelper.TABLE_BOOK_NAME, book.getBookName());
            contentValues.put(DatabaseHelper.TABLE_BOOK_CATEGORY, book.getCategory());
            contentValues.put(DatabaseHelper.TABLE_BOOK_AUTHOR, book.getAuthor());
            contentValues.put(DatabaseHelper.TABLE_BOOK_QUANTITY, book.getQuantity());
            contentValues.put(DatabaseHelper.TABLE_BOOK_PUBLISHER, book.getPublisher());
            contentValues.put(DatabaseHelper.TABLE_BOOK_EDITION, book.getEdition());
            Long inserted = database.insert(DatabaseHelper.TABLE_BOOK, null, contentValues);
            if (inserted > 0)
                result = true;
            else
                result = false;
        } catch (Exception e) {
            Log.d("error", e.getMessage());
            result = false;
        }
        this.close();

        return result;
    }

    public boolean deleteBookFromLibrary(int id) {
        this.open();
        boolean result;
        try {
            int deleted = database.delete(DatabaseHelper.TABLE_BOOK, DatabaseHelper.TABLE_BOOK_ID + " = " + id, null);
            if (deleted > 0)
                result = true;
            else
                result = false;
        } catch (Exception e) {
            Log.d("error", e.getMessage());
            result = false;
        }
        this.close();
        return result;
    }

    public boolean updateBook(Book book, int id) {
        this.open();
        boolean result;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.TABLE_BOOK_ISBN, book.getIsbn());
            contentValues.put(DatabaseHelper.TABLE_BOOK_NAME, book.getBookName());
            contentValues.put(DatabaseHelper.TABLE_BOOK_CATEGORY, book.getCategory());
            contentValues.put(DatabaseHelper.TABLE_BOOK_AUTHOR, book.getAuthor());
            contentValues.put(DatabaseHelper.TABLE_BOOK_PUBLISHER, book.getPublisher());
            contentValues.put(DatabaseHelper.TABLE_BOOK_EDITION, book.getEdition());
            int updated = database.update(DatabaseHelper.TABLE_BOOK, contentValues, DatabaseHelper.TABLE_BOOK_ID + " = " + id, null);
            if (updated > 0)
                result = true;
            else
                result = false;
        } catch (Exception e) {
            result = false;
            Log.d("error", e.getMessage());
        }
        this.close();
        return result;
    }

    public boolean borrowBook(String bookISBN, String userId, String userName) {
        this.open();
        boolean result;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.TABLE_USER_ID, userId);
            contentValues.put(DatabaseHelper.TABLE_USER_NAME, userName);
            contentValues.put(DatabaseHelper.TABLE_BORROW_ISBN_CODE, bookISBN);
                /*Insert into borrow table*/
            long inserted = database.insert(DatabaseHelper.TABLE_BORROW_NAME, null, contentValues);

            Cursor cursor = database.query(DatabaseHelper.TABLE_BOOK, new String[]{DatabaseHelper.TABLE_BOOK_ID, DatabaseHelper.TABLE_BOOK_QUANTITY}, DatabaseHelper.TABLE_BOOK_ISBN + " = \"" + bookISBN + "\"", null, null, null, null);
            cursor.moveToFirst();
            int bookId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_ID));
            int bookQuantity = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_QUANTITY));
            bookQuantity--;
            ContentValues updateContent = new ContentValues();
            updateContent.put(DatabaseHelper.TABLE_BOOK_QUANTITY, bookQuantity);
            int updated = database.update(DatabaseHelper.TABLE_BOOK, updateContent, DatabaseHelper.TABLE_BOOK_ID + " = " + bookId, null);

            if (inserted > 0 && updated > 0)
                result = true;
            else
                result = false;
        } catch (Exception e) {
            result = false;
            Log.d("error", e.getMessage());
        }
        this.close();
        return result;
    }

    public boolean returnBook(String bookISBN, String userId) {
        this.open();
        boolean result;
        try {
             /*Delete from borrow table*/
            int deleted = database.delete(DatabaseHelper.TABLE_BORROW_NAME, DatabaseHelper.TABLE_BORROW_ISBN_CODE + " = \"" + bookISBN + "\" AND " + DatabaseHelper.TABLE_USER_ID + " = \"" + userId + "\"", null);
            Cursor cursor = database.query(DatabaseHelper.TABLE_BOOK, new String[]{DatabaseHelper.TABLE_BOOK_ID, DatabaseHelper.TABLE_BOOK_QUANTITY}, DatabaseHelper.TABLE_BOOK_ISBN + " = \"" + bookISBN + "\"", null, null, null, null);
            cursor.moveToFirst();
            int bookId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_ID));
            int bookQuantity = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_QUANTITY));
            ContentValues updateContent = new ContentValues();
            bookQuantity++;
                 /*Update book quantity table*/
            updateContent.put(DatabaseHelper.TABLE_BOOK_QUANTITY, bookQuantity);
            int updated = database.update(DatabaseHelper.TABLE_BOOK, updateContent, DatabaseHelper.TABLE_BOOK_ID + " = " + bookId, null);
            if (deleted > 0 && updated > 0)
                result = true;
            else
                result = false;

        } catch (Exception e) {
            result = false;
            Log.d("error", e.getMessage());
        }
        this.close();
        return result;
    }

    public boolean addBookQuantity(int bookId, int quantity) {
        this.open();
        Boolean result;
        try {
            Cursor cursor = database.query(DatabaseHelper.TABLE_BOOK, new String[]{DatabaseHelper.TABLE_BOOK_ID, DatabaseHelper.TABLE_BOOK_QUANTITY}, DatabaseHelper.TABLE_BOOK_ID + " = " + bookId, null, null, null, null);
            cursor.moveToFirst();
            int bookTableId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_ID));
            int bookTableQuantity = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TABLE_BOOK_QUANTITY));
            int totalQuantity = quantity + bookTableQuantity;
            ContentValues updateContent = new ContentValues();
            updateContent.put(DatabaseHelper.TABLE_BOOK_QUANTITY, totalQuantity);
            int updated = database.update(DatabaseHelper.TABLE_BOOK, updateContent, DatabaseHelper.TABLE_BOOK_ID + " = " + bookTableId, null);
            if (updated > 0)
                result = true;
            else
                result = false;
        } catch (Exception e) {
            result = false;
            Log.d("error", e.getMessage());
        }
        this.close();
        return result;
    }
}


