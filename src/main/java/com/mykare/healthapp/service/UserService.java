package com.mykare.healthapp.service;

import com.mykare.healthapp.model.User;

import java.util.List;

/**
 * <p>The class contain the abstract method of user service</p>
 * @author vishnu.kp
 * @version 1.0
 * @since 15 Jun,2022
 *
 */
public interface UserService {

    User createUser(User user);

    List<User> getAllRegisteredUser();

    Boolean removeUser(String email);

}
