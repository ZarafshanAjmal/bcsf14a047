package mobifun.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


//some part copied from template pick from internet

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mTodayStory = findViewById(R.id.writeTodayStory);
        mTodayStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAboutYourself();
            }
        });

        Button story = findViewById(R.id.Story);
        story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchStory();
            }
        });

        Button read = findViewById(R.id.ReadDiary);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchReadDiary();
            }
        });
    }


    private void launchAboutYourself() {

        Intent intent = new Intent(this,AboutYourself.class);
        startActivity(intent);
    }

    private void launchStory()
    {
        Intent intent = new Intent(this,Story.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void launchReadDiary()
    {
        Intent intent = new Intent(this,ReadDiary.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
