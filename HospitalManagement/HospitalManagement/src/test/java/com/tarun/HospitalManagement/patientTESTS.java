package com.tarun.HospitalManagement;

import com.tarun.HospitalManagement.repository.PatientRepository;
import com.tarun.HospitalManagement.dto.BloodGroupCountResponseEntity;
import com.tarun.HospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class patientTESTS {

    @Autowired
    private PatientRepository patientRepo;
    @Autowired
    private PatientService patientService;

//    @Test
//    public void testPatientRepo(){
//        List<Patient> patientlist=patientRepo.findAll();
//        System.out.println(patientlist);
//
//        Patient p1=new Patient();
//        patientRepo.save(p1);
//    }

    @Test
    public void testTransactionMethods() {
//        Patient patient = patientService.getPatientById(1L);
//        System.out.println(patient);

//        Patient patient=patientRepo.findByName("Rahul Sharma");
//        System.out.println(patient);
//
//        List<Patient> patientList= patientRepo.findByBirthDateOrEmail(LocalDate.of(2002, 9, 21),"rahul.sharma@example.com");
//        for(Patient p : patientList){
//            System.out.println(p);
//        }

//        List<Patient> patientList= patientRepo.findByNameContaining("Ra");
//        for(Patient p : patientList){
//            System.out.println(p);
//        }


//        List<Patient> patientBloodGroupList= patientRepo.findByBloodGroup(BloodGroupType.O_Pos);
//        for(Patient p : patientBloodGroupList){
//            System.out.println(p);
//        }

//        List<Patient> patientBornAfterDate= patientRepo.findByBornAfterDate(LocalDate.of(2001,03,22));
//        for(Patient p : patientBornAfterDate){
//            System.out.println(p);
//        }

//        List<Object[]> bloodGroupCount=patientRepo.countEachByBloodGroupType();
//        for(Object[] objects : bloodGroupCount){
//            System.out.println(objects[0]+" "+objects[1]);
//        }

        List<BloodGroupCountResponseEntity> bloodGroupCount = patientRepo.countEachByBloodGroupType();
        for (BloodGroupCountResponseEntity b : bloodGroupCount) {
            System.out.println(b);
        }

//        List<Patient> patientList=patientRepo.findAllPatients();
//        System.out.println(patientList);


//        int rowsAffected=patientRepo.updateNameWithId("Mehul",1L);
//        System.out.println(rowsAffected);
//    }
    }
}
