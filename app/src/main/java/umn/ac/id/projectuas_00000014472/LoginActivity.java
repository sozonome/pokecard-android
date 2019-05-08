package umn.ac.id.projectuas_00000014472;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import umn.ac.id.projectuas_00000014472.helper.LoginDatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    EditText username_input, password_input;
    Button signin_button;

    LoginDatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        db = new LoginDatabaseHelper(this);
//        if(db.checkSession()){
//            finish();
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }

        username_input = findViewById(R.id.editText_username);
        password_input = findViewById(R.id.editText_password);
        signin_button = findViewById(R.id.signin_button);

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                login();
            }
        });
    }

    private void login(){
//        String input_uname = username_input.getText().toString();
//        String input_pass = password_input.getText().toString();
//        if(input_uname.equals("") || input_pass.equals("")){
//            Toast.makeText(getApplicationContext(), "Form is still empty!", Toast.LENGTH_SHORT).show();
//        }else{
//            if(db.loginCheck(input_uname, input_pass)){
//                db.saveLoginSession(input_uname, input_pass);
//                finish();
//                Intent intent = new Intent(this, MainActivity.class);
//                Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//            }
//            else{
//                Toast.makeText(getApplicationContext(), "Wrong username / password, please try again.", Toast.LENGTH_SHORT).show();
//            }
//        }
    }
}
