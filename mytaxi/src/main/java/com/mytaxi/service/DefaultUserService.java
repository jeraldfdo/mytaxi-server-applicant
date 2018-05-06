package com.mytaxi.service;

import com.mytaxi.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mytaxi.repository.DriverDao;


/**
 * Implementation of UserDetailsService Interface
 *  
 * @author jeraldfdo
 */
@Service("userDetailsService")
public class DefaultUserService implements UserDetailsService {

    @Autowired
    private DriverDao driverDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return driverDao.findByUsername(username);
    }
}
