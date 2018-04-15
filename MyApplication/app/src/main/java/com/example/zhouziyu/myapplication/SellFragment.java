package com.example.zhouziyu.myapplication;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhouziyu.myapplication.data.BookDbHelper;
import com.example.zhouziyu.myapplication.data.bookContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellFragment extends Fragment {


    public SellFragment() {
        // Required empty public constructor
    }

    private BookDbHelper mDbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_sell, container, false);
        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EditorActivity.class);
                startActivity(intent);
            }
        });

        displayDatabaseInfo(rootView);
        mDbHelper = new BookDbHelper(getActivity());

        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();
        displayDatabaseInfo(this.getView());
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo(View view) {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        BookDbHelper mDbHelper = new BookDbHelper(getActivity());

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM books"
        // to get a Cursor that contains all rows from the table.

        String[] projection = {
                bookContract.BookEntry._ID,
                bookContract.BookEntry.COLUMN_BOOK_NAME,
                bookContract.BookEntry.COLUMN_BOOK_AUTHOR,
                bookContract.BookEntry.COLUMN_BOOK_PUBLISHER,
                bookContract.BookEntry.COLUMN_BOOK_PRICE,
                bookContract.BookEntry.COLUMN_CITY,
                bookContract.BookEntry.COLUMN_UNIVERSITY,
                bookContract.BookEntry.COLUMN_CONTACT,
                bookContract.BookEntry.COLUMN_DESCRIPTION
        };

        Cursor cursor = db.query(
                bookContract.BookEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        TextView displayView = (TextView) view.findViewById(R.id.text_view_book);

        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            displayView.setText("你上传的书共有: " + cursor.getCount() + "本\n");
            displayView.append(bookContract.BookEntry._ID + "-" + bookContract.BookEntry.COLUMN_BOOK_NAME
                    + "-" + bookContract.BookEntry.COLUMN_BOOK_AUTHOR
                    + "-" + bookContract.BookEntry.COLUMN_BOOK_PUBLISHER
                    + "-" + bookContract.BookEntry.COLUMN_BOOK_PRICE
                    + "-" + bookContract.BookEntry.COLUMN_CITY
                    + "-" + bookContract.BookEntry.COLUMN_UNIVERSITY + "\n");

            int idColumnIndex = cursor.getColumnIndex(bookContract.BookEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(bookContract.BookEntry.COLUMN_BOOK_NAME);
            int authorColumnIndex = cursor.getColumnIndex(bookContract.BookEntry.COLUMN_BOOK_AUTHOR);
            int publisherColumnIndex = cursor.getColumnIndex(bookContract.BookEntry.COLUMN_BOOK_PUBLISHER);
            int priceColumnIndex = cursor.getColumnIndex(bookContract.BookEntry.COLUMN_BOOK_PRICE);
            int cityColumnIndex = cursor.getColumnIndex(bookContract.BookEntry.COLUMN_CITY);
            int universityColumnIndex = cursor.getColumnIndex(bookContract.BookEntry.COLUMN_UNIVERSITY);


            while(cursor.moveToNext()){
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentAuthor = cursor.getString(authorColumnIndex);
                String currentPublisher = cursor.getString(publisherColumnIndex);
                float currentPrice = cursor.getFloat(priceColumnIndex);
                int currentCity = cursor.getInt(cityColumnIndex);
                int currentUniversity = cursor.getInt(universityColumnIndex);

                displayView.append(("\n" + currentID + "-"
                        + currentName + "-"
                        + currentAuthor + "-"
                        + currentPublisher + "-"
                        + currentPrice + "-"
                        + currentCity + "-"
                        + currentUniversity));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        inflater.inflate(R.menu.menu_catalog, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    private void insertBook() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(bookContract.BookEntry.COLUMN_BOOK_NAME, "Math");
        values.put(bookContract.BookEntry.COLUMN_BOOK_AUTHOR, "who");
        values.put(bookContract.BookEntry.COLUMN_BOOK_PUBLISHER, "某出版社");
        values.put(bookContract.BookEntry.COLUMN_BOOK_PRICE, "8.8");
        values.put(bookContract.BookEntry.COLUMN_CITY, bookContract.BookEntry.CITY_1);
        values.put(bookContract.BookEntry.COLUMN_UNIVERSITY, bookContract.BookEntry.University_1);
        values.put(bookContract.BookEntry.COLUMN_CONTACT, "123@qq.com");
        values.put(bookContract.BookEntry.COLUMN_DESCRIPTION, "good");
        long newRowd = db.insert(bookContract.BookEntry.TABLE_NAME,null,values);



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_book_data:
                insertBook();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
