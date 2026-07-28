package com.tarun.HospitalManagement;

import com.tarun.HospitalManagement.dto.request.AppointmentRequestDTO;
import com.tarun.HospitalManagement.dto.request.InsuranceRequestDTO;
import com.tarun.HospitalManagement.dto.response.PatientResponseDTO;
import com.tarun.HospitalManagement.entity.Doctor;
import com.tarun.HospitalManagement.entity.Patient;
import com.tarun.HospitalManagement.repository.DoctorRepository;
import com.tarun.HospitalManagement.repository.PatientRepository;
import com.tarun.HospitalManagement.service.AppointmentService;
import com.tarun.HospitalManagement.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testInsurance(){
        InsuranceRequestDTO insurance = InsuranceRequestDTO.builder()
                .policyNumber("HDFC 1232")
                .provider("HDFC")
                .validUntil(LocalDate.of(2028,12,2)).build();

        PatientResponseDTO p1 = insuranceService.assignInsuranceToPatient(insurance, 1L);
        System.out.println(p1);
    }

    @Test
    public void testCreateAppointment(){
        Doctor doctor = Doctor.builder()
                .name("Dr. Smith")
                .email("smith@example.com")
                .specialization("Oncology")
                .build();
        doctor = doctorRepository.save(doctor);

        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setEmail("john.doe@example.com");
        patient = patientRepository.save(patient);

        AppointmentRequestDTO appointment = AppointmentRequestDTO.builder()
                .appointmentTime(LocalDateTime.of(2026,2,14,6,12,30))
                .reason("cancer")
                .build();

        appointmentService.createNewAppointment(appointment, doctor.getId(), patient.getId());
    }
}
