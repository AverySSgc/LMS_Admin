package com.SS.library.Entity;

import java.io.Serializable;
import java.util.List;

public class Genre implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 794379213453797431L;
	private Integer genreID;
    private String genreName;
    private List<Book> books;
    
    public Integer getGenreID() {
        return genreID;
    }

    public void setGenreID(Integer genreID) {
        this.genreID = genreID;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

	@Override
	public String toString() {
		return "Genre [genreID=" + genreID + ", genreName=" + genreName + ", books=" + books + "]";
	}
    
}
