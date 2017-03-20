package com.example.anthony.popularmovies;

import android.util.Log;
import cz.msebera.android.httpclient.Header;
import com.loopj.android.http.*;

import org.json.JSONObject;


/**
 * Created by Anthony on 3/8/2017.
 */

public class MoviesDbClient {
    private final String API_KEY = "";
    private final String API_BASE_URL ="https://api.themoviedb.org/3/movie/";
    private AsyncHttpClient client;
    public  void getPopularMovies(JsonHttpResponseHandler handler){
        String url = getApiUrl("popular?api_key="+API_KEY);
       // RequestParams params = new RequestParams("api_key", API_KEY);
        client.get(url,handler);


    }
    public void getTopRatedMovies(JsonHttpResponseHandler handler){
        String url = getApiUrl("top_rated?api_key="+API_KEY);
        client.get(url,handler);

    }

    public MoviesDbClient(){
        this.client = new AsyncHttpClient();
    }
    private String getApiUrl(String relativeUrl){
        return API_BASE_URL + relativeUrl;
    }


}
