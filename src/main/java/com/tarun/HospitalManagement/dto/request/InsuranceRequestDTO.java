package com.tarun.HospitalManagement.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for creating or updating an insurance policy")
public class InsuranceRequestDTO {

    @NotBlank(message = "Policy number is required")
    @Size(max = 50, message = "Policy number must not exceed 50 characters")
    @Schema(description = "Unique policy number", example = "POL-2025-001")
    private String policyNumber;

    @NotBlank(message = "Provider name is required")
    @Size(max = 300, message = "Provider name must not exceed 300 characters")
    @Schema(description = "Insurance provider name", example = "Star Health Insurance")
    private String provider;

    @NotNull(message = "Valid until date is required")
    @Future(message = "Valid until date must be in the future")
    @Schema(description = "Policy expiration date", example = "2026-12-31")
    private LocalDate validUntil;
}
