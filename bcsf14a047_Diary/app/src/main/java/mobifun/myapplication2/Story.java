package mobifun.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mobifun.myapplication2.Data.DatabaseHelper;

/**
 * Created by 786 on 1/25/2018.
 */

//some part copied from template pick from internet

public class Story extends AppCompatActivity
{
    DatabaseHelper myDB;
    EditText etStory;
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        myDB = new DatabaseHelper(this);

        etStory= (EditText) findViewById(R.id.et_Story);
        btnsave = (Button) findViewById(R.id.btn_Story);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String newEntry = etStory.getText().toString();
                if (etStory.length() != 0)
                {
                    AddData(newEntry);
                    Intent i = new Intent(Story.this, MainActivity.class);
                    startActivity(i);

                    Story.super.onDestroy();
                }
                else
                {
                    Toast.makeText(Story.this, "You must write something in the Story", Toast.LENGTH_SHORT).show();
                }
            }}
        );
    }

    public void AddData(String newEntry)
    {
        boolean isinserted =  myDB.insertData(newEntry);

        if (isinserted == true)
        {
            Toast.makeText(Story.this, "Data is Inserted Successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(Story.this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
        }
    }
}