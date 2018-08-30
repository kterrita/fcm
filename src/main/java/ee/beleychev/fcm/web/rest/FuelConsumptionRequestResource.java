package ee.beleychev.fcm.web.rest;

import ee.beleychev.fcm.domain.projection.MonthlyFuelRequestSummaryDTO;
import ee.beleychev.fcm.domain.projection.MonthlyFuelTypeSummaryDTO;
import ee.beleychev.fcm.domain.projection.TotalMonthFuelRequestSummaryDTO;
import ee.beleychev.fcm.service.FuelConsumptionRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Fuel consumption requests controller
 *
 * @author beleychev
 */
@RestController
@RequestMapping("/api")
public class FuelConsumptionRequestResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(FuelConsumptionRequestResource.class);
    private FuelConsumptionRequestService fuelConsumptionRequestService;

    public FuelConsumptionRequestResource(FuelConsumptionRequestService fuelConsumptionRequestRepository) {
        this.fuelConsumptionRequestService = fuelConsumptionRequestRepository;
    }

    /**
     * Get /month-total-report: Gets total spent amount of money grouped by month
     *
     * @return the ResponseEntity with status 200 (OK) and the list of {@link TotalMonthFuelRequestSummaryDTO} in body
     */
    @GetMapping(value = "/month-total-report")
    public List<TotalMonthFuelRequestSummaryDTO> getMonthStatistic() {
        List<TotalMonthFuelRequestSummaryDTO> summaries = fuelConsumptionRequestService.getTotalGroupByMonth();
        summaries.forEach(request -> LOGGER.info("{}", request));
        return summaries;
    }

    /**
     * Get /month-total-report/driver/{driverId}: Gets total spent amount of money grouped by month for specified driver id
     *
     * @param driverId driverId
     * @return the ResponseEntity with status 200 (OK) and the list of {@link TotalMonthFuelRequestSummaryDTO} in body
     */
    @GetMapping(value = "/month-total-report/driver/{driverId}")
    public List<TotalMonthFuelRequestSummaryDTO> getMonthStatisticForDriver(@PathVariable Long driverId) {
        List<TotalMonthFuelRequestSummaryDTO> summaries = fuelConsumptionRequestService.getTotalByDriverIdGroupByMonth(driverId);
        summaries.forEach(request -> LOGGER.info("{}", request));
        return summaries;
    }

    /**
     * Get /month-report/{month}: Gets list fuel consumption records for specified month
     *
     * @param month month number
     * @return the ResponseEntity with status 200 (OK) and the list of {@link TotalMonthFuelRequestSummaryDTO} in body
     */
    @GetMapping(value = "/month-report/{month}")
    public List<MonthlyFuelRequestSummaryDTO> getMonthStatistic(@PathVariable Integer month) {
        List<MonthlyFuelRequestSummaryDTO> summaries = fuelConsumptionRequestService.getFuelConsumptionRequestByMonth(month);
        summaries.forEach(request -> LOGGER.info("{}", request));
        return summaries;
    }

    /**
     * Get /month-report/{month}/driver/{driverId}: Gets list fuel consumption records for specified month for specified driver
     *
     * @param month    month number
     * @param driverId driverId
     * @return the ResponseEntity with status 200 (OK) and the list of {@link TotalMonthFuelRequestSummaryDTO} in body
     */
    @GetMapping(value = "/month-report/{month}/driver/{driverId}")
    public List<MonthlyFuelRequestSummaryDTO> getMonthStatisticForDriver(@PathVariable Integer month, @PathVariable Long driverId) {
        List<MonthlyFuelRequestSummaryDTO> summaries = fuelConsumptionRequestService.getFuelConsumptionRequestByMonthAndDriverId(month, driverId);
        summaries.forEach(request -> LOGGER.info("{}", request));
        return summaries;
    }

    /**
     * Get /fuel-type-report: Gets statistic for each month, list fuel consumption records grouped by fuel type
     *
     * @return the ResponseEntity with status 200 (OK) and the list of {@link TotalMonthFuelRequestSummaryDTO} in body
     */
    @GetMapping(value = "/fuel-type-report")
    public List<MonthlyFuelTypeSummaryDTO> getFuelTypeMonthStatistic() {
        List<MonthlyFuelTypeSummaryDTO> summaries = fuelConsumptionRequestService.getMonthStatisticByFuelType();
        summaries.forEach(request -> LOGGER.info("{}", request));
        return summaries;
    }

    /**
     * Get /fuel-type-report/driver/{driverId}: Gets statistic for each month, list fuel consumption records grouped by fuel type for specified driver
     *
     * @param driverId driverId
     * @return the ResponseEntity with status 200 (OK) and the list of {@link TotalMonthFuelRequestSummaryDTO} in body
     */
    @GetMapping(value = "/fuel-type-report/driver/{driverId}")
    public List<MonthlyFuelTypeSummaryDTO> getFuelTypeMonthStatisticForDriver(@PathVariable Long driverId) {
        List<MonthlyFuelTypeSummaryDTO> summaries = fuelConsumptionRequestService.getMonthStatisticByFuelTypeAndByDriverId(driverId);
        summaries.forEach(request -> LOGGER.info("{}", request));
        return summaries;
    }

}
