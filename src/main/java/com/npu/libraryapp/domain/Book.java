package com.npu.libraryapp.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class Book {
	@NotEmpty
	String title;
	@NotEmpty
	String publisher;
	@NotEmpty
	String author;
	int numOfBooks;
	int bookId;
	
	double price;
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", publisher=" + publisher
				+ ", author=" + author + ", numOfBooks=" + numOfBooks
				+ ", bookId=" + bookId + ", price=" + price + "]";
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Book(){
		
	}
	
	public Book(String title, String publisher, String author, double price, int numOfBooks){
		this.title = title;
		this.publisher = publisher;
		this.author = author;
		this.numOfBooks = numOfBooks;
		this.price = price;
	}
	
	
	public int getBookId(){
		return bookId;
	}
	
	public void setBookId(int bookId){
		this.bookId = bookId;
	}
	
	public double getPrice(){
		return price;
	}
	
	public int getNumOfBooks(){
		return numOfBooks;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getPublisher(){
		return publisher;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setPublisher(String publisher){
		this.publisher = publisher;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public void setNumOfBooks(int numOfBooks){
		this.numOfBooks = numOfBooks;
	}

}