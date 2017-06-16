package tetamba.androidlibrary.Retrofit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import tetamba.androidlibrary.Gson.Earthquake;
import tetamba.androidlibrary.R;
import tetamba.androidlibrary.Retrofit.RetrofitModel.Branch;

import static android.R.attr.button;
import static android.net.sip.SipErrorCode.TIME_OUT;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RetrofitFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RetrofitFragment extends Fragment {
    @BindView(R.id.test_button)
    Button test;

    private OnFragmentInteractionListener mListener;
    private RetrofitInterface retrofitInterface;

    public RetrofitFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.test_button)
    void clickButton(){
        retrofitInterface.getData().enqueue(new Callback<List<Earthquake>>() {
            @Override
            public void onResponse(Call<List<Earthquake>> call, Response<List<Earthquake>> response) {

            }

            @Override
            public void onFailure(Call<List<Earthquake>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_retrofit, container, false);
        ButterKnife.bind(this, view);
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient().newBuilder()
                        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create()).build();

        return view;
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
