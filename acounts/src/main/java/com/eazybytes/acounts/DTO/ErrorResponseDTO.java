package com.eazybytes.acounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Schema(name = "ErrorResponseDTO", description = "Data transfer object for error responses")
public class ErrorResponseDTO {
    @Schema(description = "API path where the error occurred", example = "/api/v1/accounts")
    private String apiPath;

    @Schema(description = "HTTP status code of the error", example = "404")
    private HttpStatus errorCode;

    @Schema(description = "Error message detailing the issue", example = "Account not found")
    private String errorMsg;

    @Schema(description = "Timestamp when the error occurred", example = "2023-10-01T12:34:56")
    private LocalDateTime errorTime;
}
