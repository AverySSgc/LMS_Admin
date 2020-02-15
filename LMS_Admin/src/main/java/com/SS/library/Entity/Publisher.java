/**
 * 
 */
package com.SS.library.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author acorb
 *
 */
public class Publisher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2495782306085481780L;
	private Integer publisherId;
    private String publisherPhone;
    private String publisherName;
    private String publisherAddress;
    private List<Book> books;
    
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherPhone() {
        return publisherPhone;
    }

    public void setPublisherPhone(String publisherPhone) {
        this.publisherPhone = publisherPhone;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherAddress() {
        return publisherAddress;
    }

    public void setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
    }

	@Override
	public String toString() {
		return "Publisher [publisherId=" + publisherId + ", publisherPhone=" + publisherPhone + ", publisherName="
				+ publisherName + ", publisherAddress=" + publisherAddress + ", books=" + books + "]";
	}
    
}
