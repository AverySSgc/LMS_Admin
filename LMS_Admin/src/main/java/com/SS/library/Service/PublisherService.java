package com.SS.library.Service;

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
	
	public void addPublisher(Publisher publisher) throws SQLException {
		publisher.setPublisherId(pDao.add(publisher));
		util.getConnection().commit();
		
	}
	public List<Publisher> readAllPublisher() throws SQLException{
		return pDao.read();
	}
	public Publisher readPublisherById(int publisher)throws SQLException{
		return pDao.readByID(publisher);
	}
	public void updatePublisher(Publisher publisher)throws SQLException{
		pDao.update(publisher);
		util.getConnection().commit();
	}
	public void deletePublisher(Publisher publisher)throws SQLException{
		pDao.delete(publisher);
		util.getConnection().commit();
	}
	public List<Book> readBookByPublisher(int publisherId)throws SQLException{
		return bDao.readByPubId(publisherId);
	}
}
