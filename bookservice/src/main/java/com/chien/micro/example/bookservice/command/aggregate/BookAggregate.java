package com.chien.micro.example.bookservice.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.chien.micro.example.bookservice.command.command.CreateBookCommand;
import com.chien.micro.example.bookservice.command.event.BookCreateEvent;
@Aggregate
public class BookAggregate {
	@AggregateIdentifier
	private String bookId;
	private String name;
	private String author;
	private boolean isReady;
	
	public BookAggregate () {
		
	}
	
	@CommandHandler
	public BookAggregate (CreateBookCommand createBookCommand) {
		BookCreateEvent bookEvent = new BookCreateEvent();
		BeanUtils.copyProperties(createBookCommand, bookEvent);
		AggregateLifecycle.apply(bookEvent);
	}
	@EventSourcingHandler 
	public void on (BookCreateEvent event) {
		this.bookId = event.getBookId();
		this.author = event.getAuthor();
		this.isReady = event.isReady();
		this.name = event.getName();
	}
}
