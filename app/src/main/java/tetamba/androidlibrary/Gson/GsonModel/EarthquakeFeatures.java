package tetamba.androidlibrary.Gson.GsonModel;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by MahdiFr on 14/06/2017.
 */

public class EarthquakeFeatures implements Serializable{
    @SerializedName("properties")
    private EarthquakeProperties properties;

    public EarthquakeProperties getProperties(){ return properties; }
    public void setProperties(EarthquakeProperties properties){ this.properties=properties; }
}
