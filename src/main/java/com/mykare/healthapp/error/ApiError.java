package com.mykare.healthapp.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.Map;
/**
 *
 * this is for common error response
 * @author vishnu.kp
 * @version 1.0
 * @since 18 Jun,2022
 */
@Data
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiError
{
    private long timeStamp=new Date().getTime();

    private int status;

    private String message;

    private String url;

    private Map<String,String>errorMap;

    public ApiError(int status, String message, String url)
    {
        super();
        this.status = status;
        this.message = message;
        this.url = url;
    }
}
