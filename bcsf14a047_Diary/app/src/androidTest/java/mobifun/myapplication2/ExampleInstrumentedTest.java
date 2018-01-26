package mobifun.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import mobifun.myapplication2.Data.DbHelper;
import mobifun.myapplication2.Data.TableData;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("mobifun.myapplication2", appContext.getPackageName());
    }

    @Test
    public void DbCreation() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        appContext.deleteDatabase(TableData.TableInfo.DATABASE_NAME);

        SQLiteDatabase db = new DbHelper(appContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }

    @Test
    public void testDbinsert() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DbHelper db = new DbHelper(appContext);
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TableData.TableInfo.USER_NAME, "abc");
        cv.put(TableData.TableInfo.PASSWORD, "abc");
        long result = sq.insert(TableData.TableInfo.TABLE_NAME_USER, null, cv);
        assertTrue(result > 0);

    }

    @Test
    public void testGet() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DbHelper db = new DbHelper(appContext);
        SQLiteDatabase sq = db.getReadableDatabase();

        String[] coloumns = {TableData.TableInfo._ID,
                TableData.TableInfo.USER_NAME,
                TableData.TableInfo.PASSWORD};

        String selection = TableData.TableInfo.USER_NAME + " = ? ";
        String[] args = new String[]{"abc"};
        Cursor data = sq.query(TableData.TableInfo.TABLE_NAME_USER, coloumns, selection, args, null, null, null);

        if (data.moveToFirst()) {
            int name_index = data.getColumnIndex(TableData.TableInfo.USER_NAME);
            String name = data.getString(name_index);

            int pass_index = data.getColumnIndex(TableData.TableInfo.PASSWORD);
            String passV = data.getString(pass_index);

            assertEquals("abc", "abc");
            assertEquals("abc", passV);


        }
    }

    @Test
    public void testStory() {

        Context appContext = InstrumentationRegistry.getTargetContext();
        long result;
        long time = System.currentTimeMillis();
        String time_val = Long.toString(time);

        DbHelper db = new DbHelper(appContext);
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TableData.TableInfo.STORY, "abc");
        cv.put(TableData.TableInfo.DATE, time_val);
        cv.put(TableData.TableInfo.CREATED_BY, "Zarafshan");

        result = sq.insert(TableData.TableInfo.TABLE_STORY, null, cv);

        assertTrue(result>0);
    }
}

