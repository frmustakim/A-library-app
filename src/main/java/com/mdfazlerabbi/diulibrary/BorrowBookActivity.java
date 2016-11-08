package com.mdfazlerabbi.diulibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BorrowBookActivity extends AppCompatActivity {

    EditText userIdEditText, userNameEditText;
    AutoCompleteTextView userIdAutoCompleteTextView, isbnCodeAutoCompleteTextView;
    TextView bookNameTextView;
    String userIdString, userNameString, isbnCodeString;
    Button borrowBookSubmitButton;
    ArrayList<Book> bookArrayList;
    ArrayAdapter<Book> bookArrayAdapter;
    ArrayList<User> userArrayList;
    ArrayAdapter<User> userArrayAdapter;
    Book book;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);

        initAllFormField();
        final UserModel userModel = new UserModel(BorrowBookActivity.this);
        final LibraryManager library = new LibraryManager(BorrowBookActivity.this);

        userArrayList = userModel.getAllUsersList();
        userArrayAdapter = new UserListAdapter(BorrowBookActivity.this, R.layout.activity_borrow_book, android.R.id.text1, userArrayList);
        userIdAutoCompleteTextView.setAdapter(userArrayAdapter);
        userIdAutoCompleteTextView.setThreshold(1);

        userIdAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user = userArrayList.get(position);
                userNameEditText.setText(user.getUserName());
            }
        });


        bookArrayList = library.getAllBookList();
        bookArrayAdapter = new BorrowBookListAdapter(BorrowBookActivity.this, R.layout.activity_borrow_book, android.R.id.text1, bookArrayList);
        isbnCodeAutoCompleteTextView.setAdapter(bookArrayAdapter);
        isbnCodeAutoCompleteTextView.setThreshold(1);

        isbnCodeAutoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //no need to do anything
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String isbnCodeString = s.toString();
                if (isbnCodeString.isEmpty()) {
                    bookNameTextView.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //no need to do anything
            }
        });

        isbnCodeAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                book = bookArrayList.get(position);
                bookNameTextView.setText(book.getBookName());
            }
        });



        borrowBookSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userIdString = userIdAutoCompleteTextView.getText().toString();
                userNameString = userNameEditText.getText().toString();
                isbnCodeString = isbnCodeAutoCompleteTextView.getText().toString();

                try {
                    if (userIdString.isEmpty()) {
                        throw new Exception("User ID is required.");
                    } else if (userNameString.isEmpty()) {
                        throw new Exception("User name is required.");
                    } else if (isbnCodeString.isEmpty()) {
                        throw new Exception("Book ISBN code is required.");
                    } else {
                        try {
                            if (!library.borrowBook(isbnCodeString, userIdString, userNameString)) {
                                throw new Exception("Database insertion fail.");
                            } else {
                                userIdAutoCompleteTextView.getText().clear();
                                userNameEditText.getText().clear();
                                isbnCodeAutoCompleteTextView.getText().clear();
                                bookNameTextView.setText(String.valueOf(""));
                                Toast.makeText(BorrowBookActivity.this, "Book borrowed.", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Log.d("Error: ", e.getMessage());
                            Toast.makeText(BorrowBookActivity.this, "Unexpected exception is happened. For details show log file.", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(BorrowBookActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void initAllFormField() {
        userIdAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.userIdAutoCompleteTextView);
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        isbnCodeAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.isbnCodeAutoCompleteTextView);
        bookNameTextView = (TextView) findViewById(R.id.bookNameTextView);

        borrowBookSubmitButton = (Button) findViewById(R.id.borrowBookSubmitButton);
    }

}

