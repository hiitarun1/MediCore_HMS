package com.tarun.HospitalManagement.controller;

import com.tarun.HospitalManagement.entity.Appointment;
import com.tarun.HospitalManagement.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
            @RequestBody Appointment appointment,
            @RequestParam Long doctorId,
            @RequestParam Long patientId) {
        appointmentService.createNewAppointment(appointment, doctorId, patientId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Get all scheduled appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an appointment by ID")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
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
    public ResponseEntity<Appointment> reAssignAppointmentToAnotherDoctr(
            @PathVariable Long id,
            @PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.reAssignAppointmentToAnotherDoctr(id, doctorId));
    }

    @PatchMapping("/{id}/time")
    @Operation(summary = "Reschedule appointment time")
    public ResponseEntity<Appointment> updateAppointmentTime(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newTime) {
        return ResponseEntity.ok(appointmentService.updateAppointmentTime(id, newTime));
    }

    @PatchMapping("/{id}/reason")
    @Operation(summary = "Update the medical reason for the appointment")
    public ResponseEntity<Appointment> updateReason(
            @PathVariable Long id,
            @RequestParam String reason) {
        return ResponseEntity.ok(appointmentService.updateReason(id, reason));
    }

    @GetMapping("/doctor/{doctorId}")
    @Operation(summary = "Get all appointments scheduled for a specific doctor")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctor(doctorId));
    }

    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Get all appointments scheduled for a specific patient")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatient(patientId));
    }

    @GetMapping("/range")
    @Operation(summary = "Get all appointments scheduled between two date-times")
    public ResponseEntity<List<Appointment>> getAppointmentsBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(appointmentService.getAppointmentsBetweenDates(start, end));
    }
}
