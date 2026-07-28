package com.tarun.HospitalManagement.controller;

import com.tarun.HospitalManagement.dto.request.DoctorRequestDTO;
import com.tarun.HospitalManagement.dto.response.AppointmentResponseDTO;
import com.tarun.HospitalManagement.dto.response.DepartmentResponseDTO;
import com.tarun.HospitalManagement.dto.response.DoctorResponseDTO;
import com.tarun.HospitalManagement.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
@Tag(name = "Doctor Controller", description = "Endpoints for managing doctor records")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    @Operation(summary = "Register a new doctor")
    public ResponseEntity<DoctorResponseDTO> createDoctor(@Valid @RequestBody DoctorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a doctor by ID")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @GetMapping
    @Operation(summary = "Get all registered doctors")
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove a doctor by ID")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/email")
    @Operation(summary = "Update doctor's email address")
    public ResponseEntity<DoctorResponseDTO> updateDoctorEmail(@PathVariable Long id, @RequestParam String email) {
        return ResponseEntity.ok(doctorService.updateDoctorEmail(id, email));
    }

    @PatchMapping("/{id}/specialization")
    @Operation(summary = "Update doctor's area of specialization")
    public ResponseEntity<DoctorResponseDTO> updateSpecialization(@PathVariable Long id, @RequestParam String specialization) {
        return ResponseEntity.ok(doctorService.updateSpecialization(id, specialization));
    }

    @GetMapping("/{id}/appointments")
    @Operation(summary = "Get all appointments assigned to a doctor")
    public ResponseEntity<List<AppointmentResponseDTO>> getDoctorAppointments(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorAppointments(id));
    }

    @GetMapping("/{id}/departments")
    @Operation(summary = "Get all departments a doctor belongs to")
    public ResponseEntity<Set<DepartmentResponseDTO>> getDoctorDepartments(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorDepartments(id));
    }

    @PostMapping("/{id}/departments/{departmentId}")
    @Operation(summary = "Assign a doctor to a department")
    public ResponseEntity<Void> assignDoctorToDepartment(@PathVariable Long id, @PathVariable Long departmentId) {
        doctorService.assignDoctorToDepartment(id, departmentId);
        return ResponseEntity.ok().build();
    }
}
