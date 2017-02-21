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

public class OneMoviePosterTask extends AsyncTask {


    @Override
    protected Bitmap doInBackground(Object[] params) {


        Bitmap sc = null;
        try {

            String URLBase = "http://image.tmdb.org/t/p/w185/";
            try {

                    Bitmap bit = BitmapFactory.decodeStream(
                            (InputStream)new URL(URLBase+params[0]).getContent());
                    sc = Bitmap.createScaledBitmap(bit, 400, 400, true);

            } catch (Exception e) {e.printStackTrace();}


        } catch (Exception e) {
            e.printStackTrace();
        }
        return sc;
    }
}
