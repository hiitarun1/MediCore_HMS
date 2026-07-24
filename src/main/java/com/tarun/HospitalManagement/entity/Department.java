package com.tarun.HospitalManagement.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import javax.print.Doc;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @OneToOne
    @JoinColumn(name="head_doctor_id", unique = true)
    @JsonIgnoreProperties({"departments", "appointmentList"})
    private Doctor headDoctor;

    @ManyToMany
    @JoinTable(name = "department_doctor", joinColumns = @JoinColumn(name="department_id"), inverseJoinColumns = @JoinColumn(name="doctor_id"))
    @JsonIgnoreProperties({"departments", "appointmentList"})
    private Set<Doctor> doctors=new HashSet<>();

}

