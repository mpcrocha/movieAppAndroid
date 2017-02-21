package marcos.com.br.desafioandroid.movie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos on 18/02/17.
 */

public class MoviePosterTask extends AsyncTask {


    @Override
    protected List<Bitmap> doInBackground(Object[] params) {
        List<String> moviesPosters = new ArrayList<>();
        List<Bitmap> moviesPostersBitmap = new ArrayList<>();
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
            JSONArray movies  = jsonObject.getJSONArray("results");

            for(int i = 0; i<movies.length(); i++){
                JSONObject movie = movies.getJSONObject(i);
                String title = movie.getString("poster_path");
                moviesPosters.add(title);
            }

            String URLBase = "http://image.tmdb.org/t/p/w185/";
            try {
                for(int i =0; i < moviesPosters.size();i++) {
                    Bitmap bit = BitmapFactory.decodeStream(
                            (InputStream)new URL(URLBase+moviesPosters.get(i)).getContent());
                    Bitmap sc = Bitmap.createScaledBitmap(bit, 400, 400, true);
                    moviesPostersBitmap.add(sc);

                }

            } catch (Exception e) {e.printStackTrace();}


        } catch (Exception e) {
            e.printStackTrace();
        }
        return moviesPostersBitmap;
    }
}
