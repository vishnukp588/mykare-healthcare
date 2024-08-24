package com.mykare.healthapp.service;

import com.mykare.healthapp.model.User;
import com.mykare.healthapp.repositary.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>This class for implementing the user details Service and check for user is valid</p>
 * @author vishnu.kp
 * @version 1.0
 * @since 18 Jun,2022
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @author vishnu.kp
     * @param username the user name, here email as user name
     * @return a valid user
     * @throws UsernameNotFoundException if user not found in database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("Invalid user "+username);
        }
        return user;
    }
}
