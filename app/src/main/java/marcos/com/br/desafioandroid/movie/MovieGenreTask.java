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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by marcos on 18/02/17.
 */

public class MovieGenreTask extends AsyncTask {


    @Override
    protected String doInBackground(Object[] params) {

        Map<String,String> mapGenres = getGenresMap();
        String genres = "";
        String movieGenres[] = ((String)(params[0])).replace("[", "").replace("]", "").split(",");
        for (int i = 0; i < movieGenres.length; i++) {
            genres+= ";"+mapGenres.get(movieGenres[i]);
        }

        return genres.replaceFirst(";", "");
    }


    public Map<String,String> getGenresMap(){
        Map<String,String> mapGenres = new HashMap<>();
        try {
            String URLBase = "https://api.themoviedb.org/3/genre/movie/list";
            String chave = "?api_key=43406930b0e490448fcbf403abc46623";
            URL url = new URL(URLBase + chave);
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
            JSONArray genresArray  = jsonObject.getJSONArray("genres");


            for (int i = 0; i < genresArray.length(); i++) {
                JSONObject genreJson = genresArray.getJSONObject(i);

                String id = genreJson.getString("id");
                String name = genreJson.getString("name");
                mapGenres.put(id, name);

            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return mapGenres;
    }
}
