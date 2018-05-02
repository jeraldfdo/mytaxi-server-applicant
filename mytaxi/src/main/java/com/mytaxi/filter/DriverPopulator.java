package com.mytaxi.filter;

import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.datatransferobject.DriverDto;
import com.mytaxi.domain.Car;
import com.mytaxi.domain.Driver;
import com.mytaxi.domain.Coordinate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DriverPopulator
{
    public static Driver makeDriverDO(DriverDto driverDto)
    {
        return new Driver(driverDto.getUsername(), driverDto.getPassword());
    }


    public static DriverDto makeDriverDTO(Object[] object)
    {
        Driver driver = (Driver) object[0];
        Car car =  (Car) object[1] ;
        CarDto carDto = CarDto.builder()
            .id(car.getId())
            .rating(car.getRating())
            .seatCount(car.getSeatCount())
            .engineType(car.getEngineType())
            .convertible(car.getConvertible())
            .licensePlate(car.getLicensePlate())
            .manufacturer(car.getManufacturer().getName())
            .build();
        DriverDto driverDto = makeDriverDTO(driver);
        driverDto.setCarDto(carDto);
        return driverDto;
    }


    public static DriverDto makeDriverDTO(Driver driver)
    {
        DriverDto.DriverDTOBuilder driverDTOBuilder = DriverDto.newBuilder()
            .setId(driver.getId())
            .setPassword(driver.getPassword())
            .setUsername(driver.getUsername());

        Coordinate coordinate = driver.getCoordinate();
        if (coordinate != null)
        {
            driverDTOBuilder.setCoordinate(coordinate);
        }

        return driverDTOBuilder.createDriverDTO();
    }


    public static List<DriverDto> makeDriverDTOList(Collection<Driver> drivers)
    {
        return drivers.stream()
            .map(DriverPopulator::makeDriverDTO)
            .collect(Collectors.toList());
    }
}
