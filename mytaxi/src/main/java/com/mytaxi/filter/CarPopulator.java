package com.mytaxi.filter;

import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.domain.Car;
import com.mytaxi.domain.Manufacturer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jeraldfdo
 */
public class CarPopulator
{

    public static CarDto populate(Car source)
    {
        return CarDto.builder()
            .id(source.getId())
            .convertible(source.getConvertible())
            .engineType(source.getEngineType())
            .licensePlate(source.getLicensePlate())
            .manufacturer(source.getManufacturer().getName())
            .rating(source.getRating())
            .seatCount(source.getSeatCount())
            .build();
    }


    public static List<CarDto> populate(Iterable<Car> source)
    {
        List<CarDto> carDtoList = new ArrayList<>();
        source.forEach(car -> carDtoList.add(populate(car)));
        return carDtoList;
    }


    public static Car convert(CarDto source)
    {
        Car car = new Car();
        car.setConvertible(source.getConvertible());
        car.setEngineType(source.getEngineType());
        car.setLicensePlate(source.getLicensePlate());
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(source.getManufacturer());
        car.setManufacturer(manufacturer);
        car.setRating(source.getRating());
        car.setSeatCount(source.getSeatCount());
        return car;

    }

}
