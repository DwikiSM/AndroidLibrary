package tetamba.androidlibrary.Gson.GsonModel;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by MahdiFr on 14/06/2017.
 */

public class EarthquakeProperties implements Serializable{
    // Magnitude gempa
    @SerializedName("mag")
    private Number mMagnitude;
    // Lokasi gempa
    @SerializedName("place")
    private String mLocation;
    // Waktu kejadian gempa
    @SerializedName("time")
    private Number mDate;

    public float getMagnitude() {
        return mMagnitude.floatValue();
    }
    public void setmMagnitude(Number mMagnitude) {
        this.mMagnitude = mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }
    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public int getDate() {
        return mDate.intValue();
    }
    public void setmDate(Number mDate) { this.mDate = mDate; }
}
