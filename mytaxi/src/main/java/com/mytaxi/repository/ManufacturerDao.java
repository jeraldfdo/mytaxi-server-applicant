package com.mytaxi.repository;

import com.mytaxi.domain.Manufacturer;
import org.springframework.data.repository.CrudRepository;

/**
 * Manufacturer Data access object which implements default methods of CRUD Repo
 * 
 * @author jeraldfdo
 */
public interface ManufacturerDao extends CrudRepository<Manufacturer, Long> {

    Manufacturer findByName(final String name);
}
