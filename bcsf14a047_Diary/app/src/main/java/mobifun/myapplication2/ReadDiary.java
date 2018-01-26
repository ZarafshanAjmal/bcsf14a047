package mobifun.myapplication2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mobifun.myapplication2.Data.DatabaseHelper;

/**
 * Created by 786 on 1/25/2018.
 */


//some part copied from template pick from internet


public class ReadDiary extends AppCompatActivity
{
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_diary);

        ListView listView = (ListView) findViewById(R.id.lv_read_diary);

        myDB = new DatabaseHelper(this);

        ArrayList<String> arrayList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        if (data.getCount() == 0)
        {
            Toast.makeText(ReadDiary.this, "Database is empty :(", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(data.moveToNext())
            {
                //  ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_ID)));
                arrayList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, arrayList);
                listView.setAdapter(listAdapter);

            }

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
                {
                    String text = adapterView.getItemAtPosition(position).toString();

                    Cursor data = myDB.getItemID(text);
                    int itemID = -1;
                    while (data.moveToNext())
                    {
                        itemID = data.getInt(0);
                    }

                    if (itemID > -1)
                    {
                        Intent i = new Intent(ReadDiary.this, EditStory.class);
                        i.putExtra("id", itemID);
                        i.putExtra("text", text);
                        startActivity(i);
                    }
                }
            });
        }
    }
}

