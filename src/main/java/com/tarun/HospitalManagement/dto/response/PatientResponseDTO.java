package com.tarun.HospitalManagement.dto.response;

import com.tarun.HospitalManagement.entity.type.BloodGroupType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response payload for patient data")
public class PatientResponseDTO {

    @Schema(description = "Patient ID", example = "1")
    private long id;

    @Schema(description = "Full name of the patient", example = "Rahul Sharma")
    private String name;

    @Schema(description = "Date of birth", example = "2001-05-14")
    private LocalDate birthDate;

    @Schema(description = "Email address", example = "rahul@example.com")
    private String email;

    @Schema(description = "Gender", example = "Male")
    private String gender;

    @Schema(description = "Blood group type", example = "B_Pos")
    private BloodGroupType bloodGroup;

    @Schema(description = "Record creation timestamp")
    private LocalDateTime createdAt;

    @Schema(description = "Associated insurance summary")
    private InsuranceSummaryDTO insurance;

    @Schema(description = "List of appointment summaries")
    private List<AppointmentSummaryDTO> appointments;
}
