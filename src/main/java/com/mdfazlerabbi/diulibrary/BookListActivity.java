package com.mdfazlerabbi.diulibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    ListView bookListView;

    BookListAdapter adapter;
    LibraryManager library;
    ArrayList<Book> bookList = new ArrayList();
    ImageButton editBookIBID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        bookListView = (ListView) findViewById(R.id.bookListViewID);
        this.setListView();    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        this.setListView();
    }

    private void setListView() {
        library = new LibraryManager(BookListActivity.this);

        bookList = library.getAllBookList();

        adapter = new BookListAdapter(BookListActivity.this, bookList);
        adapter.notifyDataSetChanged();
        bookListView.setAdapter(adapter);
    }
}
