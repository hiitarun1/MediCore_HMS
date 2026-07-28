package com.tarun.HospitalManagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Response payload for appointment data")
public class AppointmentResponseDTO {

    @Schema(description = "Appointment ID", example = "1")
    private Long id;

    @Schema(description = "Date and time of the appointment", example = "2025-08-15T10:30:00")
    private LocalDateTime appointmentTime;

    @Schema(description = "Reason for the appointment", example = "Regular checkup")
    private String reason;

    @Schema(description = "Patient ID", example = "1")
    private Long patientId;

    @Schema(description = "Patient name", example = "Rahul Sharma")
    private String patientName;

    @Schema(description = "Doctor ID", example = "1")
    private Long doctorId;

    @Schema(description = "Doctor name", example = "Dr. Anand Kumar")
    private String doctorName;
}
