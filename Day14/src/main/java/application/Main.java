package application;

import java.util.Arrays;
import java.util.HashSet;

import entities.Author;
import entities.Book;
import repository.EntityRepository;
import util.EntityManagerUtil;


public class Main {
	public static void main(String[] args) {
		EntityRepository<Author> emuAuthor = new EntityRepository<>(Author.class, "authors");
		EntityRepository<Book> emuBook = new EntityRepository<>(Book.class, "book");
		
		Author author1 = new Author("Author 1");
		Author author2 = new Author("Author 2");
		
		Book book1 = new Book("Book 1", 20);
		Book book2 = new Book("Book 2", 19.99);
		
		author1.setBooks(new HashSet<Book>(Arrays.asList(book1, book2)));
		author2.setBooks(new HashSet<Book>(Arrays.asList(book1, book2)));
		book1.setAuthors(new HashSet<Author>(Arrays.asList(author1, author2)));
		book2.setAuthors(new HashSet<Author>(Arrays.asList(author1, author2)));	
		
		emuAuthor.create(author1);
		emuAuthor.create(author2);
		emuBook.create(book1);
		emuBook.create(book2);
		
		EntityManagerUtil.close();
	}
}
