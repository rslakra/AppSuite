package com.rslakra.servers.thristle;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.devmatre.core.utils.CoreUtil;
import com.devmatre.core.xml.AbstractSAXParser;
import com.devmatre.core.xml.XmlUtil;
import com.devmatre.logger.LogManager;
import com.devmatre.logger.Logger;

public class ServiceRequestParser extends AbstractSAXParser {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** logger */
	private static final Logger logger = LogManager.getLogger(ServiceRequestParser.class);
	
	/** ServiceRequest Tags */
	private static final String TAG_SERVICE_REQUESTS = "ServiceRequests";
	private static final String TAG_SERVICE_REQUEST = "ServiceRequest";
	private static final String TAG_REQUEST_HANDLER = "requestHandler";
	private static final String TAG_SERVICE_NAME = "ServiceName";
	private static final String TAG_METHOD_NAME = "MethodName";
	private static final String TAG_PARAMS = "Params";
	private static final String TAG_RESPONSE_TYPE = "ResponseType";
	
	static class ServiceRequest {
		private String requestHandler;
		private String serviceName;
		private String methodName;
		/* # separated parameter names */
		private String params;
		private String responseType;
		private List<String> paramList;
		
		public ServiceRequest() {
			
		}
		
		/**
		 * Returns the serviceName.
		 *
		 * @return
		 */
		public final String getServiceName() {
			return serviceName;
		}
		
		/**
		 * The serviceName to be set.
		 * 
		 * @param serviceName
		 */
		public final void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}
		
		/**
		 * Returns the requestHandler.
		 *
		 * @return
		 */
		public final String getRequestHandler() {
			return requestHandler;
		}
		
		/**
		 * The requestHandler to be set.
		 * 
		 * @param requestHandler
		 */
		public final void setRequestHandler(String requestHandler) {
			this.requestHandler = requestHandler;
		}
		
		/**
		 * Returns the methodName.
		 *
		 * @return
		 */
		public final String getMethodName() {
			return methodName;
		}
		
		/**
		 * The methodName to be set.
		 * 
		 * @param methodName
		 */
		public final void setMethodName(String methodName) {
			this.methodName = methodName;
		}
		
		/**
		 * Returns the params.
		 *
		 * @return
		 */
		public final String getParams() {
			return params;
		}
		
		/**
		 * The params to be set.
		 * 
		 * @param params
		 */
		public final void setParams(String params) {
			this.params = params;
			// TODO: Populate paramList here.
		}
		
		/**
		 * Returns the responseType.
		 *
		 * @return
		 */
		public final String getResponseType() {
			return responseType;
		}
		
		/**
		 * The responseType to be set.
		 * 
		 * @param responseType
		 */
		public final void setResponseType(String responseType) {
			this.responseType = responseType;
		}
	}
	
	/** serviceRequest */
	private ServiceRequest serviceRequest;
	/** serviceRequests */
	private List<ServiceRequest> serviceRequests;
	
	/**
	 * 
	 */
	public ServiceRequestParser() {
		super();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<ServiceRequest> getServiceRequests() {
		return serviceRequests;
	}
	
	/**
	 * 
	 * @see com.devmatre.core.xml.AbstractSAXParser#startDocumentPost()
	 */
	@Override
	protected void startDocumentPost() {
	}
	
	/**
	 * 
	 * @see com.devmatre.core.xml.AbstractSAXParser#startElementPost(java.lang.String,
	 *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	protected void startElementPost(String uri, String localName, String qName, Attributes attributes) {
		logger.debug("+startElementPost(" + uri + ", " + localName + ", " + qName + ", " + attributes + ")");
		
		if(TAG_SERVICE_REQUESTS.equals(qName)) {
			serviceRequests = new ArrayList<ServiceRequest>();
		} else if(TAG_SERVICE_REQUEST.equals(qName)) {
			serviceRequest = new ServiceRequest();
			/* read attributes by index, if any. */
			if(attributes != null) {
				serviceRequest.setRequestHandler(attributes.getValue(0));
			}
			
			/* add in books list */
			serviceRequests.add(serviceRequest);
		}
		
		logger.debug("-startElementPost()");
	}
	
	/**
	 * 
	 * @see com.devmatre.core.xml.AbstractSAXParser#preEndElement(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	protected boolean preEndElement(String uri, String name, String qName) {
		logger.debug("+preEndElement(" + uri + ", " + name + ", " + qName + ")");
		
		if(TAG_SERVICE_NAME.equals(qName)) {
			serviceRequest.setServiceName(getBuffer());
		} else if(TAG_METHOD_NAME.equals(qName)) {
			serviceRequest.setMethodName(getBuffer());
		} else if(TAG_PARAMS.equals(qName)) {
			serviceRequest.setParams(getBuffer());
		} else if(TAG_RESPONSE_TYPE.equals(qName)) {
			serviceRequest.setResponseType(getBuffer());
		}
		
		logger.debug("-preEndElement()");
		return true;
	}
	
	/**
	 * Returns the XML representation of this object.
	 * 
	 * @return
	 * @see com.devmatre.core.xml.AbastractHandler#toXMLString()
	 */
	@Override
	public String toXMLString() {
		StringBuilder xmlBuilder = new StringBuilder();
		if(!CoreUtil.isNullOrEmpty(getServiceRequests())) {
			xmlBuilder.append(XmlUtil.startElement(TAG_SERVICE_REQUESTS, true));
			for(ServiceRequest xmlObject : getServiceRequests()) {
				xmlBuilder.append(XmlUtil.generateElement(TAG_SERVICE_REQUEST, new String[] {
						TAG_REQUEST_HANDLER },
						new String[] {
								xmlObject.getRequestHandler() },
						false));
				xmlBuilder.append(XmlUtil.generateElement(TAG_SERVICE_NAME, xmlObject.getServiceName()));
				xmlBuilder.append(XmlUtil.generateElement(TAG_METHOD_NAME, xmlObject.getMethodName()));
				xmlBuilder.append(XmlUtil.generateElement(TAG_PARAMS, xmlObject.getParams()));
				xmlBuilder.append(XmlUtil.generateElement(TAG_RESPONSE_TYPE, xmlObject.getResponseType()));
				xmlBuilder.append(XmlUtil.endElement(TAG_SERVICE_REQUEST));
			}
			xmlBuilder.append(XmlUtil.endElement(TAG_SERVICE_REQUESTS));
		}
		
		return xmlBuilder.toString();
	}
	
}
