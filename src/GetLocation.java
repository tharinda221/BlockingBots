import java.io.File;
import java.io.IOException;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;


public class GetLocation {


    public ServerLocation getLocation(String ipAddress) {

        File file = new File(
                "GeoLiteCity.dat");
        return getLocation(ipAddress, file);

    }

    public ServerLocation getLocation(String ipAddress, File file) {

        ServerLocation serverLocation = null;

        try {

            serverLocation = new ServerLocation();

            LookupService lookup = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);
            Location locationServices = lookup.getLocation(ipAddress);
            new FindLocation(String.valueOf(locationServices.latitude),String.valueOf(locationServices.longitude),ipAddress);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return serverLocation;
    }
}
