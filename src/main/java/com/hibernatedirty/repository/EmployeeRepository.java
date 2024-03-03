package com.hibernatedirty.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hibernatedirty.model.emp.Employee;

@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>{

}
