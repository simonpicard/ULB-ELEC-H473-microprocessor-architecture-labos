package softarch.portal.webservice;

import java.util.List;

import softarch.portal.data.Book;

public class MainTest {
	public static void main(String [] args){
		String t = "test";
		LibrarySearchWS ws = new LibrarySearchWS();
		List<Book> books = ws.parseME(ws.getResult(t));
		System.out.println("lol");
		for (int i = 0; i < books.size(); i++){
			System.out.println(books.get(i));
		}
		
	}
}
