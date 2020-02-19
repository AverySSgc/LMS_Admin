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

import com.SS.library.Entity.Publisher;

/**
 * @author acorb
 *
 */
@Component
public class PublisherDAO extends DAO<Publisher> {
	@Autowired
	BookDAO bdao;
	   
	    @Override
	    protected List<Publisher> extractData(ResultSet rs, Connection conn) throws SQLException {
	        List<Publisher> p = new ArrayList<>();
	        while(rs.next()){
	            Publisher publisher = new Publisher();
	            publisher.setPublisherId(rs.getInt("publisherId"));
	            publisher.setPublisherName(rs.getString("publisherName"));
	            publisher.setPublisherAddress(rs.getString("publisherAddress"));
	            publisher.setPublisherPhone(rs.getString("publisherPhone"));
//	            BookDAO bdao = new BookDAO(conn);
	            publisher.setBooks(bdao.readByPubId(publisher.getPublisherId(),conn));
	            p.add(publisher);
	        }
	        return p;
	    }

	    @Override
	    protected List<Publisher> extractEssentialData(ResultSet rs, Connection conn) throws SQLException {
	        List<Publisher> p = new ArrayList<>();
	        while(rs.next()){
	            Publisher publisher = new Publisher();
	            publisher.setPublisherId(rs.getInt("publisherId"));
	            publisher.setPublisherName(rs.getString("publisherName"));
	            publisher.setPublisherAddress(rs.getString("publisherAddress"));
	            publisher.setPublisherPhone(rs.getString("publisherPhone"));
	            p.add(publisher);
	        }
	        return p;
	    }

	    @Override
	    public Integer add(Publisher object, Connection conn) throws SQLException {
	        return saveRecieveKey("insert into tbl_publisher (publisherName,publisherAddress,publisherPhone)" +
	                    "values (?,?,?)", new Object[]{object.getPublisherName(),object.getPublisherAddress(),object.getPublisherPhone()},conn);
	    }

	    @Override
	    public void update(Publisher object, Connection conn) throws SQLException {
	        save("update tbl_publisher set publisherName = ? , publisherAddress = ? , publisherPhone = ? where publisherId = ?",
	                new Object[]{object.getPublisherName(),object.getPublisherAddress(),object.getPublisherPhone(),object.getPublisherId()},conn);
	    }

	    @Override
	    public void delete(Publisher object, Connection conn) throws SQLException {
	        save("delete from tbl_publisher where publisherId = ?",new Object[] {object.getPublisherId()},conn);
	    }

	    @Override
	    public List<Publisher> read(Connection conn) throws SQLException {
	        return read("select * from tbl_publisher",null,conn);
	    }

	    public Publisher readByID(int id, Connection conn) throws SQLException {
	        List<Publisher> newPublisher =  read("select * from tbl_publisher where publisherId = ?",new Object[]{id},conn);
	        return newPublisher.get(0);
	    }

	    public Publisher readByIdEssentialData(int id, Connection conn) throws SQLException {
	        List<Publisher> newPublisher =  readEssential("select * from tbl_publisher where publisherId = ?",new Object[]{id},conn);
	        return newPublisher.get(0);
	    }

}
