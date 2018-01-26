package mobifun.myapplication2;



//some part copied from template pick from internet


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

public class EditStory extends AppCompatActivity
{
    private  static final String TAG = "EditStory";
    private Button btnSave, btnDelete;
    private EditText editText;

    DatabaseHelper myDB;
    private int selectedID;
    private String selectedtext;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_story);

        editText = (EditText) findViewById(R.id.et_edit_story);
        btnSave = (Button) findViewById(R.id.btn_save_edit_story);
        btnDelete = (Button) findViewById(R.id.btn_delete_edit_story);

        myDB = new DatabaseHelper(this);

        Intent received = getIntent();

        selectedID = received.getIntExtra("id", -1);
        selectedtext = received.getStringExtra("text");

        editText.setText(selectedtext);

        btnSave.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view)
                                       {
                                           String item = editText.getText().toString();
                                           if (!item.equals(""))
                                           {
                                               myDB.updateText(item, selectedID, selectedtext);
                                               Intent i = new Intent(EditStory.this, ReadDiary.class);
                                               Toast.makeText(EditStory.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                                               startActivity(i);
                                               EditStory.super.onDestroy();
                                           }
                                           else
                                           {
                                               Toast.makeText(EditStory.this, "You must write something in the Story", Toast.LENGTH_SHORT).show();
                                           }
                                       }
                                   }
        );

        btnDelete.setOnClickListener(new View.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View view)
                                         {
                                             String item = editText.getText().toString();
                                             if (!item.equals(""))
                                             {
                                                 myDB.deleteText(selectedID, selectedtext);
                                                 Intent i = new Intent(EditStory.this, ReadDiary.class);
                                                 Toast.makeText(EditStory.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                                                 startActivity(i);

                                                 EditStory.super.onDestroy();
                                             }
                                             else
                                             {
                                                 Toast.makeText(EditStory.this, "Something Wrong :(", Toast.LENGTH_SHORT).show();
                                             }
                                         }
                                     }
        );
    }
}
