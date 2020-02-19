package com.SS.library.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SS.library.DAO.BookDAO;
import com.SS.library.DAO.PublisherDAO;
import com.SS.library.Entity.Book;
import com.SS.library.Entity.Publisher;
import com.SS.library.Utility.ConnectUtil;

@Component
public class PublisherService {
	@Autowired
	PublisherDAO pDao;
	@Autowired 
	BookDAO bDao;
	@Autowired
	ConnectUtil util;
	
	public void addPublisher(Publisher publisher) throws SQLException, ClassNotFoundException {
		Connection conn = util.getConnection();
		publisher.setPublisherId(pDao.add(publisher,conn ));
		conn.commit();
		conn.close();
		
	}
	public List<Publisher> readAllPublisher() throws SQLException, ClassNotFoundException{
		Connection conn = util.getConnection();
		List<Publisher> p = pDao.read(conn);
		conn.close();
		return p;
	}
	public Publisher readPublisherById(int publisher)throws SQLException, ClassNotFoundException{
		Connection conn = util.getConnection();
		Publisher p = pDao.readByID(publisher,conn );
		conn.close();
		return p;
	}
	public void updatePublisher(Publisher publisher)throws SQLException, ClassNotFoundException{
		Connection conn = util.getConnection();
		pDao.update(publisher,conn);
		conn.commit();
		conn.close();
	}
	public void deletePublisher(Publisher publisher)throws SQLException, ClassNotFoundException{
		Connection conn = util.getConnection();
		pDao.delete(publisher,conn );
		conn.commit();
		conn.close();
	}
	public List<Book> readBookByPublisher(int publisherId)throws SQLException, ClassNotFoundException{
		Connection conn = util.getConnection();
		List<Book> b =bDao.readByPubId(publisherId,conn);
		conn.close();
		return b;
	}
}
