package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domain.Coordinate;
import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object class for Driver to deal with transactions related to Driver object
 * 
 * @author jeraldfdo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDto {

    @JsonIgnore
    private Long id;

    public CarDto getCarDto() {
        return carDto;
    }

    public void setCarDto(CarDto carDto) {
        this.carDto = carDto;
    }

    @JsonProperty("car")
    private CarDto carDto;

    @NotNull(message = "Username can not be null!")
    private String username;

    @NotNull(message = "Password can not be null!")
    private String password;

    private Coordinate coordinate;

    private DriverDto() {
    }

    private DriverDto(Long id, String username, String password, Coordinate coordinate, CarDto carDto) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.coordinate = coordinate;
        this.carDto = carDto;
    }

    public static DriverDTOBuilder newBuilder() {
        return new DriverDTOBuilder();
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public static class DriverDTOBuilder {

        private Long id;
        private String username;
        private String password;
        private Coordinate coordinate;
        private CarDto carDto;

        public DriverDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public DriverDTOBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public DriverDTOBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public DriverDTOBuilder setCoordinate(Coordinate coordinate) {
            this.coordinate = coordinate;
            return this;
        }

        public DriverDTOBuilder setCarDto(CarDto carDto) {
            this.carDto = carDto;
            return this;
        }

        public DriverDto createDriverDTO() {
            return new DriverDto(id, username, password, coordinate, carDto);
        }

    }
}
