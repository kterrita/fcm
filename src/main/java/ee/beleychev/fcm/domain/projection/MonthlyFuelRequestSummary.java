package ee.beleychev.fcm.domain.projection;

import java.time.Instant;

/**
 * @author beleychev
 */
public interface MonthlyFuelRequestSummary {
    String getFuelType();

    Double getVolume();

    Instant getDate();

    Double getPrice();

    Double getTotal();

    Long getDriverId();
}
