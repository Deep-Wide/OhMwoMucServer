package net.ohmwomuc.core.exception;


import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleInvaildInputException(Exception e,
                                                                     HandlerMethod handlerMethod,
                                                                     HttpServletRequest request) {

        e.printStackTrace();

        if (e instanceof CustomException) {
            CustomException customException = (CustomException) e;
            CustomExceptionCode customExceptionCode = customException.getCustomExceptionCode();
            HttpStatus httpStatus = customExceptionCode.getHttpStatus();

            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorCode(customExceptionCode.getCode())
                    .errorClassName(e.getClass().getName())
                    .errorMessage(e.getMessage())
                    .httpStatusCode(httpStatus.value())
                    .build(), httpStatus);

        }  else {
            HttpStatus httpStatus = e instanceof HttpRequestMethodNotSupportedException ? METHOD_NOT_ALLOWED : INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorCode("UNCAUGHT_ERROR")
                    .errorClassName(e.getClass().getName())
                    .errorMessage(e.getMessage())
                    .httpStatusCode(httpStatus.value())
                    .build(), httpStatus);
        }
    }

    public static void writeSecurityExceptionResponse(HttpServletResponse response, CustomExceptionCode code) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(code.getCode())
                .errorClassName(CustomException.class.getName())
                .errorMessage(code.getMessage())
                .httpStatusCode(code.getHttpStatus().value())
                .build();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(code.getHttpStatus().value());
        response.getWriter().write(new Gson().toJson(errorResponse));
    }
}
