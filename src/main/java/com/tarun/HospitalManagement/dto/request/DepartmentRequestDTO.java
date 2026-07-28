package com.tarun.HospitalManagement.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for creating or updating a department")
public class DepartmentRequestDTO {

    @NotBlank(message = "Department name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    @Schema(description = "Name of the department", example = "Cardiology")
    private String name;

    @Schema(description = "ID of the head doctor for this department")
    private Long headDoctorId;
}
