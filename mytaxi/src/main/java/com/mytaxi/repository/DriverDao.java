package com.mytaxi.repository;

import com.mytaxi.domain.Driver;
import com.mytaxi.domain.OnlineStatus;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for driver table.
 *
 * @author jeraldfdo
 */
public interface DriverDao extends CrudRepository<Driver, Long> {

    Driver findByUsername(final String username);

    List<Driver> findByOnlineStatus(OnlineStatus onlineStatus);
}
