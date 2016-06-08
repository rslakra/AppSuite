package com.rslakra.bos.library;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.rslakra.bos.BusinessObject;

/**
 * The <code>Book</code> class defines the book structure.
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @date Aug 2, 2010 8:18:16 PM
 */
public class Book extends BusinessObject implements Serializable {
	
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	/** ISBN */
	private String isbn;
	/** Title */
	private String title;
	/** Description */
	private String description;
	/** Author */
	private String author;
	/** Language */
	private String language;
	/** PublishedOn */
	private Date publishedOn;
	/** Publisher */
	private String publisher;
	/** PublishingRights */
	private String publishingRights;
	/** Category */
	private String category;
	/** SearchKeywords */
	private String searchKeywords;
	/** TerritoryRights */
	private String territoryRights;
	/** Price */
	private String price;
	/** CurrencyType */
	private String currencyType;
	
	public Book() {
	}
	
	/**
	 * Returns the isbn.
	 *
	 * @return
	 */
	public String getIsbn() {
		return isbn;
	}
	
	/**
	 * The isbn to be set.
	 * 
	 * @param isbn
	 */
	public void setIsbn(String isbn) {
		if(this.isbn != isbn) {
			String oldValue = this.isbn;
			this.isbn = isbn;
			firePropertyChange("isbn", oldValue, isbn);
		}
	}
	
	/**
	 * Returns the title.
	 *
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * The title to be set.
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		if(this.title != title) {
			String oldValue = this.title;
			this.title = title;
			firePropertyChange("title", oldValue, title);
		}
	}
	
	/**
	 * Returns the description.
	 *
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * The description to be set.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		if(this.description != description) {
			String oldValue = this.description;
			this.description = description;
			firePropertyChange("description", oldValue, description);
		}
	}
	
	/**
	 * Returns the author.
	 *
	 * @return
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * The author to be set.
	 * 
	 * @param author
	 */
	public void setAuthor(String author) {
		if(this.author != author) {
			String oldValue = this.author;
			this.author = author;
			firePropertyChange("author", oldValue, author);
		}
	}
	
	/**
	 * Returns the language.
	 *
	 * @return
	 */
	public String getLanguage() {
		return language;
	}
	
	/**
	 * The language to be set.
	 * 
	 * @param language
	 */
	public void setLanguage(String language) {
		if(this.language != language) {
			String oldValue = this.language;
			this.language = language;
			firePropertyChange("language", oldValue, language);
		}
	}
	
	/**
	 * Returns the publishedOn.
	 *
	 * @return
	 */
	public Date getPublishedOn() {
		return publishedOn;
	}
	
	/**
	 * The publishedOn to be set.
	 * 
	 * @param publishedOn
	 */
	public void setPublishedOn(Date publishedOn) {
		if(this.publishedOn != publishedOn) {
			Date oldValue = this.publishedOn;
			this.publishedOn = publishedOn;
			firePropertyChange("publishedOn", oldValue, publishedOn);
		}
	}
	
	/**
	 * Returns the publisher.
	 *
	 * @return
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 * The publisher to be set.
	 * 
	 * @param publisher
	 */
	public void setPublisher(String publisher) {
		if(this.publisher != publisher) {
			String oldValue = this.publisher;
			this.publisher = publisher;
			firePropertyChange("publisher", oldValue, publisher);
		}
	}
	
	/**
	 * Returns the publishingRights.
	 *
	 * @return
	 */
	public String getPublishingRights() {
		return publishingRights;
	}
	
	/**
	 * The publishingRights to be set.
	 * 
	 * @param publishingRights
	 */
	public void setPublishingRights(String publishingRights) {
		if(this.publishingRights != publishingRights) {
			String oldValue = this.publishingRights;
			this.publishingRights = publishingRights;
			firePropertyChange("publishingRights", oldValue, publishingRights);
		}
	}
	
	/**
	 * Returns the category.
	 *
	 * @return
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * The category to be set.
	 * 
	 * @param category
	 */
	public void setCategory(String category) {
		if(this.category != category) {
			String oldValue = this.category;
			this.category = category;
			firePropertyChange("category", oldValue, category);
		}
	}
	
