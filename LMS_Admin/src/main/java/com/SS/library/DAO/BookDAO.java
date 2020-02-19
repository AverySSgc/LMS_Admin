/**
 * 
 */
package com.SS.library.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SS.library.Entity.Author;
import com.SS.library.Entity.Book;
import com.SS.library.Entity.Genre;

/**
 * @author acorb
 *
 */
@Component
public class BookDAO extends DAO<Book> {
	@Autowired
	PublisherDAO pdao;
	@Autowired
	AuthorDAO adao;
	@Autowired
	GenreDAO gdao;
	
    @Override
    protected List<Book> extractData(ResultSet rs,Connection conn) throws SQLException {
        List<Book> books = new ArrayList<>();
        while(rs.next()){
            Book b = new Book();
            b.setBookId(rs.getInt("bookId"));
            b.setTitle(rs.getString("title"));
            //set publisher
//            PublisherDAO pdao = new PublisherDAO(conn);
            try {
            	b.setPublisher(pdao.readByIdEssentialData(rs.getInt("pubId"),conn));
            }catch(SQLException e) {}
////            //set author list
//            AuthorDAO adao = new AuthorDAO(conn);
            b.setAuthors(adao.readBookAuthorByBookId(b.getBookId(),conn));
//            //set genre list
//            GenreDAO gdao = new GenreDAO(conn);
            b.setGenres(gdao.readBookGenre(b.getBookId(),conn));
            books.add(b);
        }
        return books;
    }

    @Override
    protected List<Book> extractEssentialData(ResultSet rs, Connection conn) throws SQLException {
        List<Book> books = new ArrayList<>();
        while(rs.next()){
            Book b = new Book();
            b.setBookId(rs.getInt("bookId"));
            b.setTitle(rs.getString("title"));
            //set publisher
//            PublisherDAO pdao = new PublisherDAO(conn);
            try {
            b.setPublisher(pdao.readByIdEssentialData(rs.getInt("pubID"),conn));
            }catch(SQLException e) {}
//          //set genre list
//          GenreDAO gdao = new GenreDAO(conn);
            b.setGenres(gdao.readBookGenre(b.getBookId(),conn));
            books.add(b);
        }
        return books;
    }

    @Override
    public Integer add(Book object,Connection conn) throws SQLException {
        return saveRecieveKey("insert into tbl_book (bookId, title, pubId) values (?,?,?)",
                 new Object[]{object.getBookId(),object.getTitle(),object.getPublisher().getPublisherId()},conn);
    }

    @Override
    public void update(Book object,Connection conn) throws SQLException {
        save("update tbl_book set title = ? pubId = ? where bookId=?",
                new Object[]{object.getTitle(),object.getPublisher().getPublisherId(),object.getBookId()},conn);
    }

    @Override
    public void delete(Book object,Connection conn) throws SQLException {
        save("delete from tbl_book where bookId = ?", new Object[]{object.getBookId()},conn);
    }

    @Override
    public List<Book> read(Connection conn) throws SQLException {
        return read("select * from tbl_book", null,conn);
    }

    public Book readByBookId(int id,Connection conn) throws SQLException {
        List<Book> books = read("select * from tbl_book where bookId= ?", new Object[]{id},conn);
        return  books.get(0);
    }

    public List<Book> readByPubId(int id,Connection conn) throws SQLException {
        return readEssential("select * from tbl_book where pubId= ?", new Object[]{id},conn);
    }

    public void addToBookAuthor(Author author, Book book,Connection conn) throws SQLException {
        save("insert into tbl_book_authors (bookId, AuthorId) values (?,?)", new Object[]{book.getBookId(),author.getAuthorID()},conn);
    }
    public void deleteFromBookAuthor(int authorId, int bookId,Connection conn) throws SQLException {
    	save("delete from tbl_book_authors where bookId = ? and authorId = ?", new Object[] {bookId , authorId},conn);
    }

    public List<Book> readBookAuthorByAuthorId(int authorId,Connection conn) throws SQLException {
        return readEssential("select tb.bookId as bookId, title, pubId from tbl_book_authors as tba " +
                            "join tbl_book as tb on tba.bookId = tb.bookId " +
                            "where authorId = ?", new Object[]{authorId},conn);
    }

    public List<Book> readBookGenreByBookId(int genreId,Connection conn) throws SQLException {
        return readEssential("select  tb.bookId as bookId, tb.title, tb.pubId  from tbl_book as tb " +
                "join tbl_book_genres as tbg on tb.bookId = tbg.bookId " +
                "where genre_id = ?", new Object[]{genreId},conn);
    }
    public void deleteFromGenreBook(int genreId, int bookId,Connection conn) throws SQLException{
    	save("delete from tbl_book_genres where genre_id =? and bookId = ?", new Object[] {genreId,bookId},conn);
    }
    
    public void addToBookGenres(Book book, Genre genre,Connection conn) throws SQLException {
    	save("insert into tbl_book_genres (genre_id, bookId) values (?,?)",new Object[] {genre.getGenreID(),book.getBookId()},conn);
    }

}
