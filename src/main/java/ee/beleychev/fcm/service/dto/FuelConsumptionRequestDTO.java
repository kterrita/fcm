package ee.beleychev.fcm.service.dto;

import ee.beleychev.fcm.domain.FuelConsumptionRequest;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;

/**
 * @author beleychev
 */
public class FuelConsumptionRequestDTO implements Serializable {
    private static final long serialVersionUID = 10L;

    private Long id;

    @NotBlank
    private String fuelType;

    @NotBlank
    private Float price;

    @NotBlank
    private Float volume;

    @NotBlank
    private Instant date;

    @NotBlank
    private Long driverId;

    public FuelConsumptionRequestDTO() {
        // Empty constructor needed for Jackson.
    }

    public FuelConsumptionRequestDTO(FuelConsumptionRequest fuelConsumptionRequest) {
        this.id = fuelConsumptionRequest.getId();
        this.fuelType = fuelConsumptionRequest.getFuelType();
        this.price = fuelConsumptionRequest.getPrice();
        this.volume = fuelConsumptionRequest.getVolume();
        this.date = fuelConsumptionRequest.getDate();
        this.driverId = fuelConsumptionRequest.getDriverId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
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
    public String toString() {
        return "FuelConsumptionRequestDTO{" +
                "fuelType='" + fuelType + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                ", date=" + date +
                ", driverId=" + driverId +
                '}';
    }
}
