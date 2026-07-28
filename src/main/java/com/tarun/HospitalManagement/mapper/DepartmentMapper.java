package com.tarun.HospitalManagement.mapper;

import com.tarun.HospitalManagement.dto.request.DepartmentRequestDTO;
import com.tarun.HospitalManagement.dto.response.DepartmentResponseDTO;
import com.tarun.HospitalManagement.dto.response.DoctorSummaryDTO;
import com.tarun.HospitalManagement.entity.Department;
import com.tarun.HospitalManagement.entity.Doctor;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "headDoctor", ignore = true)
    @Mapping(target = "doctors", ignore = true)
    Department toEntity(DepartmentRequestDTO dto);

    @Mapping(source = "headDoctor", target = "headDoctor")
    @Mapping(source = "doctors", target = "doctors")
    DepartmentResponseDTO toResponseDTO(Department department);

    DoctorSummaryDTO toDoctorSummary(Doctor doctor);

    Set<DoctorSummaryDTO> toDoctorSummarySet(Set<Doctor> doctors);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "headDoctor", ignore = true)
    @Mapping(target = "doctors", ignore = true)
    void updateEntityFromDTO(DepartmentRequestDTO dto, @MappingTarget Department department);
}
