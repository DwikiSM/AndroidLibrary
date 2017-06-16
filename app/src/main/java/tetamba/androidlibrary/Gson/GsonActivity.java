package tetamba.androidlibrary.Gson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import tetamba.androidlibrary.Gson.GsonModel.EarthquakeResponse;
import tetamba.androidlibrary.R;

/**
 * Created by MahdiFr on 14/06/2017.
 */

public class GsonActivity extends AppCompatActivity {
    final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2012-01-01&endtime=2012-12-01&minmagnitude=6";
    ArrayList<Earthquake> earthquakes = new ArrayList<>();
    EarthquakeAdapter earthquakeAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);

        EarthquakeQuery task = new EarthquakeQuery();
        task.execute();

        // Find a reference to the {@link ListView} in the layout
        ListView listView = (ListView) findViewById(R.id.gson_list);

        // Create a new {@link ArrayAdapter} of earthquakes
        earthquakeAdapter = new EarthquakeAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        listView.setAdapter(earthquakeAdapter);
    }

    // Update arrayList
    private synchronized void updateData(ArrayList<Earthquake> newEarthquake) {
        earthquakes = newEarthquake;

        earthquakeAdapter.clear();
        earthquakeAdapter.addAll(earthquakes);
        earthquakeAdapter.notifyDataSetChanged();
    }


    private class EarthquakeQuery extends AsyncTask<URL, Void, ArrayList<Earthquake>> {
        ArrayList<Earthquake> arrTemp = new ArrayList<>();

        @Override
        protected ArrayList<Earthquake> doInBackground(URL... params) {
            // Create URL object
            URL url = null;
            try{
                url = new URL(USGS_REQUEST_URL);
            }
            catch (MalformedURLException exception){
                Toast.makeText(GsonActivity.this, "Error URL", Toast.LENGTH_SHORT).show();
            }

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Toast.makeText(GsonActivity.this, "Error Http Request", Toast.LENGTH_SHORT).show();
            }

            // Extract Json using Gson
            extractFeatureFromJson(jsonResponse);

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return arrTemp;
        }

        protected void onPostExecute(ArrayList<Earthquake> newEarthquakes) {
            if (newEarthquakes == null) {
                return;
            }

            updateData(newEarthquakes);
        }

        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);

            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }

            return jsonResponse;
        }

        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        private void extractFeatureFromJson(String earthquakeJSON) {
            EarthquakeResponse earthquakeResponse = new Gson().fromJson(earthquakeJSON, EarthquakeResponse.class);
            SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm:ss z");

            if(earthquakeResponse != null){
                for(int i=0; i<earthquakeResponse.getFeatures().size(); i++){
                    String tempMagnitude = String.valueOf(earthquakeResponse.getFeatures().get(i).getProperties().getMagnitude());
                    String tempLocation = earthquakeResponse.getFeatures().get(i).getProperties().getLocation();
                    String tempDate = formatter.format(earthquakeResponse.getFeatures().get(i).getProperties().getDate());

                    Earthquake temp = new Earthquake(tempMagnitude, tempLocation, tempDate);
                    arrTemp.add(temp);
                }
            }
        }
    }
}
