package ee.beleychev.fcm.domain.projection;

/**
 * @author beleychev
 */
public interface MonthlyFuelTypeSummary {
    String getMonth();
    String getFuelType();
    Double getVolume();
    Double getAveragePrice();
    Double getTotal();
    Long getDriverId();
}
