package com.mykare.healthapp.controller;

import com.mykare.healthapp.error.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * The controller clas used for error handling
 * @author vishnu.kp
 * @version 1.0
 * @since 18 June ,2022s
 */
@RestController
public class ErrorHandler implements ErrorController
{
    private final static String ERROR_URL="/error";
    private final static String STATUS="status";
    private final static String MESSAGE="message";
    private final static String PATH="path";

    @Autowired
    private ErrorAttributes errorAttributes;
    /**
     * @author vishnu.kp
     * @param webRequest the web request
     * @return an Api error with status,message and url
     */
    @RequestMapping("/error")
    ApiError handleError(WebRequest webRequest)
    {
        final Map<String, Object> attributeMap =
                errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE));
        final int status = (Integer) attributeMap.get(STATUS);
        final String message = (String) attributeMap.get(MESSAGE);
        final String Url = (String) attributeMap.get(PATH);
        return new ApiError(status, message, Url);
    }
    public String getErrorPath()
    {
        return ERROR_URL;
    }
}
