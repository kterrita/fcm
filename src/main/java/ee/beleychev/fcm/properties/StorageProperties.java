package ee.beleychev.fcm.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author beleychev
 */

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-files";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
