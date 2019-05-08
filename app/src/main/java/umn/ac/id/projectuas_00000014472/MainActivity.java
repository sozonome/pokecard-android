package umn.ac.id.projectuas_00000014472;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import umn.ac.id.projectuas_00000014472.adapter.PokeCardAdapter;
import umn.ac.id.projectuas_00000014472.helper.LoginDatabaseHelper;
import umn.ac.id.projectuas_00000014472.model.PokeCard;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<PokeCard> pokeCards;

    private String URL = "https://api.pokemontcg.io/v1/cards";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.cards_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        pokeCards = new ArrayList<>();

        loadCard();
    }

    void loadCard(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Capturing some\nPokemon for you . . . \n\nPlease be patient...\nit may take a while");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.GET,
                URL,  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try{
                    JSONObject cards = new JSONObject(response);
                    JSONArray array = cards.getJSONArray("cards");
                    for(int i=0; i<array.length(); i++){
                        JSONObject pokemon = array.getJSONObject(i);

                        String pokemon_name = pokemon.getString("name");
                        String card_image = pokemon.getString("imageUrl");
                        String card_image_hires = pokemon.getString("imageUrlHiRes");

                        String card_supertype = pokemon.getString("supertype");
                        String card_subtype = pokemon.getString("subtype");

                        String card_series = pokemon.getString("series");
                        String card_set = pokemon.getString("set");
                        String card_setkode = pokemon.getString("setCode");

                        PokeCard pokeCard = new PokeCard(pokemon_name,
                                card_image,
                                card_image_hires,
                                card_supertype,
                                card_subtype,
                                card_series,
                                card_set,
                                card_setkode
                        );

                        pokeCards.add(pokeCard);
                        
                        Log.d("Test Data Pokemon TCG", "Card Name : " + pokemon_name);
                        Log.d("Test Data Pokemon TCG", "Card Image URL : " + card_image);
                    }

                    adapter = new PokeCardAdapter(pokeCards, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.about_button:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.logout_button:
                LoginDatabaseHelper loginDatabaseHelper = new LoginDatabaseHelper(this);
                Cursor cursor = loginDatabaseHelper.getLoginSession();
                loginDatabaseHelper.killLoginSession(
                        cursor.getString(cursor.getColumnIndexOrThrow(LoginDatabaseHelper.TableColumns.user_username)),
                        cursor.getString(cursor.getColumnIndexOrThrow(LoginDatabaseHelper.TableColumns.user_password))
                );
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
