package com.example.zhouziyu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class sellActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        ArrayList<bookList> sellList = new ArrayList<bookList>();

        sellList.add(new bookList("one","sdc",R.drawable.family_older_brother,R.raw.family_older_brother));
        sellList.add(new bookList("twe","s345",R.drawable.family_daughter,R.raw.family_daughter));


        WordAdapter sellAdapter = new WordAdapter(this,sellList,R.color.colorTextBackground);

        ListView listView = (ListView) findViewById(R.id.sellList);
        listView.setAdapter(sellAdapter);

    }
}
