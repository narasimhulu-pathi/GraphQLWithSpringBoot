package com.example.service.datafetcher;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class EmployeeDataFetcher implements DataFetcher<Employee>{

	@Autowired
	 EmployeeRepository employeeRepository;

    @Override
    public Employee get(DataFetchingEnvironment dataFetchingEnvironment) {

        String isn = dataFetchingEnvironment.getArgument("id");
        
        Optional<Employee> emp = this.employeeRepository.findById(isn);

        if (emp.isPresent()) {
            return emp.get();
        } else {
            return null;
        }
    }
}
