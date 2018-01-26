package mobifun.myapplication2.Data;

import android.provider.BaseColumns;

/**
 * Created by 786 on 1/25/2018.
 */

public class TableData {

    public TableData(){

    }

    public static abstract class TableInfo implements BaseColumns
    {


        public static final String STORY =  "story";
        public static final String DATE =  "date";
        public static final String CREATED_BY =  "created_by";


        public static final String USER_NAME =  "user_name";
        public static final String PASSWORD =  "pass";






        //DATABASE AND TABLE NAME
        public static final String DATABASE_NAME = "diary_database";
        public static final String TABLE_STORY = "story";
        public static final String TABLE_NAME_USER = "table_user";

    }

}
