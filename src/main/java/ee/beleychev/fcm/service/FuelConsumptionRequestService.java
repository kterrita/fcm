package ee.beleychev.fcm.service;

import ee.beleychev.fcm.domain.projection.*;
import ee.beleychev.fcm.repository.FuelConsumptionRequestRepository;
import ee.beleychev.fcm.util.ProjectionConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author beleychev
 */
@Service
@Transactional
public class FuelConsumptionRequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FuelConsumptionRequestService.class);

    private FuelConsumptionRequestRepository fuelConsumptionRequestRepository;

    public FuelConsumptionRequestService(FuelConsumptionRequestRepository fuelConsumptionRequestRepository) {
        this.fuelConsumptionRequestRepository = fuelConsumptionRequestRepository;
    }

    /**
     * Gets total spent amount of money grouped by month
     *
     * @return list of total spent amount of money grouped by month
     */
    public List<TotalMonthFuelRequestSummaryDTO> getTotalGroupByMonth() {
        LOGGER.info("Request to total spent by month report.");
        List<TotalMonthFuelRequestSummary> summaries = fuelConsumptionRequestRepository.getTotalGroupByMonth();
        return summaries.stream()
                .map(ProjectionConverter::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Gets total spent amount of money grouped by month for specified driver id
     *
     * @param driverId driverId
     * @return total spent amount of money grouped by month for specified driver id
     */
    public List<TotalMonthFuelRequestSummaryDTO> getTotalByDriverIdGroupByMonth(Long driverId) {
        LOGGER.info("Request to total spent by month report for driver [{}].", driverId);
        List<TotalMonthFuelRequestSummary> summaries = fuelConsumptionRequestRepository.getTotalByDriverIdGroupByMonth(driverId);
        return summaries.stream()
                .map(ProjectionConverter::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Gets list fuel consumption records for specified month.
     *
     * @param month specified month
     * @return list fuel consumption records for specified month. Structure {@link MonthlyFuelRequestSummaryDTO}
     */
    public List<MonthlyFuelRequestSummaryDTO> getFuelConsumptionRequestByMonth(Integer month) {
        LOGGER.info("Request to specified month [{}] general report.", month);
        List<MonthlyFuelRequestSummary> summaries = fuelConsumptionRequestRepository.getFuelConsumptionRequestByMonth(month);
        return summaries.stream()
                .map(ProjectionConverter::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Gets list fuel consumption records for specified month for specified driver
     *
     * @param month    specified month
     * @param driverId specified driverId
     * @return list fuel consumption records for specified month. Structure {@link MonthlyFuelRequestSummaryDTO}
     */
    public List<MonthlyFuelRequestSummaryDTO> getFuelConsumptionRequestByMonthAndDriverId(Integer month, Long driverId) {
        LOGGER.info("Request to specified month [{}] general report for driver [{}].", month, driverId);
        List<MonthlyFuelRequestSummary> summaries = fuelConsumptionRequestRepository.getFuelConsumptionRequestByMonthAndDriverId(month, driverId);
        return summaries.stream()
                .map(ProjectionConverter::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Gets statistic for each month, list fuel consumption records grouped by fuel type
     *
     * @return statistic for each month, list fuel consumption records grouped by fuel type. Structure {@link MonthlyFuelTypeSummaryDTO}
     */
    public List<MonthlyFuelTypeSummaryDTO> getMonthStatisticByFuelType() {
        LOGGER.info("Request to fuel type report.");
        List<MonthlyFuelTypeSummary> summaries = fuelConsumptionRequestRepository.getMonthStatisticByFuelType();
        return summaries.stream()
                .map(ProjectionConverter::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Gets statistic for each month, list fuel consumption records grouped by fuel type for specified driver
     *
     * @param driverId specified driverId
     * @return statistic for each month, list fuel consumption records grouped by fuel type. Structure {@link MonthlyFuelTypeSummaryDTO}
     */
    public List<MonthlyFuelTypeSummaryDTO> getMonthStatisticByFuelTypeAndByDriverId(Long driverId) {
        LOGGER.info("Request to fuel type report for driver [{}].", driverId);
        List<MonthlyFuelTypeSummary> summaries = fuelConsumptionRequestRepository.getMonthStatisticByFuelTypeAndByDriverId(driverId);
        return summaries.stream()
                .map(ProjectionConverter::toDTO)
                .collect(Collectors.toList());
    }
}
