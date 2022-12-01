package com.chien.micro.example.bookservice.command.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chien.micro.example.bookservice.command.data.Book;
import com.chien.micro.example.bookservice.command.data.BookRepository;

@Component
public class BookEventHandler {

	@Autowired
	private BookRepository bookRepository;
	
	@EventHandler
	public void on(BookCreateEvent event) {
		
		Book book = new Book();
		BeanUtils.copyProperties(event, book);
		bookRepository.save(book);
	}
}
