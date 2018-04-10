package com.example.zhouziyu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static android.system.Os.remove;
import static java.nio.file.Files.size;

public class usersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

//        //创建数组,Array is not class,储存Primitive和Object类型
//        int [] sell = new int[3];
//        sell[0] = 12;
//        sell[1] = 2;
//        String[] words = new String[3];
//        words[0] = "zero";
////        Log.v("usersActivity", String.valueOf(sell[1]));
////        Log.v("usersActivity",words[0] + sell.length);

        //创建数组列表,Arraylist is class,only储存Object类型。大小内容可变

        ArrayList<String> tryArrayList = new ArrayList<String>();
        tryArrayList.add("try111");
        tryArrayList.add("try121");
        tryArrayList.add("try131");
        tryArrayList.add("try141");
        tryArrayList.add("try151");
        tryArrayList.add("try161");
        tryArrayList.add("try171");
        tryArrayList.add("try131");
        tryArrayList.add("try181");
        tryArrayList.add("try191");
        tryArrayList.add("try101");
        tryArrayList.add("try241");

        tryArrayList.remove("try111");

        int numOfTry = tryArrayList.size();

//        Log.v("sellActivityArrayList", tryArrayList.get(0));


        for (int i = 0; i < numOfTry; i++){
            setList(i,tryArrayList);
        }
    }

    private void setList(int i,ArrayList<String> list){
        LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);
        TextView tryView = new TextView(this);
        tryView.setText(list.get(i));
        tryView.setTextSize(24);
        rootView.addView(tryView);
    }


}


