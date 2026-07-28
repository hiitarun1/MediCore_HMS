package com.tarun.HospitalManagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response payload for insurance data")
public class InsuranceResponseDTO {

    @Schema(description = "Insurance ID", example = "1")
    private long id;

    @Schema(description = "Policy number", example = "POL-2025-001")
    private String policyNumber;

    @Schema(description = "Insurance provider", example = "Star Health Insurance")
    private String provider;

    @Schema(description = "Policy expiration date", example = "2026-12-31")
    private LocalDate validUntil;

    @Schema(description = "Policy creation date")
    private LocalDate createdAt;

    @Schema(description = "Associated patient ID")
    private Long patientId;

    @Schema(description = "Associated patient name")
    private String patientName;
}
