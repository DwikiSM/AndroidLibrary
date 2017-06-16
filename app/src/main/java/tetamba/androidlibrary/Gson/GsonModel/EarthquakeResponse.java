package tetamba.androidlibrary.Gson.GsonModel;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * Created by MahdiFr on 14/06/2017.
 */

public class EarthquakeResponse implements Serializable {
    @SerializedName("features")
    private List<EarthquakeFeatures> features;

    public List<EarthquakeFeatures> getFeatures(){
        return features;
    }
    public void setFeature(List<EarthquakeFeatures> listEarthquake){
        this.features = listEarthquake;
    }
}
