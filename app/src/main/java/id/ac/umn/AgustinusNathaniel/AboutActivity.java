package id.ac.umn.AgustinusNathaniel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Activity Track", "Masuk AboutActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
