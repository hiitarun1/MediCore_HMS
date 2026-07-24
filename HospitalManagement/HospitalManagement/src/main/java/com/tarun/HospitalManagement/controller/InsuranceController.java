package com.tarun.HospitalManagement.controller;

import com.tarun.HospitalManagement.entity.Insurance;
import com.tarun.HospitalManagement.entity.Patient;
import com.tarun.HospitalManagement.service.InsuranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/insurances")
@RequiredArgsConstructor
@Tag(name = "Insurance Controller", description = "Endpoints for managing insurance policies")
public class InsuranceController {

    private final InsuranceService insuranceService;

    @PostMapping
    @Operation(summary = "Create a new insurance record")
    public ResponseEntity<Insurance> createInsurance(@RequestBody Insurance insurance) {
        return ResponseEntity.ok(insuranceService.createInsurance(insurance));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an insurance record by ID")
    public ResponseEntity<Insurance> getInsuranceById(@PathVariable Long id) {
        return ResponseEntity.ok(insuranceService.getInsuranceById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an insurance record by ID")
    public ResponseEntity<Void> deleteInsurance(@PathVariable Long id) {
        insuranceService.deleteInsurance(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/patient")
    @Operation(summary = "Get the patient associated with the insurance policy")
    public ResponseEntity<Patient> getPatientByInsurance(@PathVariable Long id) {
        return ResponseEntity.ok(insuranceService.getPatientByInsurance(id));
    }

    @GetMapping("/{id}/valid")
    @Operation(summary = "Check if the insurance policy is currently valid")
    public ResponseEntity<Boolean> isInsuranceValid(@PathVariable Long id) {
        return ResponseEntity.ok(insuranceService.isInsuranceValid(id));
    }

    @PostMapping("/assign")
    @Operation(summary = "Assign a new/existing insurance policy to a patient")
    public ResponseEntity<Patient> assignInsuranceToPatient(
            @RequestBody Insurance insurance,
            @RequestParam Long patientId) {
        return ResponseEntity.ok(insuranceService.assignInsuranceToPatient(insurance, patientId));
    }
}
