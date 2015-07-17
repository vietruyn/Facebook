package info.androidhive.listviewfeed.model;

        import java.io.Serializable;

/**
 * Created by Rom on 4/20/2015.
 */
public class Geometry implements Serializable {
    private Location location;

    public Location getLocation(String lat) {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
