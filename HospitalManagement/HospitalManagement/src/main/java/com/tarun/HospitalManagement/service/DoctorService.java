package com.tarun.HospitalManagement.service;

import com.tarun.HospitalManagement.entity.Appointment;
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
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Transactional
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor not found");
        }
        doctorRepository.deleteById(id);
    }

    @Transactional
    public Doctor updateDoctorEmail(Long id, String email) {
        Doctor doctor = getDoctorById(id);
        doctor.setEmail(email);
        return doctorRepository.save(doctor);
    }

    @Transactional
    public Doctor updateSpecialization(Long id, String specialization) {
        Doctor doctor = getDoctorById(id);
        doctor.setSpecialization(specialization);
        return doctorRepository.save(doctor);
    }

    public List<Appointment> getDoctorAppointments(Long doctorId) {
        Doctor doctor = getDoctorById(doctorId);
        return doctor.getAppointmentList();
    }

    public Set<Department> getDoctorDepartments(Long doctorId) {
        Doctor doctor = getDoctorById(doctorId);
        return doctor.getDepartments();
    }

    @Transactional
    public void assignDoctorToDepartment(Long doctorId, Long departmentId) {
        Doctor doctor = getDoctorById(doctorId);
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        
        department.getDoctors().add(doctor);
        doctor.getDepartments().add(department);
        
        doctorRepository.save(doctor);
        departmentRepository.save(department);
    }
}
