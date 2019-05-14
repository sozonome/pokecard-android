package id.ac.umn.AgustinusNathaniel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Activity Track", "Masuk AboutActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        LinearLayout about_me = findViewById(R.id.about_me);
        LinearLayout api_link = findViewById(R.id.api_link);

        about_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https:agustinusnathaniel.com"));
                startActivity(i);
            }
        });

        api_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pokemontcg.io/"));
                startActivity(i);
            }
        });
    }
}
