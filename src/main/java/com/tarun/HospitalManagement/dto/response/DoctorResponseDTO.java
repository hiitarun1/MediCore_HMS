package com.tarun.HospitalManagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response payload for doctor data")
public class DoctorResponseDTO {

    @Schema(description = "Doctor ID", example = "1")
    private long id;

    @Schema(description = "Full name", example = "Dr. Anand Kumar")
    private String name;

    @Schema(description = "Area of specialization", example = "Cardiology")
    private String specialization;

    @Schema(description = "Email address", example = "anand.kumar@hospital.com")
    private String email;

    @Schema(description = "Department IDs this doctor belongs to")
    private Set<Long> departmentIds;

    @Schema(description = "Appointment summaries for this doctor")
    private List<AppointmentSummaryDTO> appointments;
}
