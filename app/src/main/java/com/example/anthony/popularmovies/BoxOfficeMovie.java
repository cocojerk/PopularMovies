package com.example.anthony.popularmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Anthony on 3/8/2017.
 */

public class BoxOfficeMovie implements Serializable {
    private static final long serialVersionUID = -8959832007991513854L;
    private String title;
    private String imageUrl;
    private String overview;
    private double vote_average;
    private String releaseDate;

    public String getTitle(){
        return title;
    }
    public String getImageUrl(){
        String completeImage = "http://image.tmdb.org/t/p/w185"+imageUrl;

        return completeImage;
    }
    public String getOverview(){
        return overview;
    }
    public double getVote_average(){
        return vote_average;
    }
    public String getReleaseDate(){
        return releaseDate;
    }
    public static BoxOfficeMovie fromJSON(JSONObject jsonObject){
        BoxOfficeMovie b = new BoxOfficeMovie();
        try{
            b.title = jsonObject.getString("title");
            b.imageUrl = jsonObject.getString("poster_path");
            b.overview = jsonObject.getString("overview");
            b.vote_average = jsonObject.getDouble("vote_average");
            b.releaseDate = jsonObject.getString("release_date");
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
        return b;

    }
    public static ArrayList<BoxOfficeMovie> fromJSON(JSONArray jsonArray){
        ArrayList<BoxOfficeMovie> movies = new ArrayList<BoxOfficeMovie>(jsonArray.length());
        for(int i = 0; i<jsonArray.length();i++){
            JSONObject moviesJson = null;
            try{
                moviesJson = jsonArray.getJSONObject(i);
                Log.i("qqq", "jsonobject accepted jsonarray");
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
            BoxOfficeMovie movie = BoxOfficeMovie.fromJSON(moviesJson);
            if(movie != null){
                movies.add(movie);
                Log.i("qqq", "moviesadded");
            }


        }
        return movies;
    }

}
