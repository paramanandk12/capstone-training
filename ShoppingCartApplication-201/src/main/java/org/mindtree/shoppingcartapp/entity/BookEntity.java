package org.mindtree.shoppingcartapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
/**
 * @author M1048697
 *
 */
@Entity
public class BookEntity extends ProductEnity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Column
	private String bookGener;
	
	@Column
	private String bookAuthor;
	
	@Column
	private String bookPublication;
	
	public BookEntity() {
		super();
	}

	
	public String getBookGener() {
		return bookGener;
	}

	public void setBookGener(String bookGener) {
		this.bookGener = bookGener;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPublication() {
		return bookPublication;
	}

	public void setBookPublication(String bookPublication) {
		this.bookPublication = bookPublication;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bookAuthor == null) ? 0 : bookAuthor.hashCode());
		result = prime * result + ((bookGener == null) ? 0 : bookGener.hashCode());
		result = prime * result + ((bookPublication == null) ? 0 : bookPublication.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookEntity other = (BookEntity) obj;
		if (bookAuthor == null) {
			if (other.bookAuthor != null)
				return false;
		} else if (!bookAuthor.equals(other.bookAuthor))
			return false;
		if (bookGener == null) {
			if (other.bookGener != null)
				return false;
		} else if (!bookGener.equals(other.bookGener))
			return false;
		if (bookPublication == null) {
			if (other.bookPublication != null)
				return false;
		} else if (!bookPublication.equals(other.bookPublication))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "BookEntity [bookGener=" + bookGener + ", bookAuthor=" + bookAuthor + ", bookPublication="
				+ bookPublication + "]";
	}

	
	
	

}
