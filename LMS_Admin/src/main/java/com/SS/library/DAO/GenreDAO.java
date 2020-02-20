package com.SS.library.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SS.library.Entity.Genre;

@Component
public class GenreDAO extends DAO<Genre> {
	@Autowired
	BookDAO bdao;
	
    @Override
    protected List<Genre> extractData(ResultSet rs, Connection conn) throws SQLException {
        List<Genre> genres = new ArrayList<>();
        while(rs.next()){
            Genre g = new Genre();
            g.setGenreID(rs.getInt("genre_id"));
            g.setGenreName(rs.getString("genre_name"));
            //get book list
//            BookDAO bdao = new BookDAO(conn);
            g.setBooks(bdao.readBookGenreByBookId(g.getGenreID(),conn));
            genres.add(g);
        }
        return genres;
    }

    @Override
    protected List<Genre> extractEssentialData(ResultSet rs, Connection conn) throws SQLException {
        List<Genre> genres = new ArrayList<>();
        while(rs.next()){
            Genre g = new Genre();
            g.setGenreID(rs.getInt("genre_id"));
            g.setGenreName(rs.getString("genre_name"));
            genres.add(g);
        }
        return genres;
    }

    @Override
    public Integer add(Genre object, Connection conn) throws SQLException {
        return saveRecieveKey("insert into tbl_genre (genre_name) value (?)",
                new Object[]{object.getGenreName()},conn);
    }

    @Override
    public void update(Genre object, Connection conn) throws SQLException {
        save("update tbl_genre set genre_name = ? where genre_id = ?",
                new Object[]{object.getGenreName(),object.getGenreID()},conn);
    }

    @Override
    public void delete(int id, Connection conn) throws SQLException {
        save("delete from tbl_genre where genre_id = ?", new Object[]{id},conn);
    }

    @Override
    public List<Genre> read(Connection conn) throws SQLException {
        return read("select * from tbl_genre", null,conn);
    }
    
    public Genre readById(int genreId, Connection conn) throws SQLException{
    	List<Genre> genre = read("select * from tbl_genre where genre_id = ?", new Object[] {genreId},conn);
    	return genre.get(0);
    }

    public List<Genre> readBookGenre(int bookId, Connection conn) throws SQLException {
        return readEssential("select genre_name, tg.genre_id as genre_id from tbl_genre as tg " +
                "join tbl_book_genres as tbg on tg.genre_id = tbg.genre_id " +
                "where bookId= ?",new Object[]{bookId},conn);
    }


}
