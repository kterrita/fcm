package ee.beleychev.fcm.domain.projection;

import java.util.Objects;

/**
 * @author beleychev
 */
public class TotalMonthFuelRequestSummaryDTO implements TotalMonthFuelRequestSummary {
    private String month;
    private Double total;
    private Long driverId;

    public TotalMonthFuelRequestSummaryDTO(String month, Double total, Long driverId) {
        this.month = month;
        this.total = total;
        this.driverId = driverId;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    @Override
    public String getMonth() {
        return month;
    }

    @Override
    public Double getTotal() {
        return total;
    }

    @Override
    public Long getDriverId() {
        return driverId;
    }

    @Override
    public String toString() {
        return "TotalMonthFuelRequestSummaryDTO{" +
                "month='" + month + '\'' +
                ", total=" + total +
                ", driverId=" + driverId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TotalMonthFuelRequestSummaryDTO that = (TotalMonthFuelRequestSummaryDTO) o;
        return Objects.equals(month, that.month) &&
                Objects.equals(total, that.total) &&
                Objects.equals(driverId, that.driverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, total, driverId);
    }
}
