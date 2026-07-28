package com.tarun.HospitalManagement.service;

import com.tarun.HospitalManagement.dto.request.InsuranceRequestDTO;
import com.tarun.HospitalManagement.dto.response.InsuranceResponseDTO;
import com.tarun.HospitalManagement.dto.response.PatientResponseDTO;
import com.tarun.HospitalManagement.entity.Insurance;
import com.tarun.HospitalManagement.entity.Patient;
import com.tarun.HospitalManagement.mapper.InsuranceMapper;
import com.tarun.HospitalManagement.mapper.PatientMapper;
import com.tarun.HospitalManagement.repository.InsuranceRepository;
import com.tarun.HospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;
    private final InsuranceMapper insuranceMapper;
    private final PatientMapper patientMapper;

    @Transactional
    public PatientResponseDTO assignInsuranceToPatient(InsuranceRequestDTO dto, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("no id found for patient"));

        Insurance insurance = insuranceMapper.toEntity(dto);
        patient.setInsurance(insurance);
        insurance.setPatient(patient);

        Patient saved = patientRepository.save(patient);
        return patientMapper.toResponseDTO(saved);
    }

    @Transactional
    public InsuranceResponseDTO createInsurance(InsuranceRequestDTO dto) {
        Insurance insurance = insuranceMapper.toEntity(dto);
        Insurance saved = insuranceRepository.save(insurance);
        return insuranceMapper.toResponseDTO(saved);
    }

    @Transactional
    public InsuranceResponseDTO getInsuranceById(Long id) {
        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insurance not found"));
        return insuranceMapper.toResponseDTO(insurance);
    }

    @Transactional
    public void deleteInsurance(Long id) {
        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insurance not found"));
        Patient patient = insurance.getPatient();
        if (patient != null) {
            patient.setInsurance(null);
            patientRepository.save(patient);
        }
        insuranceRepository.delete(insurance);
    }

    @Transactional
    public PatientResponseDTO getPatientByInsurance(Long insuranceId) {
        Insurance insurance = insuranceRepository.findById(insuranceId)
                .orElseThrow(() -> new RuntimeException("Insurance not found"));
        return patientMapper.toResponseDTO(insurance.getPatient());
    }

    public boolean isInsuranceValid(Long insuranceId) {
        Insurance insurance = insuranceRepository.findById(insuranceId)
                .orElseThrow(() -> new RuntimeException("Insurance not found"));
        return insurance.getValidUntil() != null && insurance.getValidUntil().isAfter(java.time.LocalDate.now());
    }
}
