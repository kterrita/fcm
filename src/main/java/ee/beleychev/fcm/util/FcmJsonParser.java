package ee.beleychev.fcm.util;

import ee.beleychev.fcm.exception.FcmJsonParserException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

/**
 * @author beleychev
 */
public class FcmJsonParser {

    @SuppressWarnings("unchecked")
    public static JSONArray getJsonFromFile(MultipartFile multipartFile) throws FcmJsonParserException {
        try {
            Objects.requireNonNull(multipartFile);
            Reader reader = new InputStreamReader(multipartFile.getInputStream());
            JSONParser parser = new JSONParser();
            Object o = parser.parse(reader);
            //in case of file contains single request without square brackets
            if (o instanceof JSONObject) {
                JSONArray array = new JSONArray();
                array.add(o);
                return array;
            }
            return (JSONArray) o;
        }
        catch (IOException | ParseException e) {
            throw new FcmJsonParserException("Error while parsing file", e);
        }
    }

    private FcmJsonParser() {
    }
}
