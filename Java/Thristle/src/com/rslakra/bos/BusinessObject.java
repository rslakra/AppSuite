package com.rslakra.bos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import com.devmatre.core.xml.XmlUtil;

/**
 * This is the super class of all persistence capable classes. This class
 * provides common methods for adding, removing and firing property change
 * listeners. However it is not necessary to subclass your persistence capable
 * objects from this class, but this class is created to help you to provide
 * necessary functionality. If one does not inherits this class, you must
 * implement java.io.Serializable interface, provide methods for adding and
 * removing listeners and you would have to manage firing of all added listeners
 * on that class.
 * 
 * @author Rohtash Singh (rohtash.singh@devmatre.com)
 * @version 1.0.0
 */
public abstract class BusinessObject implements Serializable {
	
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The propertyChangeSupport is used to maintain a list of listeners added
	 * and removed from that property.
	 */
	protected transient PropertyChangeSupport propertyChangeSupport;
	
	/** id */
	private long id;
	
	/** uuid - for future usage. */
	private transient String uuid;
	
	/** active */
	private boolean active;
	
	/**
	 * 
	 */
	public BusinessObject() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	/**
	 * Returns the propertyChangeSupport instance for this object which manages
	 * all property change listeners added on this object. This getter method is
	 * used to get the current PropertyChangeSupport, which consist of the list
	 * of listeners.
	 * 
	 * @return
	 */
	final private PropertyChangeSupport getPropertyChangeSupport() {
		return propertyChangeSupport;
	}
	
	/**
	 * Add a PropertyChangeListener to the listener list.
	 * 
	 * @see com.devmatre.core.dataobject.DataObject#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	final public synchronized void addPropertyChangeListener(PropertyChangeListener propChangeListener) {
		getPropertyChangeSupport().addPropertyChangeListener(propChangeListener);
	}
	
	/**
	 * Remove a PropertyChangeListener from the listener list.
	 * 
	 * @see com.devmatre.core.dataobject.DataObject#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	final public synchronized void removePropertyChangeListener(PropertyChangeListener propChangeListener) {
		getPropertyChangeSupport().removePropertyChangeListener(propChangeListener);
	}
	
	/**
	 * Add a PropertyChangeListener for a specific property.
	 * 
	 * @see com.devmatre.core.dataobject.DataObject#addPropertyChangeListener(java.lang.String,
	 *      java.beans.PropertyChangeListener)
	 */
	final public synchronized void addPropertyChangeListener(String property, PropertyChangeListener propChangeListener) {
		getPropertyChangeSupport().addPropertyChangeListener(property, propChangeListener);
	}
	
	/**
	 * Remove a PropertyChangeListener for a specific property.
	 * 
	 * @see com.devmatre.core.dataobject.DataObject#removePropertyChangeListener(java.lang.String,
	 *      java.beans.PropertyChangeListener)
	 */
	final public synchronized void removePropertyChangeListener(String property, PropertyChangeListener propChangeListener) {
		getPropertyChangeSupport().removePropertyChangeListener(property, propChangeListener);
	}
	
	/**
	 * To be called by classes inheriting this class when a propertyValue is
	 * changed. This method accepts a property name, old value and new value for
	 * that property and fires all listeners added to this object for that
	 * property as well as the listeners added to this object without specifying
	 * any property Name.
	 * 
	 * @param property
	 * @param oldValue
	 * @param newValue
	 */
	final protected void firePropertyChange(String property, Object oldValue, Object newValue) {
		getPropertyChangeSupport().firePropertyChange(property, oldValue, newValue);
	}
	
	/**
	 * To be called by classes inheriting this class when a propertyValue is
	 * changed. This method accepts a property name, old value and new value for
	 * that property and fires all listeners added to this object for that
	 * property as well as the listeners added to this object without specifying
	 * any property Name.
	 * 
	 * @param property
	 * @param oldValue
	 * @param newValue
	 */
	final protected void firePropertyChange(String property, int oldValue, int newValue) {
		getPropertyChangeSupport().firePropertyChange(property, oldValue, newValue);
	}
	
	/**
	 * To be called by classes inheriting this class when a propertyValue is
	 * changed. This method accepts a property name, old value and new value for
	 * that property and fires all listeners added to this object for that
	 * property as well as the listeners added to this object without specifying
	 * any property Name.
	 * 
	 * @param property
	 * @param oldValue
	 * @param newValue
	 */
	final protected void firePropertyChange(String property, boolean oldValue, boolean newValue) {
		getPropertyChangeSupport().firePropertyChange(property, oldValue, newValue);
	}
	
	/**
	 * Returns the id.
	 * 
	 * @return
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * The id to be set.
	 * 
	 * @param id
	 */
	public void setId(long id) {
		if(this.id != id) {
			long oldValue = this.id;
			this.id = id;
			firePropertyChange("id", oldValue, id);
		}
	}
	
	/**
	 * Returns the uuid.
	 * 
	 * @return
	 */
	public String getUuid() {
		return uuid;
	}
	
	/**
	 * The uuid to be set.
	 * 
	 * @param id
	 */
	public void setUuid(String uuid) {
		if(this.uuid != uuid) {
			String oldValue = this.uuid;
			this.uuid = uuid;
			firePropertyChange("uuid", oldValue, uuid);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * The active to be set.
	 * 
	 * @param active
	 */
	public void setActive(boolean active) {
		if(this.active != active) {
			boolean oldValue = this.active;
			this.active = active;
			firePropertyChange("active", oldValue, active);
		}
	}
	
	/**
	 * Returns a string representation of this object.
	 * 
	 * @return
	 * @see com.rslakra.bos.BusinessObject#toString()
	 */
	public String toString() {
		return super.toString();
	}
	
	/**
	 * Returns the XML string for this object.
	 * 
	 * @see com.devmatre.core.dataobject.DataObject#toXmlString()
	 */
	public String toXmlString() {
		StringBuffer xmlString = new StringBuffer();
		xmlString.append(XmlUtil.startElement("Classes", true));
		xmlString.append(XmlUtil.generateElement("Class", getClass().getName()));
		xmlString.append(XmlUtil.endElement("Classes"));
		return xmlString.toString();
	}
}