package com.example.zhouziyu.myapplication.data;

import android.provider.BaseColumns;

/**
 * Created by zhouziyu on 2018/4/14.
 */

public final class bookContract {
    //private BookContract() {}

    public static final class BookEntry implements BaseColumns{
        public final static String TABLE_NAME = "books";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_BOOK_NAME = "书名";
        public final static String COLUMN_BOOK_AUTHOR = "作者";
        public final static String COLUMN_BOOK_PUBLISHER = "出版社";
        public final static String COLUMN_BOOK_PRICE = "价格";
        public final static String COLUMN_CITY = "城市";
        public final static String COLUMN_UNIVERSITY = "大学";
        public final static String COLUMN_CONTACT = "联系方式";
        public final static String COLUMN_DESCRIPTION = "描述";

        public static final int CITY_UNKNOW = 0;
        public static final int CITY_1 = 1;
        public static final int CITY_2 = 2;
        public static final int University_UNKNOW = 0;
        public static final int University_1 = 1;





    }

}
