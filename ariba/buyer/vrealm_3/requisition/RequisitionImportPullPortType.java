package ariba.buyer.vrealm_3.requisition;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.cxf.annotations.Logging;

/**
 * This class was generated by Apache CXF 2.7.17
 * 2015-10-12T17:21:14.757+03:00
 * Generated source version: 2.7.17
 * 
 */
@WebService(targetNamespace = "urn:Ariba:Buyer:vrealm_3", name = "RequisitionImportPullPortType")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface RequisitionImportPullPortType {

    @WebResult(name = "RequisitionImportPullReply", targetNamespace = "urn:Ariba:Buyer:vrealm_3", partName = "body")
    @WebMethod(operationName = "RequisitionImportPullOperation", action = "/Process Definition")
    public RequisitionImportPullReply requisitionImportPullOperation(
        @WebParam(partName = "body", name = "RequisitionImportPullRequest", targetNamespace = "urn:Ariba:Buyer:vrealm_3")
        RequisitionImportPullRequest body
    );
}
