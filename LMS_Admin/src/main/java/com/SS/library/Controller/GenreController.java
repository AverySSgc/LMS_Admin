package com.SS.library.Controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SS.library.Entity.Genre;
import com.SS.library.Entity.Book;
import com.SS.library.Service.GenreService;

@RestController
public class GenreController {
	
	@Autowired
	GenreService genreService;
	
	@RequestMapping(path = "/Admin/genres", method = RequestMethod.GET)
	public ResponseEntity<List<Genre>> getgenres(){
		try {
			List <Genre> genres = genreService.readAllGenre();
			return new ResponseEntity<List<Genre>>(genres,HttpStatus.OK);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<List<Genre>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(path = "/Admin/genres", method = {RequestMethod.DELETE, RequestMethod.PUT})
	public ResponseEntity<String> genreMethodNotAllowed() {
		return new ResponseEntity<String>(HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@RequestMapping(path = "/Admin/genres/{genreId}", method = RequestMethod.GET)
	public ResponseEntity<Genre> getgenre(@PathVariable int genreId){
		try {
			Genre genre = genreService.readByGenreID(genreId);
			return new ResponseEntity<Genre>(genre,HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Genre>(HttpStatus.NO_CONTENT);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Genre>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(path = {"/Admin/genres"} , method = RequestMethod.POST)
	public ResponseEntity<Genre> addgenre(@RequestBody Genre genre){
		try {
			genreService.addGenre(genre);
			return new ResponseEntity<Genre>(genre,HttpStatus.ACCEPTED);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Genre>(HttpStatus.CONFLICT);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Genre>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(path = "/Admin/genres/{genreId}", method = RequestMethod.PUT)
	public ResponseEntity<Genre> updategenre(@RequestBody Genre genre){
		try {
			genreService.updateGenre(genre);
			return new ResponseEntity<Genre>(genre,HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Genre>(HttpStatus.NO_CONTENT);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Genre>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(path = "/Admin/genres/{genreId}", method = RequestMethod.DELETE)
	public ResponseEntity<Genre> deletegenre(@RequestBody Genre genre){
		try {
			genreService.deleteGenre(genre);
			return new ResponseEntity<Genre>(genre,HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Genre>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(path="/Admin/genres/{genreId}/books")
	public ResponseEntity<List<Book>>readBooksBygenre(@RequestBody Genre genre){
		try {
			List <Book> books = genreService.readBooksByGenre(genre);
			return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
		}catch(SQLException e) {
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
