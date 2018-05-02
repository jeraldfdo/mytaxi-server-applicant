package com.mytaxi.controller;

import com.mytaxi.datatransferobject.CarDto;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.facade.DriverFacade;
import com.mytaxi.datatransferobject.DriverDto;
import com.mytaxi.domain.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.collections.MapUtils.*;

/**
 * All operations with a driver will be routed by this controller.
 *
 * @author jeraldfdo
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController {

    @Autowired
    private DriverFacade driverFacade;

    @PostMapping("/select")
    public DriverDto selectCarByDriver(@RequestParam long driverId, @RequestParam long carId) throws EntityNotFoundException,
            CarAlreadyInUseException {

        return driverFacade.selectCarByDriver(driverId, carId);
    }

    @DeleteMapping("/deselect")
    public void deSelectCarByDriver(@RequestParam long driverId, @RequestParam long carId) throws EntityNotFoundException,
            CarAlreadyInUseException {
        driverFacade.deSelectCarByDriver(driverId, carId);
    }

    @GetMapping("/{driverId}")
    public DriverDto getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException {
        return driverFacade.findByDriverId(driverId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDto createDriver(@Valid @RequestBody DriverDto driverDto) throws ConstraintsViolationException {
        return driverFacade.create(driverDto);
    }

    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException {
        driverFacade.delete(driverId);
    }

    @PutMapping("/{driverId}")
    public void updateLocation(
            @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
            throws EntityNotFoundException {
        driverFacade.updateLocation(driverId, longitude, latitude);
    }

    @GetMapping
    public List<DriverDto> findDrivers(@RequestParam OnlineStatus onlineStatus)
            throws ConstraintsViolationException {
        return driverFacade.findByOnlineStatus(onlineStatus);
    }

    @GetMapping("/car")
    public List<DriverDto> findDriverByCarAttributes(@RequestParam Map<String, String> params) {
        return driverFacade.findDriverByCarAttributes(getCarDtoRequest(params));
    }

    private CarDto getCarDtoRequest(Map<String, String> params) {
        return CarDto.builder()
                .seatCount(getInteger(params, "seatCount"))
                .engineType(getString(params, "engineType"))
                .convertible(getBoolean(params, "convertible"))
                .build();
    }
}
