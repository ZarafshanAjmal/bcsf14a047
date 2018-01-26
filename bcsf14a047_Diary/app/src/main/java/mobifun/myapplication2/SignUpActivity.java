package mobifun.myapplication2;

import android.content.ContentValues;
import android.content.Intent;
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

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText mUserName = findViewById(R.id.email);
        final EditText mPass = findViewById(R.id.password);
        final EditText mRePass = findViewById(R.id.confirm_password);


        Button mSignup = findViewById(R.id.email_sign_up_button);
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(SignUpActivity.this, "abcccccc", Toast.LENGTH_SHORT).show();
                if (isEmailValid(mUserName.getText().toString())) {
                    if (mPass.getText().toString().equals(mRePass.getText().toString())) {
                        SignUp(mUserName.getText().toString(), mPass.getText().toString());
                    } else {
                        Toast.makeText(SignUpActivity.this, "Password does not Match!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mUserName.setError("Not a valid email address");
                }
            }
        });
    }

    private void SignUp(String user, String pass) {
        long result;
        DbHelper db = new DbHelper(this);
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TableData.TableInfo.USER_NAME, user);
        cv.put(TableData.TableInfo.PASSWORD, pass);
        result = sq.insert(TableData.TableInfo.TABLE_NAME_USER, null, cv);

        if (result > 0) {
            launchLoginActivity();
            Toast.makeText(this, "Signup Successfull", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Signup Fail", Toast.LENGTH_SHORT).show();
        }
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }
}
