package com.tarun.HospitalManagement.service;

import com.tarun.HospitalManagement.entity.Department;
import com.tarun.HospitalManagement.entity.Doctor;
import com.tarun.HospitalManagement.repository.DepartmentRepository;
import com.tarun.HospitalManagement.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Transactional
    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException("Department not found");
        }
        departmentRepository.deleteById(id);
    }

    @Transactional
    public void assignDoctor(Long departmentId, Long doctorId) {
        Department department = getDepartmentById(departmentId);
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        department.getDoctors().add(doctor);
        doctor.getDepartments().add(department);

        departmentRepository.save(department);
    }

    @Transactional
    public void removeDoctor(Long departmentId, Long doctorId) {
        Department department = getDepartmentById(departmentId);
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        department.getDoctors().remove(doctor);
        doctor.getDepartments().remove(department);

        departmentRepository.save(department);
    }

    @Transactional
    public void assignHeadDoctor(Long departmentId, Long doctorId) {
        Department department = getDepartmentById(departmentId);
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        department.setHeadDoctor(doctor);
        departmentRepository.save(department);
    }

    public Set<Doctor> getDoctorsInDepartment(Long departmentId) {
        Department department = getDepartmentById(departmentId);
        return department.getDoctors();
    }
}
