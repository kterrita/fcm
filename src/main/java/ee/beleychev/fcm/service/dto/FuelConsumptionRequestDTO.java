/*
FuelConsumptionRequestDTO.java
*
Avaya Inc. - Proprietary (Restricted)
Solely for authorized persons having a need to know
pursuant to Company instructions.
*
Copyright © 2008-2018 Avaya Inc. All rights reserved.
THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Avaya Inc.
The copyright notice above does not evidence any actual
or intended publication of such source code.
*/
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
    private Float volume;

    @NotBlank
    private Instant date;

    @NotBlank
    private String driverId;

    public FuelConsumptionRequestDTO() {
        // Empty constructor needed for Jackson.
    }

    public FuelConsumptionRequestDTO(FuelConsumptionRequest fuelConsumptionRequest) {
        this.id = fuelConsumptionRequest.getId();
        this.fuelType = fuelConsumptionRequest.getFuelType();
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

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "FuelConsumptionRequestDTO{" +
                "fuelType='" + fuelType + '\'' +
                ", volume=" + volume +
                ", date=" + date +
                ", driverId='" + driverId + '\'' +
                '}';
    }
}
