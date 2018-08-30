package ee.beleychev.fcm.util;

import ee.beleychev.fcm.domain.FcmJsonParserException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @author beleychev
 */
public class FcmJsonParser {

    public static JSONArray getJsonFromFile(MultipartFile multipartFile) throws FcmJsonParserException {
        try {
            Reader reader = new InputStreamReader(multipartFile.getInputStream());
            JSONParser parser = new JSONParser();
            return (JSONArray) parser.parse(reader);
        }
        catch (IOException | ParseException e) {
            throw new FcmJsonParserException("Error while parsing file", e);
        }
    }

    private FcmJsonParser() {
    }
}
