package com.mykare.healthapp.repositary;

import com.mykare.healthapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>This class is repository class of User extended from JPA repository</p>
 * @author vishnu.kp
 * @version 1.0
 * @since 15 June,2022
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
    /**
     * <p>The abstract method for finding the use on basis of email id</p>
     * @author vishnu.kp
     * @since 15 Jun,2022
     * @param email the email id of user
     * @return a valid user if it exists in database
     */
    User findByEmail(String email);
}
