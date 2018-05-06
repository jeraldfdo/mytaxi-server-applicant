package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object class for Car to deal with transactions related to Car object
 * 
 * @author jeraldfdo
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto
{

    private Long id;

    private Float rating;

    private String engineType;

    private Integer seatCount;

    private Boolean convertible;

    private String licensePlate;

    private String manufacturer;

}
