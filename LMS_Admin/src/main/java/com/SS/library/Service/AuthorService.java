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
import com.SS.library.Entity.Author;
import com.SS.library.Entity.Book;
import com.SS.library.Utility.ConnectUtil;

/**
 * @author acorb
 *
 */
@Component
public class AuthorService {
	@Autowired
	AuthorDAO aDao;
	@Autowired
	BookDAO bDao;
	@Autowired
	ConnectUtil util;
	
	public void addAuthor(Author author) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		author.setAuthorID(aDao.add(author,conn));
		conn.commit();
		conn.close();
		
	}
	
	public void addBookToAuthor(int authorId, int bookId) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		aDao.addToBookAuthor(authorId, bookId,conn);
		conn.commit();
		conn.close();
	}
	
	public List<Author> readAllAuthors() throws SQLException, ClassNotFoundException{
		Connection conn = util.getConnection();
		List<Author> a = aDao.read(conn);
		conn.close();
		return a;
	}
	
	public Author readAuthorAtId(int authorId) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		Author a =aDao.readByID(authorId,conn);
		conn.close();
		return a;
	}
	
	public void updateAuthor(Author author) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		aDao.update(author,conn);
		conn.commit();
		conn.close();
	}
	
	public void deleteAuthor(int authorId) throws SQLException, ClassNotFoundException{
		Connection conn = util.getConnection();
		aDao.delete(authorId,conn);
		conn.commit();
		conn.close();
	}
	
	public void deleteBookFromAuthor(int author, int book) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		aDao.deleteBookAuthor(author, book,conn);
		conn.commit();
		conn.close();
	}
	
	public List<Book> readBookAuthorByAuthor(int authorId) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		List<Book> b =bDao.readBookAuthorByAuthorId(authorId,conn);
		conn.close();
		return b;
	}

}
