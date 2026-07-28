package com.tarun.HospitalManagement.mapper;

import com.tarun.HospitalManagement.dto.request.PatientRequestDTO;
import com.tarun.HospitalManagement.dto.response.AppointmentSummaryDTO;
import com.tarun.HospitalManagement.dto.response.PatientResponseDTO;
import com.tarun.HospitalManagement.entity.Appointment;
import com.tarun.HospitalManagement.entity.Patient;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InsuranceMapper.class})
public interface PatientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "insurance", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    Patient toEntity(PatientRequestDTO dto);

    @Mapping(source = "insurance", target = "insurance")
    @Mapping(source = "appointment", target = "appointments")
    PatientResponseDTO toResponseDTO(Patient patient);

    AppointmentSummaryDTO toAppointmentSummary(Appointment appointment);

    List<AppointmentSummaryDTO> toAppointmentSummaryList(List<Appointment> appointments);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "insurance", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    void updateEntityFromDTO(PatientRequestDTO dto, @MappingTarget Patient patient);
}
