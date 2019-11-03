package com.example.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllEmployeesDataFetcher implements DataFetcher<List<Employee>>{

	 @Autowired
	 EmployeeRepository employeeRepository;

	    @Override
	    public List<Employee> get(DataFetchingEnvironment dataFetchingEnvironment) {
	        return employeeRepository.findAll();
	    }

}
