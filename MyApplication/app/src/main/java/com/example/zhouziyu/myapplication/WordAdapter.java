package com.example.zhouziyu.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.content.ContextCompat;


import java.util.ArrayList;

/**
 * Created by zhouziyu on 2018/1/24.
 */

public class WordAdapter extends ArrayAdapter<bookList> {

    //AdapterView:eg ListView GridView

    @NonNull
    //得到类的简写名称
    private static  final String LOG_TAG = WordAdapter.class.getSimpleName();

    //背景颜色id
    private int colorResourceId;

    public WordAdapter(Activity context, ArrayList<bookList> booklists, int colorResource ){
        //把ArrayAdapter的内容初始化为context（环境）和list

        super(context,0,booklists);
        //第二个参量：当ArrayAdapter被TextView填充，这里我们不使用它，因此它可以是任意值，eg：0

        colorResourceId = colorResource;
    }
//getView（数据在列表中的位置，可用来填充的回收视图，父ViewGroup）（将convertView填充到parent里）
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView =convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent,false             //是否把列表项视图附到父listView上
            );
        }

        bookList currentword = getItem(position);
        //getItem获得相应数据集合中特定位置的数据项

        TextView firstTextView = (TextView) listItemView.findViewById(R.id.listItem1);
        firstTextView.setText(currentword.getList1());

        TextView secondTextView = (TextView) listItemView.findViewById(R.id.listItem2);
        secondTextView.setText(currentword.getList2());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image1);
        if(currentword.hasImage()){
            imageView.setImageResource(currentword.getImage());
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            imageView.setVisibility(View.GONE);
        }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), colorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
