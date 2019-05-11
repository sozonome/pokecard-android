package id.ac.umn.AgustinusNathaniel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.ac.umn.AgustinusNathaniel.helper.LoginDatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    EditText username_input, password_input;
    Button signin_button;

    LoginDatabaseHelper loginDatabaseHelper;

    SharedPreferences sharedPreferences;
    private static final String PREFERENCES_FILENAME = "login";
    private static final int PREFERENCES_MODE = Context.MODE_PRIVATE;
    private static final String KEY_USER = "USERNAME";
    private static final String KEY_PASS = "PASSWORD";
    private static final String LOGIN_KEY = "LOGGEDIN";

    private static final int REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences(PREFERENCES_FILENAME, PREFERENCES_MODE);

        Boolean loggedinstatus = sharedPreferences.getBoolean(LOGIN_KEY, false);

        if(loggedinstatus){
            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }

        final SharedPreferences.Editor editor = sharedPreferences.edit();
        String pref_user = sharedPreferences.getString(KEY_USER, null);
        String pref_pass = sharedPreferences.getString(KEY_PASS, null);

        loginDatabaseHelper = new LoginDatabaseHelper(this);

        username_input = findViewById(R.id.editText_username);
        username_input.setText(pref_user);
        password_input = findViewById(R.id.editText_password);
        password_input.setText(pref_pass);
        signin_button = findViewById(R.id.signin_button);

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username_input.getText().toString();
                String pass = password_input.getText().toString();

                if(loginDatabaseHelper.loginCheck(uname, pass)){
                    editor.putString(KEY_USER, uname);
                    editor.putString(KEY_PASS, pass);
                    editor.putBoolean(LOGIN_KEY, true);

                    Toast.makeText(getApplicationContext(),"Login Success!",Toast.LENGTH_LONG).show();
                    editor.apply();
                    setResult(RESULT_OK);
                    finish();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }else{
                    Toast.makeText(getApplicationContext(),"Wrong Credentials.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
