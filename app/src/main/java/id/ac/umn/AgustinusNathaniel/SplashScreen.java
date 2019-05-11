package id.ac.umn.AgustinusNathaniel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SplashScreen extends AppCompatActivity {
    private int time = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Activity Track", "Masuk SplashScreen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent splashscreen = new Intent(SplashScreen.this, LoginActivity.class);
        startActivity(splashscreen);
        finish();
    }
}
