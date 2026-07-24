package com.tarun.HospitalManagement.service;

import com.tarun.HospitalManagement.entity.Insurance;
import com.tarun.HospitalManagement.entity.Appointment;
import com.tarun.HospitalManagement.entity.type.BloodGroupType;
import com.tarun.HospitalManagement.repository.InsuranceRepository;
import com.tarun.HospitalManagement.repository.PatientRepository;
import com.tarun.HospitalManagement.entity.Patient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    
    private final PatientRepository patientRepository;
    private final InsuranceRepository insuranceRepository;

    @Transactional
    public Patient getPatientById(long id){
        Patient p1=patientRepository.findById(id).orElseThrow();
        Patient p2=patientRepository.findById(id).orElseThrow();
        System.out.println(p1==p2);
        p1.setName("yoyo");

        return p1;
    }

    @Transactional
    public Patient createPatient(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient getPatientById(Long id){
        return patientRepository.findById(id).orElseThrow();
    }

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    @Transactional
    public void deletePatient(Long id){
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found");
        }
        patientRepository.deleteById(id);
    }

    public Patient searchByName(String name){
        if(name==null){
            throw new IllegalArgumentException("name does not exist");
        }
        return patientRepository.findByName(name);
    }

    public List<Patient> getPatientsByBirthDateRange(LocalDate start, LocalDate end) {
        return patientRepository.findByBirthDateBetween(start, end);
    }

    public List<Patient> getPatientsByBloodGroup(BloodGroupType bloodGroup) {
        return patientRepository.findByBloodGroup(bloodGroup);
    }

    public List<Appointment> getPatientAppointments(Long patientId) {
        Patient patient = getPatientById(patientId);
        return patient.getAppointment();
    }

    @Transactional
    public int updatePatientName(Long id, String name) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found");
        }
        return patientRepository.updateNameWithId(name, id);
    }

    @Transactional
    public void removeInsurance(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(()->new RuntimeException("patient id does not exist"));
        Insurance insurance = patient.getInsurance();
        if (insurance != null) {
            patient.setInsurance(null);
            insurance.setPatient(null);
            insuranceRepository.delete(insurance);
        }
    }
}
