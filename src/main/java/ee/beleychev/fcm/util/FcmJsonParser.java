package ee.beleychev.fcm.util;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author beleychev
 */
public class FcmJsonParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(FcmJsonParser.class);

    public static JSONArray getJsonFromFile(Resource resource) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        return (JSONArray) parser.parse(new FileReader(resource.getFile()));
    }

    private FcmJsonParser() {
    }
}
