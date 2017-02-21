package marcos.com.br.desafioandroid.movie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.JsonReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MovieContent  {
    static MovieTask movieTask = new MovieTask();
    /**
     * An array of sample (dummy) items.
     */
    public static final List<MovieItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, MovieItem> ITEM_MAP = new HashMap<>();


    static {
        List<Movie> movies = getMovies();
        List<Bitmap> posters = getPopularMoviesPosters();
        // Add some sample items.

        for (int i = 1; i < posters.size(); i++) {
            addItem(createDummyItem(i, movies.get(i) , posters.get(i)));
        }
    }

    private static MovieItem createDummyItem(int id, Movie movie,  Bitmap bitmap) {
        return new MovieItem(id, "", makeDetails(""), bitmap, movie);
    }

    private static List<Bitmap> getPopularMoviesPosters() {
        //String urlBase = "http://image.tmdb.org/t/p/" ;
        String urlBase = "http://api.themoviedb.org/3";
        String tamanho = "w185";
        String popularMoviesEndPoint = "/movie/popular";
        String chave = "?api_key=43406930b0e490448fcbf403abc46623";
        String urlPopularMovies = urlBase + popularMoviesEndPoint + chave;
        //JsonReader reader =  new JsonReader();
        //movieTask.delegate = this;
        List<String> popularMoviesNames = new ArrayList<>();
        //execute the async task
        List<Bitmap> moviesPoster = null;
        try {
            moviesPoster = (List<Bitmap>) new MoviePosterTask().execute(urlPopularMovies).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //List<String> popularMoviesNames =
        return moviesPoster;
    }

    private static List<Movie> getMovies() {
        //String urlBase = "http://image.tmdb.org/t/p/" ;
        String urlBase = "http://api.themoviedb.org/3";
        String tamanho = "w185";
        String popularMoviesEndPoint = "/movie/popular";
        String chave = "?api_key=43406930b0e490448fcbf403abc46623";
        String urlPopularMovies = urlBase + popularMoviesEndPoint + chave;
        //JsonReader reader =  new JsonReader();
        //movieTask.delegate = this;
        List<Movie> movies = new ArrayList<>();
        //execute the async task
        try {
            movies = (List<Movie>) movieTask.execute(urlPopularMovies).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //List<String> popularMoviesNames =
        return movies;
    }

    private static void addItem(MovieItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static MovieItem createDummyItem(String movieTitle) {
        return new MovieItem(0, movieTitle, makeDetails(movieTitle), null, null);
    }

    private static String makeDetails(String position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        //for (int i = 0; i < position; i++) {
        //    builder.append("\nMore details information here.");
        //}
        return builder.toString();
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class MovieItem {
        public final int id;
        public final String content;
        public final String details;
        public final Bitmap poster;
        public final Movie movie;

        public MovieItem(int id, String content, String details, Bitmap poster, Movie movie) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.poster = poster;
            this.movie = movie;
        }
    }
}
