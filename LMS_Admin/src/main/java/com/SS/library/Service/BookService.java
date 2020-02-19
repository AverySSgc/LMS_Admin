/**
 * 
 */
package com.SS.library.Service;

import java.sql.Connection;
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
	
	public void addBook(Book book) throws SQLException, ClassNotFoundException {
		if(book.getGenres().size()!=0 && book.getAuthors().size()!=0 && book.getGenres().size()!=0) {
			Connection conn = util.getConnection();
			try {
				book.setBookId(bDao.add(book, conn));
				for(Author a:book.getAuthors()) {
					bDao.addToBookAuthor(a, book, conn);
				}
	//			book.getAuthors().forEach(a->{try {
	//				bDao.addToBookAuthor(a, book);
	//			} catch (SQLException e) {
	//				// TODO Auto-generated catch bloc
	//			}});
				for(Genre g : book.getGenres()) {
					bDao.addToBookGenres(book, g, conn);
				}
	//			book.getGenres().forEach(g->{try {
	//				bDao.addToBookGenres(book, g);
	//			} catch (SQLException e) {
	//				// TODO Auto-generated catch block
	////				throw new Exception();
	//			}});
				conn.commit();
				conn.close();
			}catch(SQLException e) {
				conn.rollback();
				conn.close();
				throw e;
			}
		}
	
	}
	
	public List<Book> readAllBooks() throws SQLException, ClassNotFoundException{
		Connection conn = util.getConnection();
		List<Book> b = bDao.read(conn);
		conn.close();
		return b;
	}
	
	public Book readBookById(int bookId) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		Book b =bDao.readByBookId(bookId,conn);
		conn.close();
		return b;
	}
	
	public void updateBook(Book book) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		bDao.update(book, conn);
		conn.commit();
		conn.close();
	}
	
	public void deleteBook(Book book) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		bDao.delete(book,conn);
		conn.commit();
		conn.close();
	}
	
	public List<Genre> readGenresByBook(Book book) throws SQLException, ClassNotFoundException{
		Connection conn = util.getConnection();
		List<Genre> g = gDao.readBookGenre(book.getBookId(),conn);
		conn.close();
		return g;
	}
	public void addToBookGenre(Book book, Genre genre) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		bDao.addToBookGenres(book, genre,conn);
		conn.commit();
		conn.close();
	}
	public void deleteFromBookGenre(Book book, Genre genre) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		bDao.deleteFromGenreBook(genre.getGenreID(), book.getBookId(),conn);
		conn.commit();
		conn.close();
	}
	
	public Publisher readPublisherByBook (int bookId) throws SQLException, ClassNotFoundException{
		Connection conn = util.getConnection();
		Publisher p =pDao.readByID(bDao.readByBookId(bookId,conn).getPublisher().getPublisherId(),conn);
		conn.close();
		return p;
	}
	
	public List<Author> readAuthorsByBook (int bookId) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		List <Author> a =aDao.readBookAuthorByBookId(bookId,conn); 
		conn.close();
		return a;
	}
	public void addToBookAuthor(Book book, Author author) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		bDao.addToBookAuthor(author, book,conn);
		conn.commit();
		conn.close();
	}
	public void deleteFomAuthorBook(Book book, Author author) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		bDao.deleteFromBookAuthor(author.getAuthorID(), book.getBookId(),conn);
		conn.commit();
		conn.close();
	}
	
	
	
	
}
