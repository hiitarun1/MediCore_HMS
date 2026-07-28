package com.tarun.HospitalManagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Lightweight appointment summary")
public class AppointmentSummaryDTO {

    @Schema(description = "Appointment ID", example = "1")
    private Long id;

    @Schema(description = "Appointment time", example = "2025-08-15T10:30:00")
    private LocalDateTime appointmentTime;

    @Schema(description = "Reason for the appointment", example = "Regular checkup")
    private String reason;
}
