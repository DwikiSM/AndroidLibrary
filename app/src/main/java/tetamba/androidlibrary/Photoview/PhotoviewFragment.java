package tetamba.androidlibrary.Photoview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;

import tetamba.androidlibrary.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoVIewFragment extends Fragment {

    public PhotoVIewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_view, container, false);
        PhotoView photoView = (PhotoView) view.findViewById(R.id.photoview_img);
        photoView.setImageResource(R.drawable.ic_photoview);

        return view;
    }

}
