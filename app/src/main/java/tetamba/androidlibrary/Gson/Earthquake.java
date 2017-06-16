package tetamba.androidlibrary.Gson;

/**
 * Created by MahdiFr on 14/06/2017.
 */

public class Earthquake {
    // Magnitude gempa
    private String mMagnitude;

    // Lokasi gempa
    private String mLocation;

    // Waktu kejadian gempa
    private String mDate;

    /*
    * Create a new Earthquake object.
    */
    public Earthquake(String vMag, String vLoc, String vTime)
    {
        mMagnitude = vMag;
        mLocation = vLoc;
        mDate = vTime;
    }
    public Earthquake() {
        mMagnitude = null;
        mLocation = null;
        mDate = null;
    }

    /**
     * Getter & setter attributes
     */
    public String getMagnitude() {
        return mMagnitude;
    }
    public void setmMagnitude(String mMagnitude) {
        this.mMagnitude = mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }
    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getDate() {
        return mDate;
    }
    public void setmDate(String mDate) {
        this.mDate = mDate;
    }
}
