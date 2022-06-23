package com.mykare.healthapp.service;

import com.mykare.healthapp.exception.DuplicateUsernameException;
import com.mykare.healthapp.model.User;
import com.mykare.healthapp.repositary.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author
 * @version 1.0
 * @since 15 Jun,2022
 */
@Service
public class UserServiceImplement implements UserService
{
    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * @author vishnu.kp
     * @param user the user holding the name,email id,password,and gender
     * @return return a valid used if exists in database otherwise return empty
     */
    @Override
    @Transactional
    public User createUser(User user) {
         if(Objects.nonNull(user.getEmail()) && !this.isRegisteredUser(user.getEmail())) {
             throw new DuplicateUsernameException();
         }
           Optional<User> validUser = Optional.ofNullable(user);
           if (validUser.isPresent()) {
               User bCryptUser = encodePassword(validUser.get());
               User newUser = userRepository.saveAndFlush(bCryptUser);
               return newUser;
           }
        return new User();
    }

    /**
     * @author vishnu.kp
     * @param user the user
     * @return a user with encoded password
     */
    private User encodePassword(User user){
        final String bCryptPassword=bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(bCryptPassword);
        return user;
    }

    /**
     * @author vishnu.kp
     * @since 15 Jun,2022
     * @return list of user if present in database, otherwise empty
     */
    @Override
    public List<User> getAllRegisteredUser()
    {
        final Optional<List<User>>userRegisteredList=Optional.ofNullable(userRepository.findAll());
        if(userRegisteredList.isPresent())
           return userRegisteredList.get();
        return new ArrayList<>();

    }

    private Boolean isRegisteredUser(String email)
    {
        final User userInDb = userRepository.findByEmail(email);
        if(Objects.isNull(userInDb))
            return true;
        return false;
    }

    @Override
    public Boolean removeUser(String email)
    {
        final Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if(user.isPresent())
        {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }
}
