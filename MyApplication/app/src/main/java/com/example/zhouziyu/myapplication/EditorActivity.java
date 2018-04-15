/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.zhouziyu.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zhouziyu.myapplication.data.BookDbHelper;
import com.example.zhouziyu.myapplication.data.bookContract;

/**
 * Allows user to create a new book or edit an existing one.
 */
public class EditorActivity extends AppCompatActivity {

    private EditText mNameEditText;

    private EditText mAthorEditText;

    private EditText mPublisherEditText;

    private EditText mPriceEditText;

    private Spinner mCitySpinner;

    private Spinner mUniversitySpinner;

    private EditText mContactEditText;

    private EditText mDescriptionEditText;


    private int mCity = 0;

    private int mUniversity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.edit_book_name);
        mAthorEditText = (EditText) findViewById(R.id.edit_author);
        mPublisherEditText = (EditText) findViewById(R.id.edit_publish);
        mPriceEditText = (EditText) findViewById(R.id.edit_price);
        mCitySpinner = (Spinner) findViewById(R.id.spinner_city);
        mUniversitySpinner = (Spinner) findViewById(R.id.spinner_univercity);
        mContactEditText = (EditText) findViewById(R.id.edit_contact);
        mDescriptionEditText = (EditText) findViewById(R.id.edit_description);

        setupSpinner();
    }

    /**
     * Setup the dropdown spinner that allows the user to select the city and university.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter SpinnerCityAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_city_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        SpinnerCityAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // City!
        mCitySpinner.setAdapter(SpinnerCityAdapter);

        // Set the integer mSelected to the constant values
        mCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.city_bj))) {
                        mCity = bookContract.BookEntry.CITY_1;
                    } else if (selection.equals(getString(R.string.city_bj))) {
                        mCity = bookContract.BookEntry.CITY_2;
                    } else {
                        mCity = bookContract.BookEntry.CITY_UNKNOW;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mCity = 0; // Unknown
            }
        });

        ArrayAdapter SpinnerUniversityAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_university_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        SpinnerUniversityAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        // University!
        mUniversitySpinner.setAdapter(SpinnerUniversityAdapter);

        mUniversitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.university_buaa))) {
                        mUniversity = bookContract.BookEntry.University_1;
                    } else {
                        mUniversity = bookContract.BookEntry.University_UNKNOW;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mUniversity = 0; // Unknown
            }
        });
    }

    private void insertBook(){
        String nameString = mNameEditText.getText().toString().trim();
        String authorString = mAthorEditText.getText().toString().trim();
        String publisherString = mPublisherEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();
        float price = Float.parseFloat(priceString);
        String contactString = mContactEditText.getText().toString().trim();
        String descriptionString = mDescriptionEditText.getText().toString().trim();

        BookDbHelper mDbHelper = new BookDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(bookContract.BookEntry.COLUMN_BOOK_NAME, nameString);
        values.put(bookContract.BookEntry.COLUMN_BOOK_AUTHOR, authorString);
        values.put(bookContract.BookEntry.COLUMN_BOOK_PUBLISHER, publisherString);
        values.put(bookContract.BookEntry.COLUMN_BOOK_PRICE, price);
        values.put(bookContract.BookEntry.COLUMN_CITY, mCity);
        values.put(bookContract.BookEntry.COLUMN_UNIVERSITY, mUniversity);
        values.put(bookContract.BookEntry.COLUMN_CONTACT, contactString);
        values.put(bookContract.BookEntry.COLUMN_DESCRIPTION, descriptionString);
        long newRowId = db.insert(bookContract.BookEntry.TABLE_NAME,null,values);

        if(newRowId == -1){
            Toast.makeText(this,"错误",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"成功保存" + newRowId,Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                insertBook();
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (SellFragment)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}