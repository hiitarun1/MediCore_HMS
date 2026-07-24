package com.tarun.HospitalManagement.service;

import com.tarun.HospitalManagement.repository.AppointmentRepository;
import com.tarun.HospitalManagement.repository.DoctorRepository;
import com.tarun.HospitalManagement.repository.PatientRepository;
import com.tarun.HospitalManagement.entity.Appointment;
import com.tarun.HospitalManagement.entity.Doctor;
import com.tarun.HospitalManagement.entity.Patient;
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

    @Transactional
    public void createNewAppointment(Appointment appointment, Long doctorId, Long PatientId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(PatientId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("appointment should not exist");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointment().add(appointment);
        doctor.getAppointmentList().add(appointment);

        Doctor oldDoctor = appointment.getDoctor();
        if (oldDoctor != null) {
            oldDoctor.getAppointmentList().remove(appointment);
        }

        appointment.setDoctor(doctor);
        doctor.getAppointmentList().add(appointment);
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctr(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);

        //doctor.getAppointments().add(appointment);

        return appointment;
    }

    //get All Appointments
    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return doctor.getAppointmentList();
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return patient.getAppointment();
    }

    @Transactional
    public void deleteAppointment(Long appointmentId) {
        Appointment appointment = getAppointmentById(appointmentId);
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
    public Appointment updateAppointmentTime(Long appointmentId, LocalDateTime newTime) {
        Appointment appointment = getAppointmentById(appointmentId);
        appointment.setAppointmentTime(newTime);
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment updateReason(Long appointmentId, String reason) {
        Appointment appointment = getAppointmentById(appointmentId);
        appointment.setReason(reason);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsBetweenDates(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByAppointmentTimeBetween(start, end);
    }
}
