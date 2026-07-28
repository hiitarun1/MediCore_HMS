package com.tarun.HospitalManagement.mapper;

import com.tarun.HospitalManagement.dto.request.AppointmentRequestDTO;
import com.tarun.HospitalManagement.dto.response.AppointmentResponseDTO;
import com.tarun.HospitalManagement.dto.response.AppointmentSummaryDTO;
import com.tarun.HospitalManagement.entity.Appointment;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    Appointment toEntity(AppointmentRequestDTO dto);

    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "patient.name", target = "patientName")
    @Mapping(source = "doctor.id", target = "doctorId")
    @Mapping(source = "doctor.name", target = "doctorName")
    AppointmentResponseDTO toResponseDTO(Appointment appointment);

    List<AppointmentResponseDTO> toResponseDTOList(List<Appointment> appointments);

    AppointmentSummaryDTO toSummaryDTO(Appointment appointment);

    List<AppointmentSummaryDTO> toSummaryDTOList(List<Appointment> appointments);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    void updateEntityFromDTO(AppointmentRequestDTO dto, @MappingTarget Appointment appointment);
}
