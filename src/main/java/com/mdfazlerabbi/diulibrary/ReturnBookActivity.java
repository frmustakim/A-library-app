package com.mdfazlerabbi.diulibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ReturnBookActivity extends AppCompatActivity {

    AutoCompleteTextView userIdAutoCompleteTextView, isbnCodeAutoCompleteTextView;
    Button returnBookSubmitButton;
    String userIdString, isbnCodeString;
    ArrayList<Book> bookArrayList;
    ArrayAdapter<Book> bookArrayAdapter;
    ArrayList<User> userArrayList;
    ArrayAdapter<User> userArrayAdapter;
    Book book;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book);
        initAllFormField();

        final UserModel userModel = new UserModel(ReturnBookActivity.this);
        final LibraryManager library = new LibraryManager(ReturnBookActivity.this);

        userArrayList = userModel.getAllUsersList();
        userArrayAdapter = new UserListAdapter(ReturnBookActivity.this, R.layout.activity_borrow_book, android.R.id.text1, userArrayList);
        userIdAutoCompleteTextView.setAdapter(userArrayAdapter);
        userIdAutoCompleteTextView.setThreshold(1);

        /*userIdAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user = userArrayList.get(position);
                userNameEditText.setText(user.getUserName());
            }
        });*/

        bookArrayList = library.getAllBookList();
        bookArrayAdapter = new BorrowBookListAdapter(ReturnBookActivity.this, R.layout.activity_borrow_book, android.R.id.text1, bookArrayList);
        isbnCodeAutoCompleteTextView.setAdapter(bookArrayAdapter);
        isbnCodeAutoCompleteTextView.setThreshold(1);

        returnBookSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userIdString = userIdAutoCompleteTextView.getText().toString();
                isbnCodeString = isbnCodeAutoCompleteTextView.getText().toString();

                try {
                    if (userIdString.isEmpty()) {
                        throw new Exception("User ID is required.");
                    } else if (isbnCodeString.isEmpty()) {
                        throw new Exception("Book ISBN code is required.");
                    } else {
                        try {
                            if (!library.returnBook(isbnCodeString, userIdString)) {
                                throw new Exception("Returning book failed.");
                            } else {
                                userIdAutoCompleteTextView.getText().clear();
                                isbnCodeAutoCompleteTextView.getText().clear();
                                Toast.makeText(ReturnBookActivity.this, "Book Returned.", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Log.d("Error: ", e.getMessage());
                            Toast.makeText(ReturnBookActivity.this, "Unexpected exception. For details view log file.", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(ReturnBookActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void initAllFormField() {
        userIdAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.userIdAutoCompleteTextView);
        //userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        isbnCodeAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.isbnCodeAutoCompleteTextView);
        //bookNameTextView = (TextView) findViewById(R.id.bookNameTextView);

        returnBookSubmitButton = (Button) findViewById(R.id.returnBookSubmitButton);
    }
}
