package com.enes.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.enes.constant.ErrorMessages.*;

@Getter
@AllArgsConstructor
public enum ErrorType {

    /**
     * @ErrorCode   : 9999<br>
     * @Value       : "An unexpected error occurred."<br>
     * @HttpStatus  : 500 Internal Server Error
     */
    UNEXPECTED_ERROR(9999, UNEXPECTED_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * @ErrorCode   : 1001<br>
     * @Value       : " is not found."<br>
     * @HttpStatus  : 404 Not Found
     */
    NOT_FOUND(1001, NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND),
    /**
     * @ErrorCode   : 1002<br>
     * @Value       : " is not valid."<br>
     * @HttpStatus  : 400 Bad Request
     */
    NOT_VALID(1002, NOT_VALID_MESSAGE, HttpStatus.BAD_REQUEST),
    /**
     * @ErrorCode   : 1003<br>
     * @Value       : " do not match."<br>
     * @HttpStatus  : 400 Bad Request
     */
    DO_NOT_MATCH(1003, DO_NOT_MATCH_MESSAGE, HttpStatus.BAD_REQUEST),
    /**
     * @ErrorCode   : 1004<br>
     * @Value       : "Registration failed."<br>
     * @HttpStatus  : 409 Conflict
     */
    REGISTRATION_FAILED(1004, REGISTRATION_FAILED_MESSAGE, HttpStatus.CONFLICT),
    /**
     * @ErrorCode   : 1005<br>
     * @Value       : "Deletion failed."<br>
     * @HttpStatus  : 409 Conflict
     */
    DELETION_FAILED(1005, DELETION_FAILED_MESSAGE, HttpStatus.CONFLICT),
    /**
     * @ErrorCode   : 1006<br>
     * @Value       : " is null."<br>
     * @HttpStatus  : 500 Internal Server Error
     */
    NULL_POINTER(1006, NULL_POINTER_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * @ErrorCode   : 2001<br>
     * @Value       : "User not found or password incorrect."<br>
     * @HttpStatus  : 401 Unauthorized
     */
    USER_NOT_FOUND_OR_PASSWORD_INCORRECT(2001, USER_NOT_FOUND_OR_PASSWORD_INCORRECT_MESSAGE, HttpStatus.UNAUTHORIZED);

    private final Integer code;

    private final String message;

    private final HttpStatus httpStatus;
}
