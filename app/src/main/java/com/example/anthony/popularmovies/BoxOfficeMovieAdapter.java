package com.example.anthony.popularmovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Anthony on 3/8/2017.
 */

public class BoxOfficeMovieAdapter extends ArrayAdapter<BoxOfficeMovie>{
    public BoxOfficeMovieAdapter(Context context, ArrayList<BoxOfficeMovie> aMovies){
        super(context, 0, aMovies);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        BoxOfficeMovie movie = getItem(position);
        Log.i("qqq", "getview");
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_box_office_movie, parent, false);
        }
        ImageView ivPosterImage = (ImageView) convertView.findViewById(R.id.ivPosterImage);
        Picasso.with(getContext()).load(movie.getImageUrl()).into(ivPosterImage);
        return convertView;
    }

}
