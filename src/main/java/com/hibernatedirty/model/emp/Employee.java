package com.hibernatedirty.model.emp;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Builder
@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue
    Long id;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    Integer age;

    @Column(name = "no_of_children")
    Integer noOfChildren;
    Boolean spouse;

    @JsonManagedReference
    @OneToOne(cascade = { 
        		CascadeType.MERGE,
   	    		CascadeType.PERSIST,
   	    		CascadeType.REMOVE
    })
    @JoinColumn(name="address")
     Address address;


    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", 
	    cascade = { 
	    		CascadeType.MERGE,
	    		CascadeType.PERSIST,
	    		CascadeType.REMOVE
    })
     List<PhoneNumber> phoneNumbers;



    @ElementCollection
    @CollectionTable(name="hobbies", joinColumns = @JoinColumn(name="id"))
    @Column(name="hobby")
     List<String> hobbies = new ArrayList<>();

}
