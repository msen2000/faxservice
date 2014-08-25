package org.sen.service.fax.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.xml.bind.JAXBException;

import RightFAX.RFInvalidIDException;
import RightFAX.RFNoDataException;
import RightFAX.RFNoFaxNumberException;
import RightFAX.RFStatus;
import RightFAX.RFaxSubmit;
/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class FaxServiceImplTest {

	private static final String RESQUEST_PREFIX = "org.delta.crossplane.messages.mail.request";

	private String testToEmail;

	private boolean testing;

	public String getTestToEmail() {
		return testToEmail;
	}

	public void setTestToEmail(String testToEmail) {
		this.testToEmail = testToEmail;
	}

	public boolean isTesting() {
		return testing;
	}

	public void setTesting(boolean testing) {
		this.testing = testing;
	}

	public static void main(String[] args) throws  IOException, JAXBException {
		FaxServiceImplTest ser = new FaxServiceImplTest();
//		ser.replaceEligibility();
		ser.sendFax();
	}
	
	public void sendFax(){
		RFaxSubmit obFS = new RFaxSubmit();
		//Set the URL of the RightFAX server
		obFS.setTargetURL("http://pa-rfapp-vip.deltads.ent/rfxml/");
		//Set the information on who is sending the fax
		obFS.m_FaxDocument.setSenderInfo("Bill Smith", "", "Acme, Co.", "", "", "", "", "", "", "BillS");
		//Add 2 recipients
		try {
			obFS.m_FaxDocument.addRecipient("PRODXML:0001", "415-975-8275", "", "Jim Jackson", "", "", "", "", "", "", "", "");
		} catch (RFNoFaxNumberException nfne) {
			System.out.println (nfne.toString());
		} catch (RFInvalidIDException iide) {
			System.out.println (iide.toString());
		}
		//Add an email and a printer recipient
//		try {
//			obFS.m_FaxDocument.addRecipient_email ("EMAIL:00000001", "pkumar2@delta.org", "", "Here is the document", "");
//			obFS.m_FaxDocument.addRecipient_printer("PRNT:000000001", "MyPrinter", (short)1);
//		} catch (RFNoDestinationException nde) {
//			System.out.println (nde.toString());
//		} catch (RFInvalidIDException iide) {
//			System.out.println (iide.toString());
//		}
		
		//Set the body text
//		obFS.m_FaxDocument.setBody ( "Here is some body text", "TXT", -1, -1, -1, "Arial", -1, -1,10);
		//Set the cover text
//		obFS.m_FaxDocument.setCoverText ("Here is some cover text");
		//Add attachments
//		obFS.addAttachment ("c:\\documents\\mydoc.doc");
		obFS.addAttachment ("C:\\A3\\docs_ema\\test.pdf");
		//Send the doucment, and get back the results.
		Vector obRetList = null;
		try {
			obRetList = obFS.submit();
		} catch (MalformedURLException mue) {
			System.out.println (mue.toString());
		} catch (UnknownHostException uhe) {
			System.out.println (uhe.toString());
		} catch (IOException ioe) {
			System.out.println (ioe.toString());
		} catch (RFNoDataException nde) {
			System.out.println ("Error:" + nde.toString());
		}
		//Output the results
		int nSize = obRetList.size();
		for (int i = 0; i < nSize; i++)
		{
		RFStatus obStat = (RFStatus)(obRetList.get(i));
		System.out.println ((i+1) + "-");
		System.out.println ("\tID: " + obStat.getID());
		System.out.println ("\tStatusCode: " + obStat.getStatusCode());
		System.out.println ("\tStatusMessage: " + obStat.getStatusMsg());
		}
	}
	
//	public void replaceEligibility() throws Docx4JException, IOException, JAXBException{
//		FaxServiceImplTest fx = new FaxServiceImplTest();
//		WordprocessingMLPackage wp = fx.getTemplate("C:\\A3\\fax\\Fax_U.docx");
//		fx.replacePlaceholder(wp, "Monday, July 8, 2013", "rep.date");
//		fx.replacePlaceholder(wp, "Prakash kumar                              `", "rep.patxxxxxxxxxxxxxxxxxxxxxxxxxx");
//		fx.replacePlaceholder(wp, "562-402-6503", "rep.fax");
//		fx.replacePlaceholder(wp, "April 28, 1981            `", "rep.dobxxxxxxxxxxxxx");
//		fx.replacePlaceholder(wp, "123456789             `", "rep.memidxxxxxxxxxxx");
//		fx.replacePlaceholder(wp, "Self          `", "rep.relaxxxxx");
//		fx.replacePlaceholder(wp, "The Honest Co.          `", "rep.group");
//		fx.replacePlaceholder(wp, "12345-00001                                 `", "rep.groupnoxxxxxxxxxxxxxxxxxxx");
//		fx.replacePlaceholder(wp, "Active                              `", "rep.activexxxxxxxxxxxxxxx");
//		fx.replacePlaceholder(wp, "DeltaCare USA            `", "rep.type");
//		fx.replacePlaceholder(wp, "CA11A                   `", "rep.plan");
//		fx.replacePlaceholder(wp, "12-100-07082013-123456789       `", "rep.ivrxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//		
//		
////      WordprocessingMLPackage wp = fx.getTemplate("C:\\A3\\fax.docx");
////      fx.replacePlaceholder(wp, "prakash", "{");
////      fx.replacePlaceholder(wp, "prakashsk", "sk");
////      fx.replacePlaceholder(wp, "12345", "Phone");
////      fx.replacePlaceholder(wp, "prakashfax", "fax");
////      fx.replacePlaceholder(wp, "prakashhead", "Head");
////      fx.replacePlaceholder(wp, "prakashnum", "num");
////      
//      Map<String,String> repl1 = new HashMap<String, String>();
//      repl1.put("rep_rela", "function1");
//      repl1.put("rep_name", "desc1");
//      repl1.put("rep_dob", "period1");
//      repl1.put("rep_fac", "period1");
//      repl1.put("rep_plan", "period1");
//      repl1.put("rep_assign", "period1");
//      repl1.put("rep_st", "period1");
//
//      Map<String,String> repl2 = new HashMap<String, String>();
//      repl2.put("rep_rela", "function1");
//      repl2.put("rep_name", "desc1");
//      repl2.put("rep_dob", "period1");
//      repl2.put("rep_fac", "period1");
//      repl2.put("rep_plan", "period1");
//      repl2.put("rep_assign", "period1");
//      repl2.put("rep_st", "period1");
//      
//      Map<String,String> repl3 = new HashMap<String, String>();
//      repl3.put("rep_rela", "function1");
//      repl3.put("rep_name", "desc1");
//      repl3.put("rep_dob", "period1");
//      repl3.put("rep_fac", "period1");
//      repl3.put("rep_plan", "period1");
//      repl3.put("rep_assign", "period1");
//      repl3.put("rep_st", "period1");
////      Map<String,String> repl2 = new HashMap<String, String>();
////      repl2.put("SJ_FUNCTION", "function2");
////      repl2.put("SJ_DESC", "desc2");
////      repl2.put("SJ_PERIOD", "period2");
////
////      Map<String,String> repl3 = new HashMap<String, String>();
////      repl3.put("SJ_FUNCTION", "function3");
////      repl3.put("SJ_DESC", "desc3");
////      repl3.put("SJ_PERIOD", "period3");
//
//      fx.replaceTable(new String[]{"rep_rela","rep_name","rep_dob","rep_fac","rep_plan","rep_assign","rep_st"}, Arrays.asList(repl1, repl2, repl3), wp);
////      fx.writeDocxToStream(wp, "C:\\A3\\faxta.docx");
//
//		
//		fx.writeDocxToStream(wp, "C:\\A3\\fax\\fax_X.docx");
//		
//	}
//	
//	private Tbl getTemplateTable(List<Object> tables, String templateKey) throws Docx4JException, JAXBException {
//		for (Iterator<Object> iterator = tables.iterator(); iterator.hasNext();) {
//			Object tbl = iterator.next();
//			List<?> textElements = getAllElementFromObject(tbl, Text.class);
//			for (Object text : textElements) {
//				Text textElement = (Text) text;
//				if (textElement.getValue() != null && textElement.getValue().equals(templateKey))
//					return (Tbl) tbl;
//			}
//		}
//		return null;
//	}
//	
//	private static void addRowToTable(Tbl reviewtable, Tr templateRow, Map<String, String> replacements) {
//		Tr workingRow = (Tr) XmlUtils.deepCopy(templateRow);
//		List<?> textElements = getAllElementFromObject(workingRow, Text.class);
//		for (Object object : textElements) {
//			Text text = (Text) object;
//			String replacementValue = (String) replacements.get(text.getValue());
//			if (replacementValue != null)
//				text.setValue(replacementValue);
//		}
// 
//		reviewtable.getContent().add(workingRow);
//	}
//	
//	private void replaceTable(String[] placeholders, List<Map<String, String>> textToAdd,
//			WordprocessingMLPackage template) throws Docx4JException, JAXBException {
//		List<Object> tables = getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);
// 
//		// 1. find the table
//		Tbl tempTable = getTemplateTable(tables, placeholders[0]);
//		List<Object> rows = getAllElementFromObject(tempTable, Tr.class);
// 
//		// first row is header, second row is content
//		if (rows.size() >= 2) {
//			// this is our template row
//			Tr templateRow = (Tr) rows.get(1);
// 
//			for (Map<String, String> replacements : textToAdd) {
//				// 2 and 3 are done in this method
//				addRowToTable(tempTable, templateRow, replacements);
//			}
// 
//			// 4. remove the template row
//			tempTable.getContent().remove(templateRow);
//		}
//	}
//	private WordprocessingMLPackage getTemplate(String name) throws Docx4JException, FileNotFoundException {
//		WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new File(name)));
//		return template;
//	}
//	
//	private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
//		List<Object> result = new ArrayList<Object>();
//		if (obj instanceof JAXBElement) obj = ((JAXBElement<?>) obj).getValue();
// 
//		if (obj.getClass().equals(toSearch))
//			result.add(obj);
//		else if (obj instanceof ContentAccessor) {
//			List<?> children = ((ContentAccessor) obj).getContent();
//			for (Object child : children) {
//				result.addAll(getAllElementFromObject(child, toSearch));
//			}
// 
//		}
//		return result;
//	}
//	
//	private void replacePlaceholder(WordprocessingMLPackage template, String name, String placeholder ) {
//		List<Object> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);
// 
//		for (Object text : texts) {
//			Text textElement = (Text) text;
//			System.out.println("text: "+textElement.getValue());
//			if (textElement.getValue().equals(placeholder)) {
//				textElement.setValue(name);
//			}
//		}
//	}
//	
//	private void writeDocxToStream(WordprocessingMLPackage template, String target) throws IOException, Docx4JException {
//		File f = new File(target);
//		template.save(f);
//	}

}