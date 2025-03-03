package net.ohmwomuc.core.exception;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String errorCode;

    private String errorMessage;

    private int httpStatusCode;

    private String errorClassName;
}

