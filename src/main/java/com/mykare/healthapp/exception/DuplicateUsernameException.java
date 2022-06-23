package com.mykare.healthapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>this is for duplicate custom exception class</p>
 * @author vishnu.kp
 * @version 1.0
 * @since 18 Jun,2022
 */
@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class DuplicateUsernameException extends RuntimeException {

}
