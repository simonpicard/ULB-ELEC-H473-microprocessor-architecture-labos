/**
 * SoftLibrarySoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package be.ac.vub.soft;

import java.rmi.RemoteException;

import org.apache.axis.types.Language;

/**
 * This queries for books in the soft database.
 */
public class SoftLibrarySoapBindingImpl implements SoftLibraryPortType{
    public Book[] getAllBooks(Object all) throws RemoteException {
        Book[] result = new Book[4];
        
        Book b = new Book();
		
		b = new Book();
		b.setAuthor("Robert Ludlum");
		b.setIsbn(0553260111);
		b.setLanguage(new Language("en"));
		b.setPublisher("Orion");
		b.setTitle("The Bourne Identity");
		b.setYear(2004);
		
		result[0] = b;
		
		b = new Book();
		b.setAuthor("Douglas Adams");
		b.setIsbn(345453743);
		b.setLanguage(new Language("en"));
		b.setPublisher("Del Rey");
		b.setTitle("The Ultimate Hitchhiker's Guide to the Galaxy");
		b.setYear(2002);
		
		result[1] = b;
		
		b = new Book();
		b.setAuthor("William Sleator");
		b.setIsbn(810993562);
		b.setLanguage(new Language("en"));
		b.setPublisher("Amulet Books");
		b.setTitle("Test");
		b.setYear(2008);
		
		result[2] = b;
		
		b = new Book();
		b.setAuthor("Jordan Belfort");
		b.setIsbn(606259090);
		b.setLanguage(new Language("en"));
		b.setPublisher("Hodder Paperback");
		b.setTitle("The Wolf of Wall Street");
		b.setYear(2008);
		
		result[3] = b;
		
		return result;
    }

    public Book[] searchBooks(String query) throws RemoteException {
        return getAllBooks(null);
    }

}
