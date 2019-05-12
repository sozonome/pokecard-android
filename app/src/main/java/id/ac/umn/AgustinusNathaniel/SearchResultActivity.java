package id.ac.umn.AgustinusNathaniel;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {

    TextView textViewSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        textViewSearchResult = findViewById(R.id.textViewSearchResult);
        if(Intent.ACTION_SEARCH.equals(getIntent().getAction())){
            handleSearch(getIntent().getStringExtra(SearchManager.QUERY));
        }
    }

    private void handleSearch(String searchQuery){
        textViewSearchResult.setText(searchQuery);
    }
}
