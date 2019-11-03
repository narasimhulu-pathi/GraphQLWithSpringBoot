package com.example.service.datafetcher;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.Book;
import com.example.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookDataFetcher implements DataFetcher<Book>{

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
    	
    	String isn = dataFetchingEnvironment.getArgument("id");
        
        Optional<Book> book = this.bookRepository.findById(isn);

        if (book.isPresent()) {
            return book.get();
        } else {
            return null;
        }
    }
}
