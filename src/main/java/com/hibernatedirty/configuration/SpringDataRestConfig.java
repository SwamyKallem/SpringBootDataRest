package com.hibernatedirty.configuration;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.hibernatedirty.model.emp.Employee;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@Component
@RepositoryEventHandler
@AllArgsConstructor
public class SpringDataRestConfig {
	
	EntityManager entityManager;
	
	
	 @HandleBeforeSave  // update or Patch
	 @HandleBeforeCreate //Post
	  public void handlePersonSave(Employee employee) {
		 
		 entityManager.detach(employee);
		 
	    //  delete save update get functionality can be executed
		 
	  }

}
