package ee.beleychev.fcm.repository;

import ee.beleychev.fcm.domain.FuelConsumptionRequest;
import ee.beleychev.fcm.domain.projection.MonthlyFuelRequestSummary;
import ee.beleychev.fcm.domain.projection.MonthlyFuelTypeSummary;
import ee.beleychev.fcm.domain.projection.TotalMonthFuelRequestSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author beleychev
 */
@RepositoryRestResource(collectionResourceRel = "fuelConsumptionRequests", path = "fuelConsumptionRequests")
public interface FuelConsumptionRequestRepository extends JpaRepository<FuelConsumptionRequest, Long> {
    @Query(nativeQuery = true, value =
            "SELECT EXTRACT(MONTH from date) AS MONTH, SUM(price * volume) AS TOTAL "
                    + "FROM FUEL_CONSUMPTION_REQUEST GROUP BY MONTH "
                    + "ORDER BY month")
    List<TotalMonthFuelRequestSummary> getTotalGroupByMonth();

    @Query(nativeQuery = true, value =
            "SELECT driver_id AS DRIVERID, EXTRACT(MONTH from date) AS MONTH, SUM(price * volume) AS TOTAL "
                    + "FROM FUEL_CONSUMPTION_REQUEST " +
                    "WHERE driver_id = :driverId GROUP BY MONTH "
                    + "ORDER BY month")
    List<TotalMonthFuelRequestSummary> getTotalByDriverIdGroupByMonth(@Param("driverId") Long driverId);

    @Query(nativeQuery = true, value =
            "SELECT fuel_type as FUELTYPE, volume as VOLUME, date as DATE, "
                    + "price as PRICE, SUM(price * volume) as TOTAL, driver_id as DRIVERID "
                    + "FROM FUEL_CONSUMPTION_REQUEST "
                    + "WHERE EXTRACT(MONTH from DATE) = :month GROUP BY VOLUME, DATE")
    List<MonthlyFuelRequestSummary> getFuelConsumptionRequestByMonth(@Param("month") Integer month);

    @Query(nativeQuery = true, value =
            "SELECT fuel_type as FUELTYPE, volume as VOLUME, date as DATE, "
                    + "price as PRICE, SUM(price * volume) as TOTAL, driver_id as DRIVERID "
                    + "FROM FUEL_CONSUMPTION_REQUEST "
                    + "WHERE EXTRACT(MONTH from DATE) = :month AND driver_id = :driverId GROUP BY VOLUME, DATE")
    List<MonthlyFuelRequestSummary> getFuelConsumptionRequestByMonthAndDriverId(@Param("month") Integer month, @Param("driverId") Long driverId);

    @Query(nativeQuery = true, value =
            "SELECT EXTRACT(MONTH from date) as MONTH, fuel_Type as FUELTYPE, SUM(volume) as VOLUME, AVG(price) as AVERAGEPRICE, SUM(price * volume) as TOTAL, driver_id as DRIVERID " +
                    "FROM FUEL_CONSUMPTION_REQUEST "
                    + "GROUP BY fuel_type, month, driver_id "
                    + "ORDER BY month")
    List<MonthlyFuelTypeSummary> getMonthStatisticByFuelType();

    @Query(nativeQuery = true, value =
            "SELECT EXTRACT(MONTH from date) as MONTH, fuel_Type as FUELTYPE, SUM(volume) as VOLUME, AVG(price) as AVERAGEPRICE, SUM(price * volume) as TOTAL, driver_id as DRIVERID " +
                    "FROM FUEL_CONSUMPTION_REQUEST "
                    + "WHERE driver_id = :driverId "
                    + "GROUP BY fuel_type, month, driver_id "
                    + "ORDER BY month")
    List<MonthlyFuelTypeSummary> getMonthStatisticByFuelTypeAndByDriverId(@Param("driverId") Long driverId);
}
