package ee.beleychev.fcm.service;

import ee.beleychev.fcm.domain.FuelConsumptionRequest;
import ee.beleychev.fcm.domain.projection.MonthlyFuelRequestSummaryDTO;
import ee.beleychev.fcm.domain.projection.MonthlyFuelTypeSummaryDTO;
import ee.beleychev.fcm.domain.projection.TotalMonthFuelRequestSummaryDTO;
import ee.beleychev.fcm.repository.FuelConsumptionRequestRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author beleychev
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FuelConsumptionRequestServiceTest {
    private static boolean isSetupDone = false;
    @Autowired
    private FuelConsumptionRequestRepository requestRepository;
    @Autowired
    private FuelConsumptionRequestService requestService;

    /**
     * boolean flag is used because we don't need to clean or initialize DB for every test
     * {@link org.junit.BeforeClass} is not good because of it requires static method which is inconvenient
     */
    @Before
    public void setup() {
        if (isSetupDone) {
            return;
        }
        requestRepository.saveAll(getRequests());
        isSetupDone = true;
    }

    @Test
    public void getTotalGroupByMonth() {
        List<TotalMonthFuelRequestSummaryDTO> summaryDTOS = requestService.getTotalGroupByMonth();
        int sizeExpected = 4;
        int sizeActual = summaryDTOS.size();

        double totalExpected = 110;
        double totalActual = summaryDTOS.get(1).getTotal();

        assertEquals(sizeExpected, sizeActual);
        assertEquals(totalExpected, totalActual, 0);
    }

    @Test
    public void getTotalByDriverIdGroupByMonth() {
        List<TotalMonthFuelRequestSummaryDTO> summaryDTOS = requestService.getTotalByDriverIdGroupByMonth(2L);
        int sizeExpected = 2;
        int sizeActual = summaryDTOS.size();

        double totalExpected = 120;
        double totalActual = summaryDTOS.get(1).getTotal();

        assertEquals(sizeExpected, sizeActual);
        assertEquals(totalExpected, totalActual, 0);
    }

    @Test
    public void getFuelConsumptionRequestByMonth() {
        List<MonthlyFuelRequestSummaryDTO> summaryDTOS = requestService.getFuelConsumptionRequestByMonth(3);
        int sizeExpected = 2;
        int sizeActual = summaryDTOS.size();

        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getFuelConsumptionRequestByMonthAndDriverId() {
        List<MonthlyFuelRequestSummaryDTO> summaryDTOS = requestService.getFuelConsumptionRequestByMonthAndDriverId(3, 1L);
        int sizeExpected = 1;
        int sizeActual = summaryDTOS.size();

        double totalExpected = 75;
        double totalActual = summaryDTOS.get(0).getTotal();

        assertEquals(sizeExpected, sizeActual);
        assertEquals(totalExpected, totalActual, 0);
    }

    @Test
    public void getMonthStatisticByFuelType() {
        List<MonthlyFuelTypeSummaryDTO> summaryDTOS = requestService.getMonthStatisticByFuelType();
        int sizeExpected = 5;
        int sizeActual = summaryDTOS.size();

        assertEquals(sizeExpected, sizeActual);
    }

    @Test
    public void getMonthStatisticByFuelTypeAndByDriverId() {
        List<MonthlyFuelTypeSummaryDTO> summaryDTOS = requestService.getMonthStatisticByFuelTypeAndByDriverId(2L);
        int sizeExpected = 3;
        int sizeActual = summaryDTOS.size();

        assertEquals(sizeExpected, sizeActual);
    }

    private List<FuelConsumptionRequest> getRequests() {
        List<FuelConsumptionRequest> result = new ArrayList<>();
        FuelConsumptionRequest request1 = new FuelConsumptionRequest();
        request1.setDriverId(1L);
        request1.setFuelType("D");
        request1.setPrice(10f);
        request1.setVolume(1f);
        request1.setDate(Instant.parse("2018-01-11T09:15:30.00Z"));

        FuelConsumptionRequest request2 = new FuelConsumptionRequest();
        request2.setDriverId(2L);
        request2.setFuelType("95");
        request2.setPrice(20f);
        request2.setVolume(5f);
        request2.setDate(Instant.parse("2018-02-11T09:15:30.00Z"));

        FuelConsumptionRequest request3 = new FuelConsumptionRequest();
        request3.setDriverId(2L);
        request3.setFuelType("D");
        request3.setPrice(10f);
        request3.setVolume(1f);
        request3.setDate(Instant.parse("2018-02-11T09:15:30.00Z"));

        FuelConsumptionRequest request4 = new FuelConsumptionRequest();
        request4.setDriverId(1L);
        request4.setFuelType("95");
        request4.setPrice(25f);
        request4.setVolume(3f);
        request4.setDate(Instant.parse("2018-03-11T09:15:30.00Z"));

        FuelConsumptionRequest request5 = new FuelConsumptionRequest();
        request5.setDriverId(2L);
        request5.setFuelType("98");
        request5.setPrice(30f);
        request5.setVolume(4f);
        request5.setDate(Instant.parse("2018-03-12T09:15:30.00Z"));

        result.add(request1);
        result.add(request2);
        result.add(request3);
        result.add(request4);
        result.add(request5);

        return result;
    }
}