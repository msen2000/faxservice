package org.sen.service.fax.request;

public class FaxResponse {
	
	private int sId;
	
	private String faxId;
	
	private int statusCode;
	
	private String statusMessage;

	public String getFaxId() {
		return faxId;
	}

	public void setFaxId(String faxId) {
		this.faxId = faxId;
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

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}
	
	
	

}
