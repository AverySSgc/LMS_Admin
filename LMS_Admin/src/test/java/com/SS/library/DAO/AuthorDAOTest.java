/**
 * 
 */
package com.SS.library.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.SS.library.Entity.Author;
import com.SS.library.Utility.ConnectUtil;

/**
 * @author acorb
 *
 */
@ExtendWith(MockitoExtension.class)
class AuthorDAOTest {
//	
//	@InjectMocks
//	ConnectUtil connectUtil;
//	@Mock
//	private AuthorDAO authorDAO;
////
////
//	@Test
//	void testRead() {
//		
//		List<Author> authors;
//		try {
//			authors = authorDAO.read();
//			assertEquals(true, authors.size()>1);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			fail();
//		}
//	}
////	@Test
////	void testAddDelete() {
////		Author newAuthor = new Author();
////		newAuthor.setAuthorName("Test Author");
////		try {
////			newAuthor.setAuthorID(authorDAO.add(newAuthor));
////			assertEquals(true, newAuthor.getAuthorID()>5);
////			authorDAO.delete(newAuthor);
////		}catch(Exception e) {
////			fail();
////		}
////	}
////	
////	@Test
////	void testReadById() {
////		try {
////			Author test = authorDAO.readByID(1);
////			assertEquals(1, test.getAuthorID());
////			assertEquals(true, test.getAuthorName().length()>3);
////		} catch (SQLException e) {
////			// TODO Auto-generated catch block
////			fail();
////		}
////	}
////	
////	@Test
////	void testReadBookAuthorByBookId() {
////		try {
////			List<Author> authors = authorDAO.readBookAuthorByBookId(3);
////			assertEquals(true, authors.size()>=1);
////		}catch(Exception e) {
////			fail();
////		}
////	}
//	
}
