package org.sen.service.fax.service;

import org.sen.service.fax.service.FaxService;

public class FaxDocumentTest {

	
	private FaxService faxService;
	
	public FaxService getFaxService() {
		return faxService;
	}

	public void setFaxService(FaxService faxService) {
		this.faxService = faxService;
	}

	protected String[] getConfigLocations() {
		return new String[] { "applicationContextTest.xml" };
	}

	public void testFaxDocument() throws Throwable {
		getFaxService().sendFax(null);
	}
}
