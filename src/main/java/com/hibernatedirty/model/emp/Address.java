package com.hibernatedirty.model.emp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Builder
@Entity
@Table
public class Address {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "street_address")
    String streetAddress;
    String city;
    String state;
    String country;

    @Column(name = "postal_address")
     String postalCode;

    @JsonBackReference
    @OneToOne(mappedBy="address", 
    		cascade = { 
    	    		CascadeType.MERGE,
    	    		CascadeType.PERSIST,
    	    		CascadeType.REMOVE
    })
     Employee employee;
}
