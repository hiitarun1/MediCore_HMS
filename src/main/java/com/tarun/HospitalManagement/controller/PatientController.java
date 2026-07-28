package com.tarun.HospitalManagement.controller;

import com.tarun.HospitalManagement.dto.BloodGroupCountResponseEntity;
import com.tarun.HospitalManagement.dto.request.PatientRequestDTO;
import com.tarun.HospitalManagement.dto.response.AppointmentResponseDTO;
import com.tarun.HospitalManagement.dto.response.PatientResponseDTO;
import com.tarun.HospitalManagement.entity.type.BloodGroupType;
import com.tarun.HospitalManagement.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@Tag(name = "Patient Controller", description = "Endpoints for managing patient records")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    @Operation(summary = "Create a new patient record")
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a patient by ID")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping
    @Operation(summary = "Get all patient records")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient record")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search for a patient by exact name")
    public ResponseEntity<PatientResponseDTO> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(patientService.searchByName(name));
    }

    @GetMapping("/birthdate")
    @Operation(summary = "Get patients born within a specific date range")
    public ResponseEntity<List<PatientResponseDTO>> getPatientsByBirthDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(patientService.getPatientsByBirthDateRange(start, end));
    }

    @GetMapping("/bloodgroup/{bloodGroup}")
    @Operation(summary = "Get patients with a specific blood group")
    public ResponseEntity<List<PatientResponseDTO>> getPatientsByBloodGroup(@PathVariable BloodGroupType bloodGroup) {
        return ResponseEntity.ok(patientService.getPatientsByBloodGroup(bloodGroup));
    }

    @GetMapping("/bloodgroup/count")
    @Operation(summary = "Get patients count grouped by blood group")
    public ResponseEntity<List<BloodGroupCountResponseEntity>> countEachByBloodGroupType() {
        return ResponseEntity.ok(patientService.countEachByBloodGroupType());
    }

    @GetMapping("/{id}/appointments")
    @Operation(summary = "Get all appointments for a patient")
    public ResponseEntity<List<AppointmentResponseDTO>> getPatientAppointments(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientAppointments(id));
    }

    @PatchMapping("/{id}/name")
    @Operation(summary = "Update patient name by ID")
    public ResponseEntity<String> updatePatientName(@PathVariable Long id, @RequestParam String name) {
        int rows = patientService.updatePatientName(id, name);
        return ResponseEntity.ok("Successfully updated name. Rows affected: " + rows);
    }

    @DeleteMapping("/{id}/insurance")
    @Operation(summary = "Remove insurance association from a patient")
    public ResponseEntity<Void> removeInsurance(@PathVariable Long id) {
        patientService.removeInsurance(id);
        return ResponseEntity.noContent().build();
    }
}
