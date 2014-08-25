/******************************************************************************
 * Project Name          : EMA
 * Name                  : FaxService.java
 * Description           : 
 * References            : 
 *
 * Modification History  :
 * Date          Version     Modified by    Brief Description of Modification
 * May 30, 2013  1.0		 ca52071         Initial Release
 *******************************************************************************/
package org.sen.service.fax.service;

import java.util.List;

import org.sen.service.fax.request.FaxRequest;
import org.sen.service.fax.request.FaxResponse;

/**
 * 
 * @author ca56081
 *
 */
public interface FaxService {
	
	public List<FaxResponse> sendFax(FaxRequest faxReq);
}
