package com.example.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.GraphQLService;

import graphql.ExecutionResult;

@RestController
public class EmployeeResource {
	
	@Autowired
    GraphQLService graphQLService;
	
	 @GetMapping("/fetch/{employeeId}")
	    public ResponseEntity<Object> getAllItems(@PathVariable(name="employeeId") String id) {
		 	
		 	String query="";
		 
		 	if("1234".equals(id)) {
		 		query= "{allEmployees{id,name,salary}}";
		 	}else if("2234".equals(id)){
		 		query= "{employee(id:\"2234\") {id,name,salary,dateOfBirth,address}}";
		 	}else if("3234".equals(id)){
		 		query= "{allBooks{isn,title,publisher}}";
		 	}else {
		 		query= "{book(id:\""+ id +"\"){isn,title,publisher,authors}}";
		 	}
	    	
	    	
	        ExecutionResult execute = graphQLService.getGraphQL().execute(query);

	        return new ResponseEntity<>(execute, HttpStatus.OK);
	    }

}
