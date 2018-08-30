package ee.beleychev.fcm.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * @author beleychev
 */
@Entity
@Table(name = "fuel_consumption_request")
public class FuelConsumptionRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String fuelType;

    @NotNull
    @Column(nullable = false)
    private Float price;

    @NotNull
    @Column(nullable = false)
    private Float volume;

    @NotNull
    @Column(nullable = false)
    private Instant date;

    @NotNull
    @Column(nullable = false)
    private Long driverId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Long getDriverId() {
        return driverId;
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
        FuelConsumptionRequest that = (FuelConsumptionRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "FuelConsumptionRequest{" +
                "fuelType='" + fuelType + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                ", date=" + date +
                ", driverId=" + driverId +
                '}';
    }
}
