package com.mykare.healthapp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mykare.healthapp.error.ApiError;
import com.mykare.healthapp.model.User;
import com.mykare.healthapp.service.UserService;
import com.mykare.healthapp.shared.CurrentUser;
import com.mykare.healthapp.shared.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// http://localhost:9876/api/test

/**
 * @author vishnu.kp
 * @version 1.0
 * @since 06 Jun,2022
 */
@RestController
@RequestMapping("/api/1.0")
public class UserController
{
    @Autowired
    private UserService userService;

    @JsonView(Views.Base.class)
    @PostMapping("/login")
    User handleLogin(@CurrentUser User loggedUser)
    {
        return loggedUser;
    }

    /**
     * @author Vishnu.kp
     * @param user the User model holding the name ,email,gender,password
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<User> userRegistration(@Valid @RequestBody  User user)
    {
        final var createUser=userService.createUser(user);
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("user","/api/1.0/register"+Integer.toString(createUser.getUserId()));
        return new ResponseEntity<>(createUser,httpHeaders, HttpStatus.CREATED);
    }
        @GetMapping("/users")
        public ResponseEntity<List<User>> getAllRegisteredUser()
        {
            final List<User>registeredUser=userService.getAllRegisteredUser();
            return new ResponseEntity<>(registeredUser,HttpStatus.OK);
        }
        @DeleteMapping("/remove/{email}")
        public ResponseEntity<Boolean>removeUser(@PathVariable("email") String email)
        {
            return new ResponseEntity<>(userService.removeUser(email),HttpStatus.NO_CONTENT);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        ApiError handleExceptionValidation(MethodArgumentNotValidException exeption , HttpServletRequest request)
        {
            final var apiError= new ApiError(400,"validation error",request.getServletPath());
            final var bindingResult=exeption.getBindingResult();
            final Map<String,String> validationError =new HashMap<>();
            for(final var fieldError :bindingResult.getFieldErrors())
            {
                validationError.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            apiError.setErrorMap(validationError);

             return apiError;
        }
}
