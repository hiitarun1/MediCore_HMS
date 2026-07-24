package com.tarun.HospitalManagement.entity;

import com.tarun.HospitalManagement.entity.type.BloodGroupType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "patient")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "patient_name", nullable = false, length = 40)
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "gender")
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group")
    private BloodGroupType bloodGroup;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "Insurance_Id")
    @JsonIgnoreProperties("patient")
    private Insurance insurance;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnoreProperties("patient")
    private List<Appointment> appointment=new ArrayList<>();

}