package com.mykare.healthapp.service;

import com.mykare.healthapp.model.User;

import java.util.List;

/**
 * <p>The class contain the abstract method of user service User related CRUD</p>
 * @author vishnu.kp
 * @version 1.0
 * @since 15 Jun,2022
 *
 */
public interface UserService {
    /**
     * <p>for registering a user</p>
     * @author vishnu.kp
     * @since 15 Jun,2022
     * @param user the user
     * @return return user if created
     */
    User createUser(User user);

    /**
     * <p>List out the all registered user</p>
     * @author vishnu.kp
     * @since 15 Jun,2022
     * @return list of all registered user
     */
    List<User> getAllRegisteredUser();

    /**
     * <p>for removing the user from database on basis of email</p>
     * @author vishnu.kp
     * @since 15 Jun,2022
     * @param email
     * @return true if the user removed on basis of email or return false
     */
    Boolean removeUser(String email);

}
