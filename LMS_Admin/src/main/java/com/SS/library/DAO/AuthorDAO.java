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

@Component
public class AuthorDAO extends DAO <Author> {
	@Autowired
    BookDAO bdao;

    @Override
    protected List<Author> extractData(ResultSet rs,Connection conn) throws SQLException {
        List<Author> a = new ArrayList<>();
        while(rs.next()){
            Author author= new Author();
            author.setAuthorID(rs.getInt("authorId"));
            author.setAuthorName(rs.getString("authorName"));
            //set book list
            author.setBooks(bdao.readBookAuthorByAuthorId(author.getAuthorID(),conn));
            a.add(author);
            }
        return a;
    }

    @Override
    protected List<Author> extractEssentialData(ResultSet rs, Connection conn) throws SQLException {
        List<Author> a = new ArrayList<>();
        while(rs.next()) {
            Author author = new Author();
            author.setAuthorID(rs.getInt("authorId"));
            author.setAuthorName(rs.getString("authorName"));
            a.add(author);
        }
        return a;
    }

    @Override
    public Integer add(Author object,Connection conn) throws SQLException {
        return saveRecieveKey("Insert into tbl_author (authorName) value (?)", new Object[]{object.getAuthorName()},conn);
    }

    @Override
    public void update(Author object,Connection conn) throws SQLException {
        save("update tbl_author set authorName = ? where authorId = ?", new Object[]{object.getAuthorName(),object.getAuthorID()},conn);
    }

    @Override
    public void delete(Author object,Connection conn) throws SQLException {
        save("delete from tbl_author where authorID = ?", new Object[]{object.getAuthorID()},conn);
    }

    @Override
    public List<Author> read(Connection conn) throws SQLException {
        return read("select * from tbl_author", null,conn);
    }

    public void addToBookAuthor(Author author, Book book,Connection conn) throws SQLException {
        save("Insert into tbl_book_authors (bookId, authorId) values (?,?)", new Object[]{book.getBookId(),author.getAuthorID()},conn);
    }
    
    public void deleteBookAuthor(Author author, Book book,Connection conn) throws SQLException {
    	save("delete from tbl_book_authors where authorId = ? and bookId = ?", new Object[] {author.getAuthorID(),book.getBookId()},conn);
    }

    public Author readByID(int id,Connection conn) throws SQLException{
        List<Author> newAuthor = read("select * from tbl_author where authorId = ?",new Object[]{id},conn);
        return newAuthor.get(0);
    }
 
    public List<Author> readBookAuthorByBookId(int bookId,Connection conn) throws SQLException {
        return readEssential("select ta.authorName as authorName, ta.authorId as authorId from tbl_book_authors as tba " +
                            "join tbl_author as ta on tba.authorId = ta.authorId " +
                            "where bookId = ?",new Object[]{bookId},conn);
    }

}
