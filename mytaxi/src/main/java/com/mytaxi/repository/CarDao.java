package com.mytaxi.repository;

import com.mytaxi.domain.Car;
import org.springframework.data.repository.CrudRepository;

/**
 * Car Data access object which implements default methods of CRUD Repo
 * 
 * @author jeraldfdo
 */
public interface CarDao extends CrudRepository<Car, Long> {
}
