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
	
	public void addAuthor(Author author) throws SQLException {
		author.setAuthorID(aDao.add(author));
		util.getConnection().commit();
		
	}
	
	public void addBookToAuthor(Author author, Book book) throws SQLException {
		aDao.addToBookAuthor(author, book);
		util.getConnection().commit();
	}
	
	public List<Author> readAllAuthors() throws SQLException{
		return aDao.read();
	}
	
	public Author readAuthorAtId(int authorId) throws SQLException {
		return aDao.readByID(authorId);
	}
	
	public void updateAuthor(Author author) throws SQLException {
		aDao.update(author);
		util.getConnection().commit();
	}
	
	public void deleteAuthor(Author author) throws SQLException{
		aDao.delete(author);
		util.getConnection().commit();
	}
	
	public void deleteBookFromAuthor(Author author, Book book) throws SQLException {
		aDao.deleteBookAuthor(author, book);
		util.getConnection().commit();
	}
	
	public List<Book> readBookAuthorByAuthor(Author author) throws SQLException {
		return bDao.readBookAuthorByAuthorId(author.getAuthorID());
	}

}
