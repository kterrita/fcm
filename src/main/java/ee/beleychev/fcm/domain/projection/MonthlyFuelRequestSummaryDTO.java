package ee.beleychev.fcm.domain.projection;

import java.time.Instant;
import java.util.Objects;

/**
 * @author beleychev
 */
public class MonthlyFuelRequestSummaryDTO implements MonthlyFuelRequestSummary {
    private String fuelType;
    private Double volume;
    private Instant date;
    private Double price;
    private Double total;
    private Long driverId;

    public MonthlyFuelRequestSummaryDTO(String fuelType, Double volume, Instant date, Double price, Double total, Long driverId) {
        this.fuelType = fuelType;
        this.volume = volume;
        this.date = date;
        this.price = price;
        this.total = total;
        this.driverId = driverId;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setDriverId(Long driverId) {
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
    public Instant getDate() {
        return date;
    }

    @Override
    public Double getPrice() {
        return price;
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MonthlyFuelRequestSummaryDTO that = (MonthlyFuelRequestSummaryDTO) o;
        return Objects.equals(fuelType, that.fuelType) &&
                Objects.equals(volume, that.volume) &&
                Objects.equals(date, that.date) &&
                Objects.equals(price, that.price) &&
                Objects.equals(total, that.total) &&
                Objects.equals(driverId, that.driverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelType, volume, date, price, total, driverId);
    }

    @Override
    public String toString() {
        return "MonthlyFuelRequestSummaryDTO{" +
                "fuelType='" + fuelType + '\'' +
                ", volume=" + volume +
                ", date=" + date +
                ", price=" + price +
                ", total=" + total +
                ", driverId=" + driverId +
                '}';
    }
}
