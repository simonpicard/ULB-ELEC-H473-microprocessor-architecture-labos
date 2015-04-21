package softarch.portal.webservice;

import java.util.List;

import org.apache.axis.message.MessageElement;

import softarch.portal.data.Book;


public class MainTest {
	public static void main(String [] args){
		String t = "test";
		LibrarySearchWS ws = new LibrarySearchWS();
		MessageElement[] me = ws.getResult(t);
		List<Book> books = ws.parseME(me);
		for (int i = 0; i < books.size(); i++){
			System.out.println(books.get(i).asXml());
		}
		
	}
}
