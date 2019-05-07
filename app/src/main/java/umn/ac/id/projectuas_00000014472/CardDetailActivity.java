package umn.ac.id.projectuas_00000014472;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import umn.ac.id.projectuas_00000014472.adapter.PokeCardAdapter;

public class CardDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        //Layout
        TextView card_name = findViewById(R.id.card_name);
        ImageView card_image = findViewById(R.id.card_image);

        TextView card_supertype = findViewById(R.id.card_supertype);
        TextView card_subtype = findViewById(R.id.card_subtype);

        TextView card_series = findViewById(R.id.card_series);
        TextView card_set = findViewById(R.id.card_set);
        TextView card_setcode = findViewById(R.id.card_setcode);

        //Pull from Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra(PokeCardAdapter.KEY_NAME);
        String image = intent.getStringExtra(PokeCardAdapter.KEY_IMAGE);

        String supertype = intent.getStringExtra(PokeCardAdapter.KEY_SUPERTYPE);
        String subtype = intent.getStringExtra(PokeCardAdapter.KEY_TYPE);

        String series = intent.getStringExtra(PokeCardAdapter.KEY_SERIES);
        String set = intent.getStringExtra(PokeCardAdapter.KEY_SET);
        String setcode = intent.getStringExtra(PokeCardAdapter.KEY_SETCODE);

        //Put into layout
        card_name.setText(name);
        Picasso.with(this)
                .load(image)
                .into(card_image);

        card_supertype.setText(supertype);
        card_subtype.setText(subtype);

        card_series.setText(series);
        card_set.setText(set);
        card_setcode.setText(setcode);
    }
}
