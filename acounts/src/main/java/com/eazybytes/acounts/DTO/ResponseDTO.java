package com.eazybytes.acounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "ResponseDTO", description = "Data transfer object for responses")
public class ResponseDTO {
    @Schema(description = "HTTP status code of the response", example = "200")
    private String statusCode;

    @Schema(description = "Message detailing the status of the response", example = "Success")
    private String statusMsg;
}
