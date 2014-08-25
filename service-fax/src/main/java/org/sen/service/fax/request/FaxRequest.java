package org.sen.service.fax.request;

public class FaxRequest {
	
	private String senderName;
	
	private String senderCompanyName;
	
	private String recipientName;
	
	private String faxNumber;
	
	private String recipientID;
	
	private String attachmentLocation;
	
	private String source;
	

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderCompanyName() {
		return senderCompanyName;
	}

	public void setSenderCompanyName(String senderCompanyName) {
		this.senderCompanyName = senderCompanyName;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getRecipientID() {
		return recipientID;
	}

	public void setRecipientID(String recipientID) {
		this.recipientID = recipientID;
	}

	public String getAttachmentLocation() {
		return attachmentLocation;
	}

	public void setAttachmentLocation(String attachmentLocation) {
		this.attachmentLocation = attachmentLocation;
	}

	@Override
	public String toString() {
		return "FaxRequest [senderName=" + senderName + ", senderCompanyName="
				+ senderCompanyName + ", recipientName=" + recipientName
				+ ", faxNumber=" + faxNumber + ", recipientID=" + recipientID
				+ ", attachmentLocation=" + attachmentLocation + "]";
	}
	
	

}
