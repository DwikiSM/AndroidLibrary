package tetamba.androidlibrary.Volley;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import tetamba.androidlibrary.Gson.Earthquake;
import tetamba.androidlibrary.R;

/**
 * Created by MahdiFr on 15/06/2017.
 */

public class VolleyAdapter extends ArrayAdapter<Earthquake> {
    public VolleyAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for three TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.volley_list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake earthquake = getItem(position);

        // Find the TextView in the gson_list_item.xml layout with the ID version_name
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.volley_magnitude);
        // set this text on the name TextView
        magnitudeTextView.setText(earthquake.getMagnitude());

        // Find the TextView in the gson_list_item.xml layout with the ID version_number
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.volley_location);
        // set this text on the number TextView
        locationTextView.setText(earthquake.getLocation());

        // Find the ImageView in the gson_list_item.xml layout with the ID list_item_icon
        TextView dateView = (TextView) listItemView.findViewById(R.id.volley_date);
        // set the image to TextView
        dateView.setText(earthquake.getDate());

        // Return the whole list item layout
        // so that it can be shown in the ListView
        return listItemView;
    }
}
