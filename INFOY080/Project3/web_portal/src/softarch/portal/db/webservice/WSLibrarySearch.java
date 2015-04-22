package softarch.portal.db.webservice;

import librarysearch.soft.BookList;
import librarysearch.soft.LibrarySearchSOAPBindingStub;
import librarysearch.soft.LibrarySearchServiceLocator;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.rpc.holders.StringHolder;

import org.apache.axis.message.MessageElement;

import softarch.portal.data.Book;

public class WSLibrarySearch {

	private String wsLocation;
	
	public WSLibrarySearch(String wsLocation) {
		this.wsLocation = wsLocation;
	}

	public List<Book> getBooks(String query) {
		MessageElement[] me = getResult(query);
		return parseME(me);
	}
	
	private MessageElement[] getResult(String query) {
		try {
			LibrarySearchSOAPBindingStub service = (LibrarySearchSOAPBindingStub) new LibrarySearchServiceLocator()
					.getLibrarySearchServicePort(new URL(wsLocation));
			BookList bookList = service.process(new StringHolder(query));
			MessageElement[] me = bookList.get_any();
			return me;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private List<Book> parseME(MessageElement[] me) {
		List<MessageElement> resultSL = me[0].getChildren();
		List<Book> res = new ArrayList<Book>();
		for (int i = 0; i < resultSL.size(); i++) {
			List<MessageElement> temp = resultSL.get(i).getChildren();
			String author = temp.get(0).getValue();
			int year = Integer.parseInt(temp.get(1).getValue());
			Date date = new Date(year, 1, 1);
			long isbn = Long.parseLong(temp.get(2).getValue());
			String language = temp.get(3).getValue();
			String publisher = temp.get(4).getValue();
			String title = temp.get(5).getValue();
			Book book = new Book(new Date(), author, isbn, 0, date, publisher,
					"", "", title);
			res.add(book);
		}
		for (int i = 1; i < me.length; i++) {
			List<MessageElement> temp = me[i].getChildren();
			String author = temp.get(0).getValue();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			Date date = null;

			try {
				date = df.parse(temp.get(1).getValue());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			long isbn = Long.parseLong(temp.get(2).getValue());
			String language = temp.get(3).getValue();
			String publisher = temp.get(4).getValue();
			String title = temp.get(5).getValue();
			Book book = new Book(new Date(), author, isbn, 0, date, publisher,
					"", "", title);
			res.add(book);
		}
		return res;
	}

}
