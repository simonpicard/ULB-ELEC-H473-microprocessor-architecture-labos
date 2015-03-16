package softarch.portal.data.test;

import java.util.Date;
import java.net.URL;
import java.net.MalformedURLException;

import softarch.portal.data.Article;
import softarch.portal.data.Book;
import softarch.portal.data.SoftwareRepository;

/**
 * This is a test program for the RegularData class and its subclasses.
 * @author Niels Joncheere
 */
public class TestRegularData {
	public static void main(String[] args) {
		Book book
			= new Book(
				new Date(),
				"Niels Joncheere",
				9876543210L,
				150,
				new Date(),
				"Addison-Wesley",
				"This book is excellent!",
				"This book provides an overview of how " +
					"technologies like XML can be used " +
					"to build the Semantic Web.",
				"Building the Semantic Web");
		System.out.println(book.asXml());
		System.out.println();
		System.out.println(book.asSql());
		System.out.println();

		Article article
			= new Article(
				new Date(),
				"Niels Joncheere",
				new Date(),
				"This is an interesting article.",
				"This article explains which modifications " +
					"have been made to the Semantic Web " +
					"Portal's design.",
				"Design Changes");
		System.out.println(article.asXml());
		System.out.println();
		System.out.println(article.asSql());
		System.out.println();

		try {
			SoftwareRepository softwareRepository
				= new SoftwareRepository(
					new Date(),
					"Niels Joncheere",
					"Niels' Software Repository",
					new URL("http://www.joncheere.be/"));
			System.out.println(softwareRepository.asXml());
			System.out.println();
			System.out.println(softwareRepository.asSql());
		}
		catch (MalformedURLException e) {
			System.out.println(
				"Malformed URL Exception: " + e.getMessage());
		}
	}
}
