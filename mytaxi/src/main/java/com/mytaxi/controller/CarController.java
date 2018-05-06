package com.mytaxi.controller;

import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.facade.CarFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Car related requests will be handled by this Controller
 * 
 * @author jeraldfdo
 */
@RestController
@RequestMapping("swag/cars")
public class CarController
{

    @Autowired
    private CarFacade carFacade;


    @RequestMapping(value = "/{carId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CarDto> getCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        return new ResponseEntity<>(carFacade.findCarById(carId), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CarDto>> getAllCars()
    {
        return new ResponseEntity<>(carFacade.findAllCars(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CarDto> createCar(@Valid @RequestBody CarDto carDto) throws EntityNotFoundException
    {
        return new ResponseEntity<>(carFacade.create(carDto), HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateCar(@Valid @RequestBody CarDto carDto) throws EntityNotFoundException
    {
        carFacade.update(carDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/{carId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        carFacade.delete(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
