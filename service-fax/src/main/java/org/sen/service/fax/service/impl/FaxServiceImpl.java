package org.sen.service.fax.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

import org.sen.service.fax.request.FaxRequest;
import org.sen.service.fax.request.FaxResponse;
import org.sen.service.fax.service.FaxService;

import RightFAX.RFInvalidIDException;
import RightFAX.RFNoDataException;
import RightFAX.RFNoFaxNumberException;
import RightFAX.RFStatus;
import RightFAX.RFaxSubmit;

//import RightFAX.*;

/**
 * 
 * @author 
 * 
 * 
 * #email properties
#email.smtp.host=smtprelay.deltapre.ent
email.smtp.host=rc-sun03.ut.dentegra.lab
#For Dev/Demo/Testing purposes, the emails will be sent to the following email id.
email.istesting=true
email.testtoemail=mvishwanatham@delta.org
email.sendEmail=true
email.nonrepliable.from=dit2_internal_ddc_svc_ema5@delta.org


fax.faxServerUrl=http://pa-rfapp-vip.deltads.ent/rfxml/
fax.istesting=true
fax.testtofax=415-975-8275
fax.userId=CA56081
fax.logourl=/opt/oracle/domains/a3web_domain/a3prop/
fax.senderEmail=noreply-EIVR@uatmsg.delta.org
fax.ebduserId=GAEBDEIVR

 * 
 */
@SuppressWarnings("unchecked")
public class FaxServiceImpl implements FaxService {

//	private static final Log logger = LogFactory.getLog(FaxServiceImpl.class);

	private String testToFax;

	private boolean testing;

	private String faxServerUrl;
	
	private String userId;
	
	private String ebduserId;
	
	
	public String getEbduserId() {
		return ebduserId;
	}

	public void setEbduserId(String ebduserId) {
		this.ebduserId = ebduserId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isTesting() {
		return testing;
	}

	public void setTesting(boolean testing) {
		this.testing = testing;
	}

	public String getTestToFax() {
		return testToFax;
	}

	public void setTestToFax(String testToFax) {
		this.testToFax = testToFax;
	}

	public String getFaxServerUrl() {
		return faxServerUrl;
	}

	public void setFaxServerUrl(String faxServerUrl) {
		this.faxServerUrl = faxServerUrl;
	}

	public List<FaxResponse> sendFax(FaxRequest faxReq) {
		List<FaxResponse> listFaxRes = new ArrayList<FaxResponse>();
		RFaxSubmit obFS = new RFaxSubmit();
		
		// Set the URL of the RightFAX server
		if(isTesting() && (faxReq.getSource() == null || faxReq.getSource().trim().equals("EBD"))){
			faxReq.setFaxNumber(getTestToFax());
		}
		obFS.setTargetURL(getFaxServerUrl());
		if (faxReq.getSource() != null && faxReq.getSource().trim().equals("IVR")){
			obFS.m_FaxDocument.setSenderInfo(getUserId());
		}else if(faxReq.getSource() == null || faxReq.getSource().trim().equals("EBD")) {
			obFS.m_FaxDocument.setSenderInfo(getEbduserId());
		} 
		// Set the information on who is sending the fax
		obFS.m_FaxDocument.setSenderInfo(faxReq.getSenderName(), "",
				faxReq.getSenderCompanyName(), "", "", "", "", "", "", "");
		// Add 2 recipients
		try {
			obFS.m_FaxDocument.addRecipient(faxReq.getRecipientID(),
					faxReq.getFaxNumber(), "", faxReq.getRecipientName(), "",
					"", "", "", "", "", "", "");
		} catch (RFNoFaxNumberException nfne) {
			System.out.println(nfne.toString());
		} catch (RFInvalidIDException iide) {
			System.out.println(iide.toString());
		}
		obFS.addAttachment(faxReq.getAttachmentLocation());
		// Send the doucment, and get back the results.
		Vector obRetList = null;
		try {
			obRetList = obFS.submit();
		} catch (MalformedURLException mue) {
//			logger.error("FaxService:" + mue.toString());
			System.out.println(mue.toString());
		} catch (UnknownHostException uhe) {
//			logger.error("FaxService:" + uhe.toString());
			System.out.println(uhe.toString());
		} catch (IOException ioe) {
//			logger.error("FaxService:" + ioe.toString());
			System.out.println(ioe.toString());
		} catch (RFNoDataException nde) {
//			logger.error("FaxService:" + nde.toString());
			System.out.println("Error:" + nde.toString());
		}
		// Output the results
		int nSize = obRetList.size();
		for (int i = 0; i < nSize; i++) {
			RFStatus obStat = (RFStatus) (obRetList.get(i));
			FaxResponse faxRes = new FaxResponse();
			System.out.println((i + 1) + "-");
			faxRes.setsId(i + 1);
			System.out.println("\tID: " + obStat.getID());
			faxRes.setFaxId(obStat.getID());
			System.out.println("\tStatusCode: " + obStat.getStatusCode());
			faxRes.setStatusCode(obStat.getStatusCode());
			System.out.println("\tStatusMessage: " + obStat.getStatusMsg());
			faxRes.setStatusMessage(obStat.getStatusMsg());
			listFaxRes.add(faxRes);
		}

		return listFaxRes;
	}

}