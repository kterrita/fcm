package ee.beleychev.fcm.util;

import ee.beleychev.fcm.exception.FcmJsonParserException;
import org.json.simple.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.Assert.*;

/**
 * @author beleychev
 */
public class FcmJsonParserTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getJsonFromFile() throws FcmJsonParserException {
        byte[] content = ("[{\"fuelType\":\"D\",\"price\":\"20.1\",\"volume\":\"6.5\",\"date\":\"2018-07-23T18:25:43.511Z\",\"driverId\":\"2\"},"
                + "{\"fuelType\":\"D\",\"price\":\"10.1\",\"volume\":\"6.5\",\"date\":\"2018-07-23T18:25:43.511Z\",\"driverId\":\"1\"}]").getBytes();
        MockMultipartFile file = new MockMultipartFile("file", "test.json", "multipart/form-data", content);

        JSONArray array = FcmJsonParser.getJsonFromFile(file);

        int sizeExpected = 2;
        int sizeActual = array.size();

        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getJsonFromFileSingleJSON() throws FcmJsonParserException {
        byte[] content = ("{\"fuelType\":\"D\",\"price\":\"20.1\",\"volume\":\"6.5\",\"date\":\"2018-07-23T18:25:43.511Z\",\"driverId\":\"2\"}").getBytes();
        MockMultipartFile file = new MockMultipartFile("file", "test.json", "multipart/form-data", content);

        JSONArray array = FcmJsonParser.getJsonFromFile(file);

        int sizeExpected = 1;
        int sizeActual = array.size();

        assertEquals(sizeExpected, sizeActual);
    }

    @Test(expected = FcmJsonParserException.class)
    public void getJsonFromFileThrowExceptionInCaseOfPasringError() throws FcmJsonParserException {
        byte[] content = ("[{\"fuelType\":\"D\",\"price\":\"20.1\",\"volume\":\"6.5\",\"date\":\"2018-07-23T18:25:43.511Z\",\"driverId\":\"2\"}").getBytes();
        MockMultipartFile file = new MockMultipartFile("file", "test.json", "multipart/form-data", content);

        FcmJsonParser.getJsonFromFile(file);
    }
}