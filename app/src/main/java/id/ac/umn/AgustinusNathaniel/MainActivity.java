package id.ac.umn.AgustinusNathaniel;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.ac.umn.AgustinusNathaniel.adapter.PokeCardAdapter;
import id.ac.umn.AgustinusNathaniel.model.PokeCard;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<PokeCard> pokeCards;

    private String URL = "https://api.pokemontcg.io/v1/cards";
    //secara default akan load 100 data.

    SharedPreferences login_pref;
    private static final String PREFERENCES_FILENAME = "login";
    private static final int PREFERENCES_MODE = Context.MODE_PRIVATE;
    private static final String KEY_USER = "USERNAME";
    private static final String KEY_PASS = "PASSWORD";
    private static final String LOGIN_KEY = "LOGGEDIN";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Activity Track", "Masuk MainActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_pref = getSharedPreferences(PREFERENCES_FILENAME, PREFERENCES_MODE);
        Boolean loggedinstatus = login_pref.getBoolean(LOGIN_KEY, false);

        if(!loggedinstatus){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        recyclerView = findViewById(R.id.cards_recycler_view);
        recyclerView.setHasFixedSize(true);

        int spanNumber;
        int orientation = this.getResources().getConfiguration().orientation;
        if(orientation==Configuration.ORIENTATION_PORTRAIT){
            spanNumber = 2;
        }else{
            spanNumber = 3;
        }

        recyclerView.setLayoutManager(new GridLayoutManager(this, spanNumber));

        pokeCards = new ArrayList<>();

        loadCard();
    }

    void loadCard(){
        final Dialog dialog = new AlertDialog.Builder(this).setView(R.layout.progressbar).create();
        dialog.show();

        StringRequest request = new StringRequest(Request.Method.GET,
                URL,  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
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

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
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
                final SharedPreferences.Editor login_pref_edit = login_pref.edit();
                login_pref_edit.putBoolean(LOGIN_KEY, false);
                login_pref_edit.apply();

                Toast.makeText(getApplicationContext(),"Logged Out!",Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
                finish();

                startActivity(getIntent());
                break;
            case R.id.app_bar_search:


        }
        return super.onOptionsItemSelected(item);
    }
}
