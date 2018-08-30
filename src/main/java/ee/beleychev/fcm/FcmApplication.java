package ee.beleychev.fcm;

import ee.beleychev.fcm.properties.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FcmApplication {

    public static void main(String[] args) {
        SpringApplication.run(FcmApplication.class, args);
    }
}
