package com.mdfazlerabbi.diulibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class UserViewActivity extends AppCompatActivity {

    @InjectView(R.id.user_booklist_btn) Button user_booklist;
    @InjectView(R.id.borrow_btn) Button user_borrow;
    @InjectView(R.id.return_btn) Button user_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.user_booklist_btn)
    public void user_booklist(){
        Intent intent = new Intent(UserViewActivity.this, BookListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.borrow_btn)
    public void user_borrowBook(){
        Intent intent = new Intent(UserViewActivity.this, BorrowBookActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.return_btn)
    public void user_returnBook(){
        Intent intent = new Intent(UserViewActivity.this, ReturnBookActivity.class);
        startActivity(intent);
    }
}
