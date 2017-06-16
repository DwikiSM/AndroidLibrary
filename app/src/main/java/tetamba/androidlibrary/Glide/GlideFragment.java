package tetamba.androidlibrary.Glide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import tetamba.androidlibrary.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GlideFragment extends Fragment {

    @BindView(R.id.glide_img) ImageView glide_img;

    public GlideFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_glide, container, false);
        ButterKnife.bind(this, view);
        Glide.with(this).load(R.drawable.ic_glide_logo).into(glide_img);
        return view;
    }

}
