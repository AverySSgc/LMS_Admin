/**
 * 
 */
package com.SS.library.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SS.library.DAO.BookDAO;
import com.SS.library.DAO.GenreDAO;
import com.SS.library.Entity.Book;
import com.SS.library.Entity.Genre;
import com.SS.library.Utility.ConnectUtil;

/**
 * @author acorb
 *
 */
@Component
public class GenreService {
	@Autowired
	GenreDAO gDao;
	@Autowired
	BookDAO bDao;
	@Autowired
	ConnectUtil util;
	
	public void addGenre(Genre genre) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		genre.setGenreID(gDao.add(genre,conn));
		conn.commit();
		conn.close();
	}
	
	public List<Genre> readAllGenre() throws SQLException, ClassNotFoundException{
		Connection conn = util.getConnection();
		List<Genre> g = gDao.read(conn);
		conn.close();
		return g;
	}
	
	public Genre readByGenreID(int genreId) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		Genre g = gDao.readById(genreId,conn);
		conn.close();
		return g;
	}
	
	public void updateGenre(Genre genre) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		gDao.update(genre,conn);
		conn.commit();
		conn.close();
	}
	public void deleteGenre(int genreId) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		gDao.delete(genreId,conn);
		conn.commit();
		conn.close();
	}
	public List<Book> readBooksByGenre(int genreId) throws SQLException, ClassNotFoundException{
		Connection conn = util.getConnection();
		List<Book> b = bDao.readBookGenreByBookId(genreId,conn);
		conn.close();
		return b;
	}
	
}
