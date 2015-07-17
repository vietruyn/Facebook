package info.androidhive.listviewfeed.checkin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.adapter.ListLocationArrayAdapter;
import info.androidhive.listviewfeed.model.Blog;
import info.androidhive.listviewfeed.model.Result;

/**
 * Created by Rom on 4/14/2015.
 */
public class ListLocation extends Activity implements ListLocationArrayAdapter.OnItemClickToMap {

    public static final String URL =
            "https://maps.googleapis.com/maps/api/place/search/json?" +
                    "location=16.0716797,108.2265868&radius=10000&rankBy=distance&types=gas_station&sensor" +
                    "=false&key=AIzaSyBOsmMFSMPua1Lz4ke2fx1X600H6O41ap8";

    private ListView mListView;
    private ListLocationArrayAdapter mAdapter;
    private ProgressDialog mProgress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3b5998")));
        setContentView(R.layout.fragment_list_station);


        mListView = (ListView) findViewById(R.id.lvShow);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListLocation.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        new SimpleTask().execute(URL);

     }

    @Override
    public void toShowMap(double lng, double lat) {
        MapPlaces.latStation = lat;
        MapPlaces.lngStation = lng;
        Intent intent = new Intent(this, MapPlaces.class);
        startActivity(intent);

    }

    private class SimpleTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar
            mProgress = new ProgressDialog(ListLocation.this);
            mProgress.setTitle("Loading Map ...");
            mProgress.setMessage("Please wait...");
            mProgress.setCancelable(true);
            mProgress.show();
        }

        protected String doInBackground(String... urls) {
            String result = "";
            try {

                HttpGet httpGet = new HttpGet(urls[0]);
                HttpClient client = new DefaultHttpClient();

                HttpResponse response = client.execute(httpGet);

                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            return result;
        }

        protected void onPostExecute(String jsonString) {
            // Dismiss ProgressBar
            showData(jsonString);
            mProgress.hide();
        }
    }

    private void showData(String jsonString) {
        Gson gson = new Gson();
        Blog blog = gson.fromJson(jsonString, Blog.class);
        List<Result> results = blog.getResults();

        mAdapter = new ListLocationArrayAdapter(this, results, this);
        mListView.setAdapter(mAdapter);
    }
}
