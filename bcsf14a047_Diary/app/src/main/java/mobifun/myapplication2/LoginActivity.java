package mobifun.myapplication2;

/**
 * Created by 786 on 1/25/2018.
 */

//some part copied from template pick from internet
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mobifun.myapplication2.Data.DbHelper;
import mobifun.myapplication2.Data.TableData;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView =  findViewById(R.id.email);


        mPasswordView =  findViewById(R.id.password);



        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        TextView mEmailSignUp = findViewById(R.id.email_sign_up);
        mEmailSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSignupActivity();
            }
        });

    }








    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {


        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            authenticate(email, password);
            //  mAuthTask = new UserLoginTask(email, password);
            //  mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 2;
    }


    private void launchSignupActivity()
    {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    private void launchMainActivity()
    {
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void authenticate(String user , String pass)
    {

        String[] coloumns = {TableData.TableInfo._ID,
                TableData.TableInfo.USER_NAME,
                TableData.TableInfo.PASSWORD};

        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = TableData.TableInfo.USER_NAME +" = ? ";
        String[] args = new String[] {user};

        Cursor result = db.query(TableData.TableInfo.TABLE_NAME_USER,coloumns,selection,args,null,null,null);

        Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
        if(result.moveToFirst())
        {
            int name_index = result.getColumnIndex(TableData.TableInfo.USER_NAME);
            String name = result.getString(name_index);

            int pass_index = result.getColumnIndex(TableData.TableInfo.PASSWORD);
            String passV = result.getString(pass_index);


            Toast.makeText(this, name +" " + passV , Toast.LENGTH_SHORT).show();
            if(user.equals(name) && pass.equals(passV))
            {

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(TableData.TableInfo.USER_NAME,name);
                editor.commit();
                launchMainActivity();
            }
            else
            {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
