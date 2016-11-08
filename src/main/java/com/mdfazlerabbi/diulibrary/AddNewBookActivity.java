package com.mdfazlerabbi.diulibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddNewBookActivity extends AppCompatActivity {

    EditText isbnCodeEditText, bookNameEditText, authorNameEditText, bookQuantityEditText, bookEditionEditText, bookPublisherEditText;
    Spinner bookCategorySpinner;
    String isbnCodeString, bookNameString, authorNameString, bookQuantityString, bookEditionString, bookPublisherString, bookCategoryString;
    Button addNewBookSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);

        initAllFormField();


        addNewBookSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();

                isbnCodeString = isbnCodeEditText.getText().toString();
                bookNameString = bookNameEditText.getText().toString();
                authorNameString = authorNameEditText.getText().toString();
                bookCategoryString = bookCategorySpinner.getSelectedItem().toString();
                bookQuantityString = bookQuantityEditText.getText().toString();
                bookEditionString = bookEditionEditText.getText().toString();
                bookPublisherString = bookPublisherEditText.getText().toString();

                try {
                    if (isbnCodeString.isEmpty()) {
                        Toast.makeText(AddNewBookActivity.this, "You must provide ISBN code number of book.", Toast.LENGTH_LONG).show();
                        return;
                    } else if (bookNameString.isEmpty()) {
                        Toast.makeText(AddNewBookActivity.this, "You must provide book name.", Toast.LENGTH_LONG).show();
                        return;
                    } else if (authorNameString.isEmpty()) {
                        Toast.makeText(AddNewBookActivity.this, "You must provide author name.", Toast.LENGTH_LONG).show();
                        return;
                    } else if (bookQuantityString.isEmpty() || Integer.parseInt(bookQuantityString) < 0) {
                        Toast.makeText(AddNewBookActivity.this, "You must provide book quantity at least 1.", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        book.setIsbn(isbnCodeString);
                        book.setBookName(bookNameString);
                        book.setAuthor(authorNameString);
                        book.setCategory(bookCategoryString);
                        book.setEdition(bookEditionString);
                        book.setPublisher(bookPublisherString);
                        book.setQuantity(Integer.parseInt(bookQuantityString));

                        LibraryManager library = new LibraryManager(getApplicationContext());
                        try {
                            if (library.addBookToLibrary(book)) {
                                clearAllField();
                                Toast.makeText(AddNewBookActivity.this, "Added new book", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AddNewBookActivity.this, "Opps", Toast.LENGTH_SHORT).show();
                                throw new RuntimeException();
                            }
                        } catch (Exception e) {
                            Toast.makeText(AddNewBookActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(AddNewBookActivity.this, "Unexpected exception. Please fill the form correctly.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void initAllFormField() {
        isbnCodeEditText = (EditText) findViewById(R.id.isbnCodeEditText);
        bookNameEditText = (EditText) findViewById(R.id.bookNameEditText);
        authorNameEditText = (EditText) findViewById(R.id.authorNameEditText);
        bookQuantityEditText = (EditText) findViewById(R.id.bookQuantityEditText);
        bookEditionEditText = (EditText) findViewById(R.id.bookEditionEditText);
        bookPublisherEditText = (EditText) findViewById(R.id.bookPublisherEditText);

        bookCategorySpinner = (Spinner) findViewById(R.id.bookCategorySpinner);

        addNewBookSubmitButton = (Button) findViewById(R.id.addNewBookSubmitButton);
    }

    private void clearAllField() {
        isbnCodeEditText.getText().clear();
        bookNameEditText.getText().clear();
        authorNameEditText.getText().clear();
        bookQuantityEditText.getText().clear();
        bookEditionEditText.getText().clear();
        bookPublisherEditText.getText().clear();
        bookCategorySpinner.setSelection(0);

    }


}
