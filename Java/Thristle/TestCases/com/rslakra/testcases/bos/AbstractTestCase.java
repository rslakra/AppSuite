package com.rslakra.testcases.bos;

import com.rslakra.bos.BusinessObject;

public abstract class AbstractTestCase {
	
	protected BusinessObject businessObject;
	
	/**
	 * 
	 * @param businessObject
	 */
	protected AbstractTestCase(BusinessObject businessObject) {
		this.businessObject = businessObject;
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getBusinessObject() {
		return (T) businessObject;
	}
	
	/**
	 * 
	 * @param businessObject
	 */
	public void populateDefault() {
	}
	
}
