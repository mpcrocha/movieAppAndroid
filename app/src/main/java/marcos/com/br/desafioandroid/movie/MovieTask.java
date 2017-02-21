package marcos.com.br.desafioandroid.movie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos on 18/02/17.
 */

public class MovieTask extends AsyncTask {


    @Override
    protected List<Movie> doInBackground(Object[] params) {
        List<Movie> movies = new ArrayList<>();
        try {
            URL url = new URL((String)params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream inStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }

            inStream.close();
            String json = sb.toString();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray moviesArray  = jsonObject.getJSONArray("results");

            String URLBase = "http://image.tmdb.org/t/p/w185/";
            for(int i = 0; i< moviesArray.length(); i++){
                JSONObject movieJson = moviesArray.getJSONObject(i);

                String title = movieJson.getString("title");
                String year = movieJson.getString("release_date").substring(0, 4);
                String genre = movieJson.getString("genre_ids");
                String duration = movieJson.getString("title");
                String summary = movieJson.getString("overview");
                String poster = movieJson.getString("poster_path");
                String rating = movieJson.getString("vote_average");
                Movie movie = new Movie(title, year, genre, duration,  summary,
                        poster, rating);
                movies.add(movie);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }
}
