package id.ac.umn.AgustinusNathaniel;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import id.ac.umn.AgustinusNathaniel.adapter.PokeCardAdapter;
import id.ac.umn.AgustinusNathaniel.model.PokeCard;

import static id.ac.umn.AgustinusNathaniel.app.App.CHANNEL_1_ID;


public class CardDetailActivity extends AppCompatActivity {
    TextView card_name;
    ImageView card_image;

    TextView card_supertype;
    TextView card_subtype;

    TextView card_series;
    TextView card_set;
    TextView card_setcode;

    FloatingActionButton floatingActionButton;

    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Activity Track", "Masuk CardDetailActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        //Layout
        card_name = findViewById(R.id.card_name);
        card_image = findViewById(R.id.card_image);

        card_supertype = findViewById(R.id.card_supertype);
        card_subtype = findViewById(R.id.card_subtype);

        card_series = findViewById(R.id.card_series);
        card_set = findViewById(R.id.card_set);
        card_setcode = findViewById(R.id.card_setcode);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        //Pull from Intent
        Intent intent = getIntent();
        final String name = intent.getStringExtra(PokeCardAdapter.KEY_NAME);
        final String image = intent.getStringExtra(PokeCardAdapter.KEY_IMAGE);

        String supertype = intent.getStringExtra(PokeCardAdapter.KEY_SUPERTYPE);
        String subtype = intent.getStringExtra(PokeCardAdapter.KEY_TYPE);

        String series = intent.getStringExtra(PokeCardAdapter.KEY_SERIES);
        String set = intent.getStringExtra(PokeCardAdapter.KEY_SET);
        String setcode = intent.getStringExtra(PokeCardAdapter.KEY_SETCODE);

        //Put into layout
        card_name.setText(name);
        Picasso.with(this)
                .load(image)
                .placeholder(R.drawable.loading_animation)
                .into(card_image);

        card_supertype.setText(supertype);
        card_subtype.setText(subtype);

        card_series.setText(series);
        card_set.setText(set);
        card_setcode.setText(setcode);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wait_duration = 5000;
                final Toast toast = Toast.makeText(getApplicationContext(), name + " registered. Please Wait...", Toast.LENGTH_LONG);

                CountDownTimer timer;
                timer = new CountDownTimer(wait_duration, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        toast.cancel();

                        String title = "Register Successful";
                        String message = name + " is registered";

                        notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                        Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_1_ID)
                                .setSmallIcon(R.drawable.ic_add)
                                .setContentTitle(title)
                                .setContentText(message)
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                .build();

                        notificationManagerCompat.notify(1, notification);
                    }
                };
                toast.show();
                timer.start();
            }
        });
    }
}
