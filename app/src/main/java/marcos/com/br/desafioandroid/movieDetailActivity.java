package marcos.com.br.desafioandroid;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
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

        setContentView(R.layout.movie_details);
        Movie movie =  (Movie)getIntent().getSerializableExtra("Movie");
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
    }
}