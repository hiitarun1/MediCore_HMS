package com.tarun.HospitalManagement.repository;

import com.tarun.HospitalManagement.dto.BloodGroupCountResponseEntity;
import com.tarun.HospitalManagement.entity.Patient;
import com.tarun.HospitalManagement.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByName(String name);
    List<Patient> findByBirthDateOrEmail(LocalDate date, String email);

    List<Patient> findByBirthDateBetween(LocalDate start, LocalDate end);

    List<Patient> findByNameContaining(String query);

    @Query("select t from Patient t where t.bloodGroup = :bloodGroup")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("select p from Patient p where p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    @Query("""
        select new com.tarun.HospitalManagement.dto.BloodGroupCountResponseEntity(p.bloodGroup, count(p))
        from Patient p
        group by p.bloodGroup
        """)
    List<BloodGroupCountResponseEntity> countEachByBloodGroupType();

    @Query(value = "select * from Patient",nativeQuery = true)
    List<Patient> findAllPatients();

    @Transactional
    @Modifying
    @Query("Update Patient p set p.name=:name where p.id=:id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);
}
