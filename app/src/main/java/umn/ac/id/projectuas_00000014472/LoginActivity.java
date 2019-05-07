package umn.ac.id.projectuas_00000014472;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import umn.ac.id.projectuas_00000014472.helper.LoginDatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    EditText username_input, password_input;
    Button signin_button;

    LoginDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_input = findViewById(R.id.editText_username);
        password_input = findViewById(R.id.editText_password);
        signin_button = findViewById(R.id.signin_button);

        db = new LoginDatabaseHelper(this);

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_uname = username_input.getText().toString();
                String input_pass = password_input.getText().toString();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
