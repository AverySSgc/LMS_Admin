/**
 * 
 */
package com.SS.library.Service;

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
	
	public void addGenre(Genre genre) throws SQLException {
		genre.setGenreID(gDao.add(genre));
		util.getConnection().commit();
	}
	
	public List<Genre> readAllGenre() throws SQLException{
		return gDao.read();
	}
	
	public Genre readByGenreID(int genreId) throws SQLException {
		return gDao.readById(genreId);
	}
	
	public void updateGenre(Genre genre) throws SQLException {
		gDao.update(genre);
		util.getConnection().commit();
	}
	public void deleteGenre(Genre genre) throws SQLException {
		gDao.delete(genre);
		util.getConnection().commit();
	}
	public List<Book> readBooksByGenre(Genre genre) throws SQLException{
		return bDao.readBookGenreByBookId(genre.getGenreID());
	}
	
}
