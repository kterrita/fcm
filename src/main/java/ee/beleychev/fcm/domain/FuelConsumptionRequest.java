/*
FuelConsumptionRequest.java
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
    @Column(name = "fuel_type", nullable = false)
    private String fuelType;

    @NotNull
    @Column(nullable = false)
    private Float volume;

    @NotNull
    @Column(nullable = false)
    private Instant date;

    @NotNull
    @Column(nullable = false)
    private String driverId;

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

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FuelConsumptionRequest that = (FuelConsumptionRequest) o;
        return id == that.id &&
                Float.compare(that.volume, volume) == 0 &&
                Objects.equals(fuelType, that.fuelType) &&
                Objects.equals(date, that.date) &&
                Objects.equals(driverId, that.driverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "FuelConsumptionRequest{" +
                "id=" + id +
                '}';
    }
}
