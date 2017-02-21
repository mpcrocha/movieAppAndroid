package marcos.com.br.desafioandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import marcos.com.br.desafioandroid.movie.Movie;
import marcos.com.br.desafioandroid.movie.MovieGenreTask;
import marcos.com.br.desafioandroid.movie.OneMoviePosterTask;

/**
 * An activity representing a single movie detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link movieListActivity}.
 */
public class movieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_movie_detail);
        setContentView(R.layout.movie_details);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        //setSupportActionBar(toolbar);
        Movie movie =  (Movie)getIntent().getSerializableExtra("Movie");
        //movie.getduration();
        OneMoviePosterTask oneMoviePoster = new OneMoviePosterTask();
        Bitmap moviePoster = null;
        try {
            moviePoster = (Bitmap) oneMoviePoster.execute(movie.getposter()).get();


        TextView movieNameView = (TextView)findViewById(R.id.movieName);
        movieNameView.setText (movie.gettitle());
        ImageView moviePosterView = (ImageView) findViewById(R.id.moviePosterDetail);
        moviePosterView.setImageBitmap(moviePoster);

        TextView movieYearView = (TextView)  findViewById(R.id.movieYear);
        movieYearView.setText(movie.getyear());

        TextView movieName2View = (TextView)  findViewById(R.id.movieName2);
        movieName2View.setText(movie.gettitle());

        MovieGenreTask movieGenreTask = new MovieGenreTask();
        String movieGenres = (String) movieGenreTask.execute(movie.getgenre()).get();
        TextView movieGenreView = (TextView)  findViewById(R.id.movieGenre);
        movieGenreView.setText(movieGenres);

        TextView movieDuration = (TextView)  findViewById(R.id.movieDuration);
        movieDuration.setText(movie.getduration());

        RatingBar movieRating = (RatingBar)  findViewById(R.id.movieRating);
        movieRating.setNumStars((int)(new Float(movie.getRating())/2.0));

        TextView movieSummary = (TextView)  findViewById(R.id.movieSummary);
        movieSummary.setText(movie.getsummary());

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        /*if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(movieDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(movieDetailFragment.ARG_ITEM_ID));
            movieDetailFragment fragment = new movieDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, movieListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
