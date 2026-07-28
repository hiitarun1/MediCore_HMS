package com.tarun.HospitalManagement.service;

import com.tarun.HospitalManagement.dto.request.AppointmentRequestDTO;
import com.tarun.HospitalManagement.dto.response.AppointmentResponseDTO;
import com.tarun.HospitalManagement.entity.Appointment;
import com.tarun.HospitalManagement.entity.Doctor;
import com.tarun.HospitalManagement.entity.Patient;
import com.tarun.HospitalManagement.mapper.AppointmentMapper;
import com.tarun.HospitalManagement.repository.AppointmentRepository;
import com.tarun.HospitalManagement.repository.DoctorRepository;
import com.tarun.HospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentMapper appointmentMapper;

    @Transactional
    public void createNewAppointment(AppointmentRequestDTO dto, Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        Appointment appointment = appointmentMapper.toEntity(dto);

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointment().add(appointment);
        doctor.getAppointmentList().add(appointment);
    }

    @Transactional
    public AppointmentResponseDTO reAssignAppointmentToAnotherDoctr(Long appointmentId, Long doctorId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);

        return appointmentMapper.toResponseDTO(appointment);
    }

    @Transactional
    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public AppointmentResponseDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        return appointmentMapper.toResponseDTO(appointment);
    }

    @Transactional
    public List<AppointmentResponseDTO> getAppointmentsByDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return appointmentMapper.toResponseDTOList(doctor.getAppointmentList());
    }

    @Transactional
    public List<AppointmentResponseDTO> getAppointmentsByPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return appointmentMapper.toResponseDTOList(patient.getAppointment());
    }

    @Transactional
    public void deleteAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        Patient patient = appointment.getPatient();
        Doctor doctor = appointment.getDoctor();
        if (patient != null) {
            patient.getAppointment().remove(appointment);
        }
        if (doctor != null) {
            doctor.getAppointmentList().remove(appointment);
        }
        appointmentRepository.delete(appointment);
    }

    @Transactional
    public AppointmentResponseDTO updateAppointmentTime(Long appointmentId, LocalDateTime newTime) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setAppointmentTime(newTime);
        Appointment saved = appointmentRepository.save(appointment);
        return appointmentMapper.toResponseDTO(saved);
    }

    @Transactional
    public AppointmentResponseDTO updateReason(Long appointmentId, String reason) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setReason(reason);
        Appointment saved = appointmentRepository.save(appointment);
        return appointmentMapper.toResponseDTO(saved);
    }

    @Transactional
    public List<AppointmentResponseDTO> getAppointmentsBetweenDates(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByAppointmentTimeBetween(start, end).stream()
                .map(appointmentMapper::toResponseDTO)
                .toList();
    }
}
