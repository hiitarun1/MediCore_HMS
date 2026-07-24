package com.tarun.HospitalManagement.service;

import com.tarun.HospitalManagement.repository.InsuranceRepository;
import com.tarun.HospitalManagement.repository.PatientRepository;
import com.tarun.HospitalManagement.entity.Insurance;
import com.tarun.HospitalManagement.entity.Patient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public  Patient assignInsuranceToPatient(Insurance insurance, Long patientId){
        Patient p1=patientRepository.findById(patientId).orElseThrow(()->new RuntimeException("no id found for patient"));
        
        p1.setInsurance(insurance);
        insurance.setPatient(p1); //bidirectional consistency maintain

        return patientRepository.save(p1);
    }

    @Transactional
    public Insurance createInsurance(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    public Insurance getInsuranceById(Long id) {
        return insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insurance not found"));
    }

    @Transactional
    public void deleteInsurance(Long id) {
        Insurance insurance = getInsuranceById(id);
        Patient patient = insurance.getPatient();
        if (patient != null) {
            patient.setInsurance(null);
            patientRepository.save(patient);
        }
        insuranceRepository.delete(insurance);
    }

    public Patient getPatientByInsurance(Long insuranceId) {
        Insurance insurance = getInsuranceById(insuranceId);
        return insurance.getPatient();
    }

    public boolean isInsuranceValid(Long insuranceId) {
        Insurance insurance = getInsuranceById(insuranceId);
        return insurance.getValidUntil() != null && insurance.getValidUntil().isAfter(java.time.LocalDate.now());
    }
}
