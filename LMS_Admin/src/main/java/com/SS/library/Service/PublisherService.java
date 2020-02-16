package com.SS.library.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SS.library.DAO.BookDAO;
import com.SS.library.DAO.PublisherDAO;
import com.SS.library.Entity.Book;
import com.SS.library.Entity.Publisher;

@Component
public class PublisherService {
	@Autowired
	PublisherDAO pDao;
	@Autowired 
	BookDAO bDao;
	
	public void addPublisher(Publisher publisher) throws SQLException {
		publisher.setPublisherId(pDao.add(publisher));
	}
	public List<Publisher> readAllPublisher() throws SQLException{
		return pDao.read();
	}
	public Publisher readPublisherById(int publisher)throws SQLException{
		return pDao.readByID(publisher);
	}
	public void updatePublisher(Publisher publisher)throws SQLException{
		pDao.update(publisher);
	}
	public void deletePublisehr(Publisher publisher)throws SQLException{
		pDao.delete(publisher);
	}
	public List<Book> readBookByPublisher(Publisher publisher)throws SQLException{
		return bDao.readByPubId(publisher.getPublisherId());
	}
}
