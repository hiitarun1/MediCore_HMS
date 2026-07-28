package com.tarun.HospitalManagement.mapper;

import com.tarun.HospitalManagement.dto.request.DoctorRequestDTO;
import com.tarun.HospitalManagement.dto.response.AppointmentSummaryDTO;
import com.tarun.HospitalManagement.dto.response.DoctorResponseDTO;
import com.tarun.HospitalManagement.dto.response.DoctorSummaryDTO;
import com.tarun.HospitalManagement.entity.Appointment;
import com.tarun.HospitalManagement.entity.Department;
import com.tarun.HospitalManagement.entity.Doctor;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "departments", ignore = true)
    @Mapping(target = "appointmentList", ignore = true)
    Doctor toEntity(DoctorRequestDTO dto);

    @Mapping(target = "departmentIds", expression = "java(mapDepartmentIds(doctor.getDepartments()))")
    @Mapping(source = "appointmentList", target = "appointments")
    DoctorResponseDTO toResponseDTO(Doctor doctor);

    DoctorSummaryDTO toSummaryDTO(Doctor doctor);

    Set<DoctorSummaryDTO> toSummaryDTOSet(Set<Doctor> doctors);

    AppointmentSummaryDTO toAppointmentSummary(Appointment appointment);

    List<AppointmentSummaryDTO> toAppointmentSummaryList(List<Appointment> appointments);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "departments", ignore = true)
    @Mapping(target = "appointmentList", ignore = true)
    void updateEntityFromDTO(DoctorRequestDTO dto, @MappingTarget Doctor doctor);

    default Set<Long> mapDepartmentIds(Set<Department> departments) {
        if (departments == null) return null;
        return departments.stream().map(Department::getId).collect(Collectors.toSet());
    }
}