	/**
	 * Returns the searchKeywords.
	 *
	 * @return
	 */
	public String getSearchKeywords() {
		return searchKeywords;
	}
	
	/**
	 * The searchKeywords to be set.
	 * 
	 * @param searchKeywords
	 */
	public void setSearchKeywords(String searchKeywords) {
		if(this.searchKeywords != searchKeywords) {
			String oldValue = this.searchKeywords;
			this.searchKeywords = searchKeywords;
			firePropertyChange("searchKeywords", oldValue, searchKeywords);
		}
	}
	
	/**
	 * Returns the territoryRights.
	 *
	 * @return
	 */
	public String getTerritoryRights() {
		return territoryRights;
	}
	
	/**
	 * The territoryRights to be set.
	 * 
	 * @param territoryRights
	 */
	public void setTerritoryRights(String territoryRights) {
		if(this.territoryRights != territoryRights) {
			String oldValue = this.territoryRights;
			this.territoryRights = territoryRights;
			firePropertyChange("territoryRights", oldValue, territoryRights);
		}
	}
	
	/**
	 * Returns the price.
	 *
	 * @return
	 */
	public String getPrice() {
		return price;
	}
	
	/**
	 * The price to be set.
	 * 
	 * @param price
	 */
	public void setPrice(String price) {
		if(this.price != price) {
			String oldValue = this.price;
			this.price = price;
			firePropertyChange("price", oldValue, price);
		}
	}
	
	/**
	 * Returns the currencyType.
	 *
	 * @return
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	
	/**
	 * The currencyType to be set.
	 * 
	 * @param currencyType
	 */
	public void setCurrencyType(String currencyType) {
		if(this.currencyType != currencyType) {
			String oldValue = this.currencyType;
			this.currencyType = currencyType;
			firePropertyChange("currencyType", oldValue, currencyType);
		}
	}
	
	/**
	 * Returns a string representation of this object.
	 * 
	 * @return
	 * @see com.rslakra.bos.BusinessObject#toString()
	 */
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Book<id=").append(getId());
		sBuilder.append(", ISBN:").append(getIsbn());
		sBuilder.append(", Title:").append(getTitle());
		sBuilder.append(", Description:").append(getDescription());
		sBuilder.append(", Author:").append(getAuthor());
		sBuilder.append(", Language:").append(getLanguage());
		sBuilder.append(", PublishedOn:").append(getPublishedOn());
		sBuilder.append(", Publisher:").append(getPublisher());
		sBuilder.append(", PublishingRights:").append(getPublishingRights());
		sBuilder.append(", Category:").append(getCategory());
		sBuilder.append(", SearchKeywords:").append(getSearchKeywords());
		sBuilder.append(", TerritoryRights:").append(getTerritoryRights());
		sBuilder.append(", Price:").append(getPrice());
		sBuilder.append(", CurrencyType:").append(getCurrencyType());
		sBuilder.append(">");
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @param newDate
	 * @return
	 */
	public String dateToString(Date newDate) {
		StringBuffer result = new StringBuffer();
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(newDate);
		result.append(gregorianCalendar.get(Calendar.DAY_OF_MONTH));
		result.append("/");
		result.append((gregorianCalendar.get(Calendar.MONTH)));
		result.append("/");
		result.append(gregorianCalendar.get(Calendar.YEAR));
		return new String(result);
	}
	
	public Date stringToDate(String strDate) {
		int startIdx = strDate.indexOf("-");
		int lastIdx = strDate.lastIndexOf("-");
		String day = strDate.substring(0, startIdx);
		String month = strDate.substring(startIdx + 1, lastIdx);
		String year = strDate.substring(lastIdx + 1);
		GregorianCalendar gc = new GregorianCalendar(getInt(year), getInt(month), getInt(day));
		return gc.getTime();
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public int getInt(String value) {
		return Integer.parseInt(value);
	}
	
}