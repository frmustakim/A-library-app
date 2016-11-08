package com.mdfazlerabbi.diulibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AdminViewActivity extends AppCompatActivity {

    @InjectView(R.id.admin_booklist_btn)
    Button admin_booklist;
    @InjectView(R.id.add_btn)
    Button add_book;
    @InjectView(R.id.edit_btn)
    Button edit_book;
    @InjectView(R.id.book_user)
    Button book_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.admin_booklist_btn)
    public void admin_booklist() {
        Intent intent = new Intent(AdminViewActivity.this, BookListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.add_btn)
    public void user_borrowBook() {
        Intent intent = new Intent(AdminViewActivity.this, AddNewBookActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.edit_btn)
    public void user_returnBook() {
        Intent intent = new Intent(AdminViewActivity.this, EditBookActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.book_user)
    public void userList() {
        Intent intent = new Intent(AdminViewActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
