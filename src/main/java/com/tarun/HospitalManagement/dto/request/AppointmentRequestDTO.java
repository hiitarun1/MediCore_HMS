package com.tarun.HospitalManagement.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for creating or updating an appointment")
public class AppointmentRequestDTO {

    @NotNull(message = "Appointment time is required")
    @Schema(description = "Date and time of the appointment", example = "2025-08-15T10:30:00")
    private LocalDateTime appointmentTime;

    @Size(max = 500, message = "Reason must not exceed 500 characters")
    @Schema(description = "Reason for the appointment", example = "Regular checkup")
    private String reason;
}
