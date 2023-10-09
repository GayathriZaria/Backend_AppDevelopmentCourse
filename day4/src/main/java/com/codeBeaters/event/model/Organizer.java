package com.codeBeaters.event.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organizer")
public class Organizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizerId;

    @Column(length = 250, nullable = false)
    private String organizerName;

    @Column(nullable = false)
    private float organizerSalary;

    @Column(nullable = false)
    private Long yearsofExp;

    @Column(nullable = false)
    private String organizerDept;

    @Column(length = 50000, nullable = true)
    private String organizerSpec;
}
