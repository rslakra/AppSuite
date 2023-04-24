/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 *
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * <pre>
 *  1. Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * </pre>
 * <p/>
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.appsuite.jdk;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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
 * @author Rohtash Singh (rohtash.singh@devamatre.com) Created on Aug 9, 2009
 * @version 1.0
 */
public class AbstractDataObject implements DataObject {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1809740618357679307L;
    /**
     * The propertyChangeSupport is used to maintain a list of listeners added
     * and removed from that property.
     */
    protected transient PropertyChangeSupport propertyChangeSupport;

    public AbstractDataObject() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    /*-------------- Getter/Setter Region ------------------*/

    /**
     * Returns the propertyChangeSupport instance for this object which manages
     * all property change listeners added on this object. This getter method is
     * used to get the current PropertyChangeSupport, which consist of the list
     * of listeners.
     */
    final private PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    /*-------------- Abstract Methods ------------------*/

    /**
     * Add a PropertyChangeListener to the listener list.
     */
    final public synchronized void addPropertyChangeListener(PropertyChangeListener propChangeListener) {
        getPropertyChangeSupport().addPropertyChangeListener(propChangeListener);
    }

    /**
     * Remove a PropertyChangeListener from the listener list.
     */
    final public synchronized void removePropertyChangeListener(PropertyChangeListener propChangeListener) {
        getPropertyChangeSupport().removePropertyChangeListener(propChangeListener);
    }

    /**
     * Add a PropertyChangeListener for a specific property.
     */
    final public synchronized void addPropertyChangeListener(String property,
                                                             PropertyChangeListener propChangeListener) {
        getPropertyChangeSupport().addPropertyChangeListener(property, propChangeListener);
    }

    /**
     * Remove a PropertyChangeListener for a specific property.
     */
    final public synchronized void removePropertyChangeListener(String property,
                                                                PropertyChangeListener propChangeListener) {
        getPropertyChangeSupport().removePropertyChangeListener(property, propChangeListener);
    }

    /**
     * To be called by classes inheriting this class when a propertyValue is
     * changed. This method accepts a property name, old value and new value for
     * that property and fires all listeners added to this object for that
     * property as well as the listeners added to this object without specifying
     * any property Name.
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
     */

    final protected void firePropertyChange(String property, boolean oldValue, boolean newValue) {
        getPropertyChangeSupport().firePropertyChange(property, oldValue, newValue);
    }

    /**
     * Overridden method of java.lang.Object and returns a string, which
     * describes this object. Its overridden with hashCode so that the printing
     * of PersistenceCapable objects and debugging of application is made easy.
     */
    public String toString() {
        return super.toString();
    }
}
