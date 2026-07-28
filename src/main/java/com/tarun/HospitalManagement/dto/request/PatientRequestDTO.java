package com.tarun.HospitalManagement.dto.request;

import com.tarun.HospitalManagement.entity.type.BloodGroupType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for creating or updating a patient")
public class PatientRequestDTO {

    @NotBlank(message = "Patient name is required")
    @Size(max = 40, message = "Name must not exceed 40 characters")
    @Schema(description = "Full name of the patient", example = "Rahul Sharma")
    private String name;

    @Past(message = "Birth date must be in the past")
    @Schema(description = "Date of birth", example = "2001-05-14")
    private LocalDate birthDate;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Schema(description = "Email address", example = "rahul@example.com")
    private String email;

    @Schema(description = "Gender of the patient", example = "Male")
    private String gender;

    @Schema(description = "Blood group type", example = "B_Pos")
    private BloodGroupType bloodGroup;

    @Schema(description = "Insurance ID to associate with the patient")
    private Long insuranceId;
}
