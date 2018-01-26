package mobifun.myapplication2;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mobifun.myapplication2.Data.DbHelper;
import mobifun.myapplication2.Data.TableData;

/**
 * Created by 786 on 1/25/2018.
 */

//some part copied from template pick from internet

public class AboutYourself extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_yourself);

        final EditText editText = findViewById(R.id.writeStory);
        Button button = findViewById(R.id.Save_story);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mStory = editText.getText().toString();
                saveStory(mStory);
            }
        });



    }

    private void saveStory(String story) {


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String user = pref.getString(TableData.TableInfo.USER_NAME,"");

        long result;
        long time =  System.currentTimeMillis();

        DbHelper db = new DbHelper(this);
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TableData.TableInfo.STORY, story);
        cv.put(TableData.TableInfo.DATE, Long.toString(time));
        cv.put(TableData.TableInfo.CREATED_BY, user);

        result = sq.insert(TableData.TableInfo.TABLE_STORY, null, cv);

        if (result > 0) {
            launchMainActivity();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Unable to save", Toast.LENGTH_SHORT).show();
        }
    }


    private void launchMainActivity()
    {
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
