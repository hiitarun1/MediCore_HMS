package com.tarun.HospitalManagement.controller;

import com.tarun.HospitalManagement.dto.request.AppointmentRequestDTO;
import com.tarun.HospitalManagement.dto.response.AppointmentResponseDTO;
import com.tarun.HospitalManagement.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@Tag(name = "Appointment Controller", description = "Endpoints for managing medical appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @Operation(summary = "Schedule a new appointment")
    public ResponseEntity<Void> createNewAppointment(
            @Valid @RequestBody AppointmentRequestDTO dto,
            @RequestParam Long doctorId,
            @RequestParam Long patientId) {
        appointmentService.createNewAppointment(dto, doctorId, patientId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Get all scheduled appointments")
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an appointment by ID")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancel/delete an appointment by ID")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/reassign/{doctorId}")
    @Operation(summary = "Reassign an appointment to a different doctor")
    public ResponseEntity<AppointmentResponseDTO> reAssignAppointmentToAnotherDoctr(
            @PathVariable Long id,
            @PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.reAssignAppointmentToAnotherDoctr(id, doctorId));
    }

    @PatchMapping("/{id}/time")
    @Operation(summary = "Reschedule appointment time")
    public ResponseEntity<AppointmentResponseDTO> updateAppointmentTime(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newTime) {
        return ResponseEntity.ok(appointmentService.updateAppointmentTime(id, newTime));
    }

    @PatchMapping("/{id}/reason")
    @Operation(summary = "Update the medical reason for the appointment")
    public ResponseEntity<AppointmentResponseDTO> updateReason(
            @PathVariable Long id,
            @RequestParam String reason) {
        return ResponseEntity.ok(appointmentService.updateReason(id, reason));
    }

    @GetMapping("/doctor/{doctorId}")
    @Operation(summary = "Get all appointments scheduled for a specific doctor")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctor(doctorId));
    }

    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Get all appointments scheduled for a specific patient")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatient(patientId));
    }

    @GetMapping("/range")
    @Operation(summary = "Get all appointments scheduled between two date-times")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(appointmentService.getAppointmentsBetweenDates(start, end));
    }
}
