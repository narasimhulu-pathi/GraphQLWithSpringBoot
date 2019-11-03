package com.example.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.example.model.Book;
import com.example.model.Employee;
import com.example.repository.BookRepository;
import com.example.repository.EmployeeRepository;
import com.example.service.datafetcher.AllBooksDataFetcher;
import com.example.service.datafetcher.AllEmployeesDataFetcher;
import com.example.service.datafetcher.BookDataFetcher;
import com.example.service.datafetcher.EmployeeDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {
	
	 @Autowired
	    BookRepository bookRepository;
	 
	 @Autowired
	 EmployeeRepository employeeRepository;

	    @Value("classpath:queries.graphql")
	    Resource resource;

	    private GraphQL graphQL;
	    @Autowired
	    private AllBooksDataFetcher allBooksDataFetcher;
	    @Autowired
	    private BookDataFetcher bookDataFetcher;
	    
	    @Autowired
	    private AllEmployeesDataFetcher allEmployeesDataFetcher;
	    
	    @Autowired
	    private EmployeeDataFetcher employeeDataFetcher;

	    // load schema at application start up
	    @PostConstruct
	    private void loadSchema() throws IOException {

	        //Load Books into the Book Repository
	        loadDataIntoHSQL();

	        // get the schema
	        File schemaFile = resource.getFile();
	        // parse schema
	        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
	        RuntimeWiring wiring = buildRuntimeWiring();
	        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
	        graphQL = GraphQL.newGraphQL(schema).build();
	    }

	    private void loadDataIntoHSQL() {

	        Stream.of(
	                new Book("123", "Java", "Ebook",
	                        new String[] {
	                        "Joshua","Jason"
	                        }, "2017"),
	                new Book("124", "Spring", "Orielly",
	                        new String[] {
	                                "Peter", "Sam"
	                        }, "2015"),
	                new Book("125", "Hibernate", "Goyal Bros",
	                        new String[] {
	                                "Venkat", "Ram"
	                        }, "2019")
	        ).forEach(book -> {
	            bookRepository.save(book);
	        });
	        
	       
	        Stream.of(
	            new Employee("1234", "Asish Patel", "50,000","02-Feb-1990","Sullurpeta, Nellore"),
	            new Employee("2234", "Bharath Kumar", "60,000","10-Mar-1991","Obularvari Palli, Kadapa"),
	            new Employee("3234", "Chandra Sekhar", "70,000","03-Apr-1990","Puttur, Chitttoor"),
	            new Employee("4234", "Desaiah", "80,000","07-Feb-1990","Puttur, Nagari"),
	            new Employee("5234", "Eswar", "90,000","08-Feb-1990","YSR Kadapa"),
	            new Employee("6234", "Farhan Akhtar", "90,000","09-Feb-1990","Ananthapur")
	        ).forEach(emp -> {
	        	employeeRepository.save(emp);
	        });
	        
	    }

	    private RuntimeWiring buildRuntimeWiring() {
	        return RuntimeWiring.newRuntimeWiring()
	                .type("Query", typeWiring -> typeWiring
	                        .dataFetcher("allBooks", allBooksDataFetcher)
	                        .dataFetcher("book", bookDataFetcher)
	                        .dataFetcher("employee", employeeDataFetcher)
	                        .dataFetcher("allEmployees", allEmployeesDataFetcher))
	                .build();
	    }


	    public GraphQL getGraphQL() {
	        return graphQL;
	    }
}
