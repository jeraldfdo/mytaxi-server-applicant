package com.mytaxi.repository;

import com.mytaxi.domain.Car;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jeraldfdo
 */
public interface CarDao extends CrudRepository<Car, Long>
{
}
