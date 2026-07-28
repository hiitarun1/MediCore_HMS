package com.tarun.HospitalManagement.service;

import com.tarun.HospitalManagement.dto.request.DepartmentRequestDTO;
import com.tarun.HospitalManagement.dto.response.DepartmentResponseDTO;
import com.tarun.HospitalManagement.dto.response.DoctorSummaryDTO;
import com.tarun.HospitalManagement.entity.Department;
import com.tarun.HospitalManagement.entity.Doctor;
import com.tarun.HospitalManagement.mapper.DepartmentMapper;
import com.tarun.HospitalManagement.mapper.DoctorMapper;
import com.tarun.HospitalManagement.repository.DepartmentRepository;
import com.tarun.HospitalManagement.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final DepartmentMapper departmentMapper;
    private final DoctorMapper doctorMapper;

    @Transactional
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto) {
        Department department = departmentMapper.toEntity(dto);
        if (dto.getHeadDoctorId() != null) {
            Doctor headDoctor = doctorRepository.findById(dto.getHeadDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            department.setHeadDoctor(headDoctor);
        }
        Department saved = departmentRepository.save(department);
        return departmentMapper.toResponseDTO(saved);
    }

    @Transactional
    public DepartmentResponseDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return departmentMapper.toResponseDTO(department);
    }

    @Transactional
    public List<DepartmentResponseDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toResponseDTO)
                .toList();
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
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        department.getDoctors().add(doctor);
        doctor.getDepartments().add(department);

        departmentRepository.save(department);
    }

    @Transactional
    public void removeDoctor(Long departmentId, Long doctorId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        department.getDoctors().remove(doctor);
        doctor.getDepartments().remove(department);

        departmentRepository.save(department);
    }

    @Transactional
    public void assignHeadDoctor(Long departmentId, Long doctorId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        department.setHeadDoctor(doctor);
        departmentRepository.save(department);
    }

    @Transactional
    public Set<DoctorSummaryDTO> getDoctorsInDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return department.getDoctors().stream()
                .map(doctorMapper::toSummaryDTO)
                .collect(Collectors.toSet());
    }
}
