package com.tarun.HospitalManagement.service;

import com.tarun.HospitalManagement.dto.BloodGroupCountResponseEntity;
import com.tarun.HospitalManagement.dto.request.PatientRequestDTO;
import com.tarun.HospitalManagement.dto.response.AppointmentResponseDTO;
import com.tarun.HospitalManagement.dto.response.PatientResponseDTO;
import com.tarun.HospitalManagement.entity.Appointment;
import com.tarun.HospitalManagement.entity.Insurance;
import com.tarun.HospitalManagement.entity.Patient;
import com.tarun.HospitalManagement.entity.type.BloodGroupType;
import com.tarun.HospitalManagement.mapper.AppointmentMapper;
import com.tarun.HospitalManagement.mapper.PatientMapper;
import com.tarun.HospitalManagement.repository.InsuranceRepository;
import com.tarun.HospitalManagement.repository.PatientRepository;
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
    private final PatientMapper patientMapper;
    private final AppointmentMapper appointmentMapper;

    @Transactional
    public PatientResponseDTO createPatient(PatientRequestDTO dto) {
        Patient patient = patientMapper.toEntity(dto);
        if (dto.getInsuranceId() != null) {
            Insurance insurance = insuranceRepository.findById(dto.getInsuranceId())
                    .orElseThrow(() -> new RuntimeException("Insurance not found"));
            patient.setInsurance(insurance);
        }
        Patient saved = patientRepository.save(patient);
        return patientMapper.toResponseDTO(saved);
    }

    @Transactional
    public PatientResponseDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return patientMapper.toResponseDTO(patient);
    }

    @Transactional
    public List<PatientResponseDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found");
        }
        patientRepository.deleteById(id);
    }

    @Transactional
    public PatientResponseDTO searchByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name does not exist");
        }
        Patient patient = patientRepository.findByName(name);
        return patientMapper.toResponseDTO(patient);
    }

    @Transactional
    public List<PatientResponseDTO> getPatientsByBirthDateRange(LocalDate start, LocalDate end) {
        return patientRepository.findByBirthDateBetween(start, end).stream()
                .map(patientMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public List<PatientResponseDTO> getPatientsByBloodGroup(BloodGroupType bloodGroup) {
        return patientRepository.findByBloodGroup(bloodGroup).stream()
                .map(patientMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public List<AppointmentResponseDTO> getPatientAppointments(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return appointmentMapper.toResponseDTOList(patient.getAppointment());
    }

    @Transactional
    public int updatePatientName(Long id, String name) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found");
        }
        return patientRepository.updateNameWithId(name, id);
    }

    @Transactional
    public void removeInsurance(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("patient id does not exist"));
        Insurance insurance = patient.getInsurance();
        if (insurance != null) {
            patient.setInsurance(null);
            insurance.setPatient(null);
            insuranceRepository.delete(insurance);
        }
    }

    public List<BloodGroupCountResponseEntity> countEachByBloodGroupType() {
        return patientRepository.countEachByBloodGroupType();
    }
}
