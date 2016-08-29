package com.scalefocus.edu.api.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExceptionHandlerAPI {
	
	private int statusCode;
	private String outputMessage;
	private String statusMessage;
	private String moreInfo;

	public ExceptionHandlerAPI(int statusCode, String outputMessage, String statusMessage, String moreInfo) {
		super();
		this.statusCode = statusCode;
		this.outputMessage = outputMessage;
		this.statusMessage = statusMessage;
		this.moreInfo = moreInfo;
	}

	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	public String getOutputMessage() {
		return outputMessage;
	}

	public void setOutputMessage(String outputMessage) {
		this.outputMessage = outputMessage;
	}


	public String getMoreInfo() {
		return moreInfo;
	}


	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}
}
