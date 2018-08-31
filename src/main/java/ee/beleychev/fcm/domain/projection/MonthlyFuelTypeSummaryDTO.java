package ee.beleychev.fcm.domain.projection;

import java.util.Objects;

/**
 * @author beleychev
 */
public class MonthlyFuelTypeSummaryDTO implements MonthlyFuelTypeSummary {
    private String month;
    private String fuelType;
    private Double volume;
    private Double averagePrice;
    private Double total;
    private Long driverId;

    public MonthlyFuelTypeSummaryDTO(String month, String fuelType, Double volume, Double averagePrice, Double total, Long driverId) {
        this.month = month;
        this.fuelType = fuelType;
        this.volume = volume;
        this.averagePrice = averagePrice;
        this.total = total;
        this.driverId = driverId;
    }

    @Override
    public String getFuelType() {
        return fuelType;
    }

    @Override
    public Double getVolume() {
        return volume;
    }

    @Override
    public Double getAveragePrice() {
        return averagePrice;
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
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MonthlyFuelTypeSummaryDTO that = (MonthlyFuelTypeSummaryDTO) o;
        return Objects.equals(fuelType, that.fuelType) &&
                Objects.equals(volume, that.volume) &&
                Objects.equals(averagePrice, that.averagePrice) &&
                Objects.equals(total, that.total) &&
                Objects.equals(driverId, that.driverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelType, volume, averagePrice, total, driverId);
    }

    @Override
    public String toString() {
        return "MonthlyFuelTypeSummaryDTO{" +
                "month='" + month + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", volume=" + volume +
                ", averagePrice=" + averagePrice +
                ", total=" + total +
                ", driverId=" + driverId +
                '}';
    }
}
