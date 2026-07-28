package com.tarun.HospitalManagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response payload for department data")
public class DepartmentResponseDTO {

    @Schema(description = "Department ID", example = "1")
    private long id;

    @Schema(description = "Department name", example = "Cardiology")
    private String name;

    @Schema(description = "Head doctor summary")
    private DoctorSummaryDTO headDoctor;

    @Schema(description = "Doctor summaries in this department")
    private Set<DoctorSummaryDTO> doctors;
}
