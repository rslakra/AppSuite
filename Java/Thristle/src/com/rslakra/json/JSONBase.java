package com.rslakra.json;

import com.rslakra.servers.utils.JsonUtil;

public abstract class JSONBase {
	
	/** boName */
	protected String boName = getBOName();
	
	/**
	 * 
	 * @return
	 */
	public abstract String getBOName();
	
	/**
	 * 
	 * @return
	 */
	public String toJson() {
		return JsonUtil.jsonString(this);
	}
	
}
