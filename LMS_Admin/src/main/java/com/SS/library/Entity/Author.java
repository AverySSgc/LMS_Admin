package com.SS.library.Entity;

import java.io.Serializable;
import java.util.List;

public class Author implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5069100490580210474L;
	private Integer authorID;
    private String authorName;
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
	public String toString() {
		return "Author [authorID=" + authorID + ", authorName=" + authorName + ", books=" + books + "]";
	}

	public Integer getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Integer authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

}
