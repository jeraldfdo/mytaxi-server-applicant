package com.mytaxi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author jeraldfdo
 */

@Data
@Entity
@Table(name = "driver_car")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DriverCar
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_id", unique = true)
    private Long driverId;


    @Column(name = "car_id", unique = true)
    private Long carId;


}
