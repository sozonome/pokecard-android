package umn.ac.id.projectuas_00000014472;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    private int time = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent splashscreen = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(splashscreen);
        finish();
    }
}
