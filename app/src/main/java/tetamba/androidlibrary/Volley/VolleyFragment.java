package tetamba.androidlibrary.Volley;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import tetamba.androidlibrary.Gson.Earthquake;
import tetamba.androidlibrary.Gson.GsonModel.EarthquakeResponse;
import tetamba.androidlibrary.R;

/**
 * Created by MahdiFr on 15/06/2017.
 */

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VolleyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class VolleyFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2012-01-01&endtime=2012-12-01&minmagnitude=8";
    private Earthquake earthquake = new Earthquake();
    private ArrayList<Earthquake> earthquakes = new ArrayList<>();
    VolleyAdapter volleyAdapter = null;

    // Constructor
    public VolleyFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volley, container, false);
        getData();

        ListView listView = (ListView) view.findViewById(R.id.volley_list);
        volleyAdapter = new VolleyAdapter(this.getActivity(), earthquakes);
        listView.setAdapter(volleyAdapter);

        return view;
    }

    private synchronized void updateData(ArrayList<Earthquake> newData) {
        earthquakes = newData;

        volleyAdapter.clear();
        volleyAdapter.addAll(earthquakes);
        volleyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void getData(){
        final SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm:ss z");
        JsonObjectRequest jsonObj = new JsonObjectRequest(JsonRequest.Method.GET, USGS_REQUEST_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                EarthquakeResponse earthquakeResponse = null;
                try {
                    /*
                    * This is Gson
                    * Ignore this if you're not yet learning gson
                    */
                    earthquakeResponse = new Gson().fromJson(response.toString(), EarthquakeResponse.class);
                }
                catch (JsonParseException e){
                    e.printStackTrace();
                }

                ArrayList<Earthquake> newArray = new ArrayList<>();
                if(earthquakeResponse != null){
                    for(int i=0; i<earthquakeResponse.getFeatures().size(); i++){
                        String tempMagnitude = String.valueOf(earthquakeResponse.getFeatures().get(0).getProperties().getMagnitude());
                        String tempLocation = earthquakeResponse.getFeatures().get(0).getProperties().getLocation();
                        String tempDate = formatter.format(earthquakeResponse.getFeatures().get(0).getProperties().getDate());

                        earthquake.setmMagnitude(tempMagnitude);
                        earthquake.setmLocation(tempLocation);
                        earthquake.setmDate(tempDate);
                        newArray.add(earthquake);
                    }
                }
                updateData(newArray);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        NetworkSingleton.getInstance(getActivity()).addToRequestQueue(jsonObj);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
