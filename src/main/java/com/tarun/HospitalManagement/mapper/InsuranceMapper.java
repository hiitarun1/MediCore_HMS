package com.tarun.HospitalManagement.mapper;

import com.tarun.HospitalManagement.dto.request.InsuranceRequestDTO;
import com.tarun.HospitalManagement.dto.response.InsuranceResponseDTO;
import com.tarun.HospitalManagement.dto.response.InsuranceSummaryDTO;
import com.tarun.HospitalManagement.entity.Insurance;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {

    Insurance toEntity(InsuranceRequestDTO dto);

    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "patient.name", target = "patientName")
    InsuranceResponseDTO toResponseDTO(Insurance insurance);

    InsuranceSummaryDTO toSummaryDTO(Insurance insurance);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(InsuranceRequestDTO dto, @MappingTarget Insurance insurance);
}
