package com.example.anthony.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {
    private ImageView ivPosterImage;
    private TextView tvTitle;
    private TextView tvSynopsis;
    private TextView tvRating;
    private TextView tvDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivPosterImage = (ImageView)findViewById(R.id.ivPosterImage);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvDate = (TextView)findViewById(R.id.tvReleaseDate);
        tvRating = (TextView)findViewById(R.id.tvCriticsScore);
        tvSynopsis = (TextView)findViewById(R.id.tvOverview);
        BoxOfficeMovie movie = (BoxOfficeMovie)
                getIntent().getParcelableExtra(MainActivity.MOVIE_DETAIL_KEY);
        loadMovie(movie);

    }

    private void loadMovie(BoxOfficeMovie movie) {
        tvTitle.setText(movie.getTitle());
        double a = movie.getVote_average();
        String average = String.valueOf(a);
        tvSynopsis.setText("Synopsis: " +movie.getOverview());
        tvRating.setText("Rating: " +average);
        tvDate.setText("Release Date: "+ movie.getReleaseDate());
        Picasso.with(this).load(movie.getImageUrl()).
                placeholder(R.drawable.large_movie_poster).
                into(ivPosterImage);

    }
}
