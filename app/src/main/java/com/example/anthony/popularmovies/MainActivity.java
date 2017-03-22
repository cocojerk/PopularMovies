package com.example.anthony.popularmovies;

import com.loopj.android.http.AsyncHttpClient;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.entity.mime.Header;

public class MainActivity extends AppCompatActivity {
    private GridView lvMovies;
    private BoxOfficeMovieAdapter adapterMovies;
    public static final String MOVIE_DETAIL_KEY = "movie";
    MoviesDbClient client;
    ArrayList<BoxOfficeMovie> aMovies = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMovies = (GridView)findViewById(R.id.lvMovies);
        if(isOnline()==true) {
            adapterMovies = new BoxOfficeMovieAdapter(this, aMovies);
            lvMovies.setAdapter(adapterMovies);

            adapterMovies.notifyDataSetChanged();
            try {


                fetchBoxOfficeMovies();


            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {


                fetchTopRated();

            } catch (JSONException e) {
                e.printStackTrace();
            }


            setupMovieSelectedListener();

        }
        else{
            Toast toast =Toast.makeText(this,"No internet connection",Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public void setupMovieSelectedListener() {
        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View item, int position, long rowID) {
                Intent d = new Intent(MainActivity.this, Detail.class);
                d.putExtra(MOVIE_DETAIL_KEY, adapterMovies.getItem(position));
                startActivity(d);
            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int counter = 0;
        if(isOnline()==true){
        switch(item.getItemId()){
            case R.id.Popular:


                    try {
                        adapterMovies.clear();

                        fetchBoxOfficeMovies();
                        adapterMovies.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                return true;
            case R.id.top_rated:

                try{
                    adapterMovies.clear();

                    fetchTopRated();
                    adapterMovies.notifyDataSetChanged();
                }catch (JSONException e){
                    e.printStackTrace();
                }
                return true;


        }
        }
        else{
            Toast toast =Toast.makeText(this,"No internet connection",Toast.LENGTH_LONG);
            toast.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void fetchBoxOfficeMovies()throws JSONException{
        client = new MoviesDbClient();
        Log.i("qqq", "fetchpop");
        client.getPopularMovies(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                JSONArray items = null;
                try{
                    items = response.getJSONArray(0);
                    ArrayList<BoxOfficeMovie> movies = BoxOfficeMovie.fromJSON(items);
                    for (BoxOfficeMovie movie : movies) {
                        adapterMovies.add(movie); // add movie through the adapter
                    }
                    adapterMovies.notifyDataSetChanged();
                    Log.i("qqq", items.toString());
                }catch (JSONException e){
                    e.printStackTrace();
                }
                }


            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                JSONArray items = null;
                Log.i("qqq", "fetchboxsucess");
                try{
                    items = response.getJSONArray("results");
                    ArrayList<BoxOfficeMovie> movies = BoxOfficeMovie.fromJSON(items);
                    for (BoxOfficeMovie movie : movies) {
                        adapterMovies.add(movie); // add movie through the adapter
                    }
                    adapterMovies.notifyDataSetChanged();
                    Log.i("qqqq", items.toString());
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

        });


    }
    public void fetchTopRated()throws JSONException{
        client = new MoviesDbClient();
        Log.i("qqq", "fetchtop");
        client.getTopRatedMovies(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                JSONArray items = null;
                try{
                    items = response.getJSONArray(0);
                    ArrayList<BoxOfficeMovie> movies = BoxOfficeMovie.fromJSON(items);
                    adapterMovies.addAll(movies);
                    adapterMovies.notifyDataSetChanged();
                    Log.i("qqq", items.toString());
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }


            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                JSONArray items = null;
                Log.i("qqq", "fetchboxsucess");
                try{
                    items = response.getJSONArray("results");
                    ArrayList<BoxOfficeMovie> movies = BoxOfficeMovie.fromJSON(items);
                    adapterMovies.addAll(movies);
                    adapterMovies.notifyDataSetChanged();
                    Log.i("qqqq", items.toString());
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

        });


    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public class movieData{

    }

}
