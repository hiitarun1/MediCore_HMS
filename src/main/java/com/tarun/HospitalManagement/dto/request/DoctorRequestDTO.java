package com.tarun.HospitalManagement.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for creating or updating a doctor")
public class DoctorRequestDTO {

    @NotBlank(message = "Doctor name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    @Schema(description = "Full name of the doctor", example = "Dr. Anand Kumar")
    private String name;

    @Size(max = 100, message = "Specialization must not exceed 100 characters")
    @Schema(description = "Area of specialization", example = "Cardiology")
    private String specialization;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Schema(description = "Email address of the doctor", example = "anand.kumar@hospital.com")
    private String email;
}
