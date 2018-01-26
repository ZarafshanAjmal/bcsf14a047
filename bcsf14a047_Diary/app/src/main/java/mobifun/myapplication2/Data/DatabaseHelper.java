package mobifun.myapplication2.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 786 on 1/25/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "digital_diary.db";
    public static final String TABLE_NAME = "story_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "STORY";



    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, STORY TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public  boolean insertData(String text)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, text);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getListContents()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor data = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public Cursor getItemID(String name)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT " + COL_1 + " FROM " + TABLE_NAME +
                " WHERE " + COL_2 + " = '" + name + "'";

        Cursor data = sqLiteDatabase.rawQuery(query, null);
        return data;
    }

    public void updateText(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL_2 +
                " = '" + newName + "' WHERE " + COL_1 + " = '" + id + "'";
        db.execSQL(query);
    }

    public void deleteText(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL_1 + " = '" + id + "'";
        db.execSQL(query);
    }
}
