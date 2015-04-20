package be.library;

import java.util.Date;

/**
 * Example Service of Library.
 */
public class LibraryService {
	
	public Book[] books = null;
	
	public LibraryService() {
		Book[] results = new Book[5];
		
		Book r = new Book();
		
		r.setAuthor("James, E. L.");
		r.setDate(new Date(2013, 12, 11));
		r.setIsbn("0345803485");
		r.setPublisher("Vintage Books");
		r.setTitle("50 Shades of Grey");
		r.setLanguage("English");
		results[0] = r;
		
		r = new Book();
		r.setAuthor("James Dashner");
		r.setDate(new Date(2014, 3, 21));
		r.setIsbn("0385388896");
		r.setPublisher("The Maze Runner Series");
		r.setTitle("Test");
		r.setLanguage("English");
		
		results[1] = r;
		
		r = new Book();
		r.setAuthor("William Sleator");
		r.setDate(new Date(2008, 3, 1));
		r.setIsbn("0810993562");
		r.setPublisher("Amulet Books");
		r.setTitle("Test");
		r.setLanguage("English");
		
		results[2] = r;
		
		r = new Book();
		r.setAuthor("Douglas Adams");
		r.setDate(new Date(2002, 4, 30));
		r.setIsbn("0345453743");
		r.setPublisher("Del Rey");
		r.setTitle("The Ultimate Hitchhiker's Guide to the Galaxy");
		r.setLanguage("English");
		
		results[3] = r;
		
		r = new Book();
		r.setAuthor("Michalewicz, Zbigniew  Fogel, David B.");
		r.setDate(new Date(2012, 10, 01));
		r.setIsbn("9783642061349");
		r.setPublisher("Del Rey");
		r.setTitle("How to Solve It: Modern Heuristics");
		r.setLanguage("English");
		
		results[4] = r;
		
		setBooks(results);
	}

	public Book[] getBooks() {
		return books;
	}

	public void setBooks(Book[] books) {
		this.books = books;
	}

	/**
	 * This queries for books in the national database.
	 * 
	 * @param returns dummy results 
	 * @return dummy results
	 */
	public final Book[] searchForBooks(final String query) {
		
		
		return getBooks();
	}
}
