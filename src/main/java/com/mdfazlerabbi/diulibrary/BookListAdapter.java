package com.mdfazlerabbi.diulibrary;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Md. Fazle Rabbi on 4/12/2016.
 */
public class BookListAdapter extends ArrayAdapter {

    Context context;
    LibraryManager libraryManager;
    ArrayList<Book> bookList;
    Book book;


    public BookListAdapter(Context context, ArrayList<Book> objects) {
        super(context, R.layout.book_list_row, objects);
        this.context = context;
        this.bookList = objects;
    }

    static class ViewHolder {

        ImageView bookCoverIV;
        TextView bookNameTV;
        TextView bookCategoryTV;
        TextView bookQtyTV;
        ImageButton detailBookInfoIB;
        Button borrowBookIB;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            // to convert my xml custom layout into view!
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.book_list_row, null);

            viewHolder = new ViewHolder();
            viewHolder.bookCoverIV = (ImageView) convertView.findViewById(R.id.bookCoverIVID);
            viewHolder.bookNameTV = (TextView) convertView.findViewById(R.id.bookNameTVID);
            viewHolder.bookCategoryTV = (TextView) convertView.findViewById(R.id.bookCategoryTVID);
            viewHolder.bookQtyTV = (TextView) convertView.findViewById(R.id.bookQtyTVID);
            viewHolder.detailBookInfoIB = (ImageButton) convertView.findViewById(R.id.detailBookInfoIBID);
            viewHolder.borrowBookIB = (Button) convertView.findViewById(R.id.borrowBookIBID);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }


        viewHolder.bookCoverIV.setImageResource(R.drawable.book);
        viewHolder.bookNameTV.setText(bookList.get(position).getBookName());
        viewHolder.bookCategoryTV.setText(bookList.get(position).getCategory());
        viewHolder.bookQtyTV.setText(String.valueOf(bookList.get(position).getQuantity()));



        // Detail Btn Task
        viewHolder.detailBookInfoIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_dialog_details);
                dialog.setTitle("Book Details");

                // set the custom dialog components - text, image and button

                TextView isbnTV = (TextView) dialog.findViewById(R.id.bookDetailsISBNTVID);
                TextView bookNameTV = (TextView) dialog.findViewById(R.id.bookDetailsBookNameTVID);
                TextView authorNameTV = (TextView) dialog.findViewById(R.id.bookDetailsAuthorTVID);
                TextView editionTV = (TextView) dialog.findViewById(R.id.bookDetailsEditionTVID);
                TextView categoryTV = (TextView) dialog.findViewById(R.id.bookDetailsTypeTVID);
                TextView publisherTV = (TextView) dialog.findViewById(R.id.bookDetailsPublisherTVID);
                TextView quantityTV = (TextView) dialog.findViewById(R.id.bookDetailsQuantityTVID);
                Button doneBtn = (Button) dialog.findViewById(R.id.bookDetailsBtnID);

                // Set Values from DB
                isbnTV.setText(bookList.get(position).getIsbn());
                bookNameTV.setText(bookList.get(position).getBookName());
                authorNameTV.setText(bookList.get(position).getAuthor());
                editionTV.setText(bookList.get(position).getEdition());
                categoryTV.setText(bookList.get(position).getCategory());
                publisherTV.setText(bookList.get(position).getPublisher());
                quantityTV.setText(String.valueOf(bookList.get(position).getQuantity()));

                // if Done Btn is Clicked

                doneBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();

                    }
                });

                dialog.show();


            }
        });

        viewHolder.borrowBookIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent borrowBookIntent = new Intent(v.getContext(), BorrowBookActivity.class);
                v.getContext().startActivity(borrowBookIntent);
            }
        });


        return convertView;
    }
}

