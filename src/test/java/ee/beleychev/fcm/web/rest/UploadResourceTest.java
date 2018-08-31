package ee.beleychev.fcm.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.beleychev.fcm.domain.FuelConsumptionRequest;
import ee.beleychev.fcm.repository.FuelConsumptionRequestRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.Instant;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author beleychev
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UploadResourceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private FuelConsumptionRequestRepository requestRepository;

    @Before
    public void setUp() {
        requestRepository.deleteAll();
    }

    @Test
    public void newRequest() throws Exception {
        FuelConsumptionRequest request = new FuelConsumptionRequest();
        request.setDate(Instant.now());
        request.setPrice(1f);
        request.setVolume(1f);
        request.setFuelType("D");
        request.setDriverId(1L);

        this.mockMvc.perform(post("/request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        int actualCount = requestRepository.findAll().size();
        int expectedCount = 1;

        List<FuelConsumptionRequest> savedEntity = requestRepository.findAll();

        String fuelTypeExpected = "D";
        String fuelTypeActual = savedEntity.get(0).getFuelType();

        assertEquals(expectedCount, actualCount);
        assertEquals(fuelTypeExpected, fuelTypeActual);
    }

    @Test
    public void handleFileUpload() throws Exception {
        byte[] content = ("[{\"fuelType\":\"D\",\"price\":\"20.1\",\"volume\":\"6.5\",\"date\":\"2018-07-23T18:25:43.511Z\",\"driverId\":\"2\"},"
                + "{\"fuelType\":\"D\",\"price\":\"10.1\",\"volume\":\"6.5\",\"date\":\"2018-07-23T18:25:43.511Z\",\"driverId\":\"1\"}]").getBytes();
        MockMultipartFile file = new MockMultipartFile("file", "test.json", "multipart/form-data", content);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders
                        .multipart("/")
                        .file(file);
        mockMvc.perform(builder).andExpect(status().isFound())
                .andDo(MockMvcResultHandlers.print());

        int actualCount = requestRepository.findAll().size();
        int expectedCount = 2;

        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void handleFileUploadWillNotSaveEntitiesIfMultipartIsNull() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders
                        .multipart("/");
        mockMvc.perform(builder).andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());

        int actualCount = requestRepository.findAll().size();
        int expectedCount = 0;

        assertEquals(expectedCount, actualCount);
    }

}