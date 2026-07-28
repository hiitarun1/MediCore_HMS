package com.tarun.HospitalManagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Lightweight doctor summary")
public class DoctorSummaryDTO {

    @Schema(description = "Doctor ID", example = "1")
    private long id;

    @Schema(description = "Doctor name", example = "Dr. Anand Kumar")
    private String name;

    @Schema(description = "Specialization", example = "Cardiology")
    private String specialization;

    @Schema(description = "Email address", example = "anand.kumar@hospital.com")
    private String email;
}
