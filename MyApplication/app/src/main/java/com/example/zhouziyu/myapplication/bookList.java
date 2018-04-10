package com.example.zhouziyu.myapplication;

/**
 * Created by zhouziyu on 2018/1/24.
 */

public class bookList {
    private String mylist1;
    private String mylist2;
    //判断是否显示图片
    private int imageSource = NO_IMAGW_PROVIDED;
    //visible invisible gone 0 4 8,-1不在其中
    private static final int NO_IMAGW_PROVIDED = -1;

    private  int audioResource;




    public bookList(String list1, String list2, int image3,int audio4){
        mylist1 = list1;
        mylist2 = list2;
        imageSource = image3;
        audioResource = audio4;

    }

    public String getList1(){
        return mylist1;
    }

    public String getList2(){
        return mylist2;
    }


    public boolean hasImage(){
       return imageSource != NO_IMAGW_PROVIDED;
    }

    public int getImage(){
        return imageSource;
    }

    public int getAudio(){
        return audioResource;
    }

}
