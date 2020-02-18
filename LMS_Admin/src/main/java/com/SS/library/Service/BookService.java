/**
 * 
 */
package com.SS.library.Service;

import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SS.library.DAO.AuthorDAO;
import com.SS.library.DAO.BookDAO;
import com.SS.library.DAO.GenreDAO;
import com.SS.library.DAO.PublisherDAO;
import com.SS.library.Entity.Author;
import com.SS.library.Entity.Book;
import com.SS.library.Entity.Genre;
import com.SS.library.Entity.Publisher;
import com.SS.library.Utility.ConnectUtil;

/**
 * @author acorb
 *
 */
@Component
public class BookService {

	@Autowired
	BookDAO bDao;
	@Autowired
	GenreDAO gDao;
	@Autowired
	PublisherDAO pDao;
	@Autowired
	AuthorDAO aDao;
	@Autowired
	ConnectUtil util;
	
	public void addBook(Book book) throws SQLException {
		if(book.getGenres().size()!=0 && book.getAuthors().size()!=0 && book.getGenres().size()!=0) {
			book.setBookId(bDao.add(book));
			book.getAuthors().forEach(a->{try {
				bDao.addToBookAuthor(a, book);
			} catch (SQLException e) {
				// TODO Auto-generated catch bloc
			}});
			book.getGenres().forEach(g->{try {
				bDao.addToBookGenres(book, g);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				throw new Exception();
			}});	
		}
		util.getConnection().commit();
	}
	
	public List<Book> readAllBooks() throws SQLException{
		return bDao.read();
	}
	
	public Book readBookById(int bookId) throws SQLException {
		return bDao.readByBookId(bookId);
	}
	
	public void updateBook(Book book) throws SQLException {
		bDao.update(book);
		util.getConnection().commit();
	}
	
	public void deleteBook(Book book) throws SQLException {
		bDao.delete(book);
		util.getConnection().commit();
	}
	
	public List<Genre> readGenresByBook(Book book) throws SQLException{
		return gDao.readBookGenre(book.getBookId());
	}
	public void addToBookGenre(Book book, Genre genre) throws SQLException {
		bDao.addToBookGenres(book, genre);
		util.getConnection().commit();
	}
	public void deleteFromBookGenre(Book book, Genre genre) throws SQLException {
		bDao.deleteFromGenreBook(genre.getGenreID(), book.getBookId());
		util.getConnection().commit();
	}
	
	public Publisher readPublisherByBook (int bookId) throws SQLException{
		return pDao.readByID(bDao.readByBookId(bookId).getPublisher().getPublisherId());
	}
	
	public List<Author> readAuthorsByBook (int bookId) throws SQLException {
		return aDao.readBookAuthorByBookId(bookId);
	}
	public void addToBookAuthor(Book book, Author author) throws SQLException {
		bDao.addToBookAuthor(author, book);
		util.getConnection().commit();
	}
	public void deleteFomAuthorBook(Book book, Author author) throws SQLException {
		bDao.deleteFromBookAuthor(author.getAuthorID(), book.getBookId());
		util.getConnection().commit();
	}
	
	
	
	
}
