package ee.beleychev.fcm.domain.projection;

/**
 * @author beleychev
 */
public interface TotalMonthFuelRequestSummary {
    String getMonth();
    Double getTotal();
    Long getDriverId();
}
