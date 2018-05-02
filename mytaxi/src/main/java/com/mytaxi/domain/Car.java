package com.mytaxi.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * @author jeraldfdo
 */
@Data
@Entity
@Table(name = "car")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Car
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "date_created")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column
    private Float rating;

    @Column(name = "engine_type")
    private String engineType;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "seat_count")
    private Integer seatCount;

    @Column
    private Boolean convertible;

    @Column(nullable = false)
    private Boolean deleted = false;

    @OneToOne
    @JoinColumn(name="manufacturer")
    private Manufacturer manufacturer;


}
