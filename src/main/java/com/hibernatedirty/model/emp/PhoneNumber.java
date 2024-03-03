package com.hibernatedirty.model.emp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class PhoneNumber {

    @Id
    @GeneratedValue
    private int id;
    private String type;
    private String number;


    @JsonBackReference
    @ManyToOne(cascade= { CascadeType.ALL})
    @JoinColumn(name="employee_id")
    private Employee employee;

}
