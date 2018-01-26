package mobifun.myapplication2.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 786 on 1/25/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final int database_version = 1;


    public DbHelper(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null,database_version);
        Log.v("DATABASE" , "Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        final String create_table_story_query = "CREATE TABLE "+ TableData.TableInfo.TABLE_STORY    + " ("+
                TableData.TableInfo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TableData.TableInfo.CREATED_BY + " TEXT NOT NULL, "+
                TableData.TableInfo.STORY + " TEXT NOT NULL, "+
                TableData.TableInfo.DATE + " TEXT NOT NULL);";

        final String create_table_user_query = "CREATE TABLE "+ TableData.TableInfo.TABLE_NAME_USER + " ("+
                TableData.TableInfo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TableData.TableInfo.USER_NAME + " TEXT NOT NULL, "+
                TableData.TableInfo.PASSWORD + " TEXT NOT NULL);";


        db.execSQL(create_table_story_query);
        db.execSQL(create_table_user_query);

        Log.v("DATABASE Table" , "Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
