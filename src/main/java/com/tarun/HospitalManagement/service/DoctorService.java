package com.tarun.HospitalManagement.service;

import com.tarun.HospitalManagement.dto.request.DoctorRequestDTO;
import com.tarun.HospitalManagement.dto.response.AppointmentResponseDTO;
import com.tarun.HospitalManagement.dto.response.DepartmentResponseDTO;
import com.tarun.HospitalManagement.dto.response.DoctorResponseDTO;
import com.tarun.HospitalManagement.entity.Department;
import com.tarun.HospitalManagement.entity.Doctor;
import com.tarun.HospitalManagement.mapper.AppointmentMapper;
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
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorMapper doctorMapper;
    private final AppointmentMapper appointmentMapper;
    private final DepartmentMapper departmentMapper;

    @Transactional
    public DoctorResponseDTO createDoctor(DoctorRequestDTO dto) {
        Doctor doctor = doctorMapper.toEntity(dto);
        Doctor saved = doctorRepository.save(doctor);
        return doctorMapper.toResponseDTO(saved);
    }

    @Transactional
    public DoctorResponseDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return doctorMapper.toResponseDTO(doctor);
    }

    @Transactional
    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor not found");
        }
        doctorRepository.deleteById(id);
    }

    @Transactional
    public DoctorResponseDTO updateDoctorEmail(Long id, String email) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctor.setEmail(email);
        Doctor saved = doctorRepository.save(doctor);
        return doctorMapper.toResponseDTO(saved);
    }

    @Transactional
    public DoctorResponseDTO updateSpecialization(Long id, String specialization) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctor.setSpecialization(specialization);
        Doctor saved = doctorRepository.save(doctor);
        return doctorMapper.toResponseDTO(saved);
    }

    @Transactional
    public List<AppointmentResponseDTO> getDoctorAppointments(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return appointmentMapper.toResponseDTOList(doctor.getAppointmentList());
    }

    @Transactional
    public Set<DepartmentResponseDTO> getDoctorDepartments(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return doctor.getDepartments().stream()
                .map(departmentMapper::toResponseDTO)
                .collect(Collectors.toSet());
    }

    @Transactional
    public void assignDoctorToDepartment(Long doctorId, Long departmentId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        department.getDoctors().add(doctor);
        doctor.getDepartments().add(department);

        doctorRepository.save(doctor);
        departmentRepository.save(department);
    }
}
