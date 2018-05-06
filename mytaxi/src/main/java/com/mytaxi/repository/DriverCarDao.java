package com.mytaxi.repository;

import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.domain.DriverCar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * DriverCar(Car-Driver relationship) Data access object which implements default methods of CRUD Repo
 * 
 * @author jeraldfdo
 */
public interface DriverCarDao extends CrudRepository<DriverCar, Long> {

    DriverCar findByDriverIdAndCarId(final Long driverId, final Long carId);

    @Query("SELECT D, C FROM Car C, Driver D, DriverCar B "
            + "WHERE B.carId = C.id AND D.id = B.driverId "
            + "AND (C.seatCount = :#{#carDto.seatCount} OR C.convertible = :#{#carDto.convertible} "
            + "OR C.engineType = :#{#carDto.engineType})"
    )
    List<Object[]> findDriverByCarAttributes(@Param("carDto") final CarDto carDto);

}
