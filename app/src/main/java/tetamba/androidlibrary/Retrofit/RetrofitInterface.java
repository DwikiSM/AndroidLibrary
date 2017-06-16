package tetamba.androidlibrary.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import tetamba.androidlibrary.Gson.Earthquake;

/**
 * Created by MahdiFr on 15/06/2017.
 */

public interface RetrofitInterface {
    @GET("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2012-01-01&endtime=2012-12-01&minmagnitude=8")
    Call<List<Earthquake>> getData();
}
