package be.library;

import java.util.Date;

public class Book {
	private String	author;
	private String	isbn;
	private Date	date;
	private String	publisher;
	private String	title;
	private String  language;
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String lang) {
		this.language = lang;
	}
}
