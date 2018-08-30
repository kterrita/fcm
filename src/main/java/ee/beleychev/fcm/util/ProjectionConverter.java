package ee.beleychev.fcm.util;

import ee.beleychev.fcm.domain.projection.*;

/**
 * Proxy projection to dto converter
 *
 * @author beleychev
 */
public class ProjectionConverter {
    public static TotalMonthFuelRequestSummaryDTO toDTO(TotalMonthFuelRequestSummary summary) {
        return new TotalMonthFuelRequestSummaryDTO(summary.getMonth(), summary.getTotal(), summary.getDriverId());
    }

    public static MonthlyFuelRequestSummaryDTO toDTO(MonthlyFuelRequestSummary summary) {
        return new MonthlyFuelRequestSummaryDTO(summary.getFuelType(), summary.getVolume(), summary.getDate(), summary.getPrice(), summary.getTotal(), summary.getDriverId());
    }

    public static MonthlyFuelTypeSummaryDTO toDTO(MonthlyFuelTypeSummary summary) {
        return new MonthlyFuelTypeSummaryDTO(summary.getMonth(), summary.getFuelType(), summary.getVolume(), summary.getAveragePrice(), summary.getTotal(), summary.getDriverId());
    }

    private ProjectionConverter() {
        //prevent of creation
    }
}
