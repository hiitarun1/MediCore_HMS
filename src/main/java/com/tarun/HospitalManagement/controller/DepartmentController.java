package com.tarun.HospitalManagement.controller;

import com.tarun.HospitalManagement.entity.Department;
import com.tarun.HospitalManagement.entity.Doctor;
import com.tarun.HospitalManagement.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@Tag(name = "Department Controller", description = "Endpoints for managing hospital departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    @Operation(summary = "Create a new department")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.createDepartment(department));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a department by ID")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @GetMapping
    @Operation(summary = "Get all departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a department by ID")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/doctors/{doctorId}")
    @Operation(summary = "Assign a doctor to a department")
    public ResponseEntity<Void> assignDoctor(@PathVariable Long id, @PathVariable Long doctorId) {
        departmentService.assignDoctor(id, doctorId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/doctors/{doctorId}")
    @Operation(summary = "Remove a doctor from a department")
    public ResponseEntity<Void> removeDoctor(@PathVariable Long id, @PathVariable Long doctorId) {
        departmentService.removeDoctor(id, doctorId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/head/{doctorId}")
    @Operation(summary = "Assign a doctor as head of department")
    public ResponseEntity<Void> assignHeadDoctor(@PathVariable Long id, @PathVariable Long doctorId) {
        departmentService.assignHeadDoctor(id, doctorId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/doctors")
    @Operation(summary = "Get all doctors belonging to a department")
    public ResponseEntity<Set<Doctor>> getDoctorsInDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDoctorsInDepartment(id));
    }
}
