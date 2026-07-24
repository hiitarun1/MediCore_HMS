package com.tarun.HospitalManagement.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(length = 100)
    private String specialization;

    @Column(nullable = false,unique = true,length = 100)
    private String email;

    @ManyToMany(mappedBy = "doctors")
    @JsonIgnoreProperties("doctors")
    private Set<Department> departments =new HashSet<>();

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("doctor")
    private List<Appointment> appointmentList=new ArrayList<>();
}
