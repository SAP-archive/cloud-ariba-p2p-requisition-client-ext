package ariba.buyer.requisition;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

/**
 * SOAP client for requisitions.
 */
public class RequisitionSOAPClient {

	private enum ResponseSuccessfulStatus {
		COMPOSING, SUBMITTED, APPROVED
	}

	private static final String NAMESPACE_PREFIX = "urn";
	private static final String URN_ARIBA_BUYER = NAMESPACE_PREFIX + ":Ariba:Buyer:{0}";

	private static final String AUTHORIZATION = "Authorization";
	private static final String BASIC = "Basic ";
	private static final String STATUS_STRING = "StatusString";
	private static final String EMPTY_STRING = "";

	private static final String DEBUG_CREATING_SOAP_REQUEST_MESSAGE = "Creating SOAP request...";
	private static final String DEBUG_SOAP_RESPONSE_МESSAGE = "SOAP response: {}";
	private static final String DEBUG_INVOKED_SUBMIT_TO_URL_MESSAGE = "Invoked submit to URL {}";
	private static final String DEBUG_CREATED_SOAP_REQUEST_MESSAGE = "Created SOAP request: {}";

	private static final String ERROR_SUBMIT_FAILED_WITH_RESPONSE = "Submit failed with response: {0}";
	private static final String ERROR_PARAMETER_PASSWOR_MESSAGE = "Parameter 'password' should not be null or empty.";
	private static final String ERROR_PARAMETER_USER_MESSAGE = "Parameter 'user' should not be null or empty.";
	private static final String ERROR_COULD_NOT_CLOSE_SOAP_CONNECTION_MESSAGE = "Could not close SOAP connection.";
	private static final String ERROR_PARAMETER_REQUISITION_MESSAGE = "Parameter 'requisition' should not be null.";
	private static final String ERROR_PARAMETER_URL_MESSAGE = "Parameter 'url' should not be null.";
	private static final String ERROR_SOAP_CONNECTION_FAIL_MESSAGE = "Could not establish SOAP connection.";
	private static final String ERROR_COULD_NOT_READ_SOAP_MESSAGE = "Could not read SOAP message.";

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

	private static final Logger LOGGER = LoggerFactory.getLogger(RequisitionSOAPClient.class);

	/**
	 * Creates request as SOAPMessage object and submits it to Ariba instance.
	 *
	 * @param url
	 *            template for URL: https://s1.ariba.com/Buyer/soap/&ltariba
	 *            instace&gt/RequisitionImportPull
	 * @param requisition
	 *            requisition object
	 * @param user
	 *            user for authorization and access to the end point
	 * @param password
	 *            password for authorization and access to the end point
	 * @return response as SOAPMessage object
	 * @throws SOAPException
	 *             if there is problem with connection or request/response
	 *             message
	 */
	public static SOAPMessage submit(URL url, Requisition requisition, String user, String password)
			throws SOAPException {
		handleIllegalParameters(url, requisition, user, password);

		LOGGER.debug(DEBUG_INVOKED_SUBMIT_TO_URL_MESSAGE, url.toString());

		SOAPConnection connection = null;
		SOAPMessage result = null;
		try {
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			connection = soapConnectionFactory.createConnection();

			SOAPMessage soapRequest = createRequest(requisition, user, password);
			SOAPMessage soapResponse = connection.call(soapRequest, url);

			validateResponse(soapResponse);

			result = soapResponse;
		} catch (SOAPException e) {
			LOGGER.error(ERROR_SOAP_CONNECTION_FAIL_MESSAGE, e);
			throw new SOAPException(ERROR_SOAP_CONNECTION_FAIL_MESSAGE, e);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SOAPException e) {
				LOGGER.error(ERROR_COULD_NOT_CLOSE_SOAP_CONNECTION_MESSAGE, e);
			}
		}

		return result;
	}

	private static SOAPMessage createRequest(Requisition requisition, String user, String password)
			throws SOAPException {
		LOGGER.debug(DEBUG_CREATING_SOAP_REQUEST_MESSAGE);

		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage request = factory.createMessage();
		MimeHeaders mimeHeaders = request.getMimeHeaders();
		mimeHeaders.addHeader(AUTHORIZATION, getAuthorizationHeader(user, password));
		SOAPPart soapPart = request.getSOAPPart();

		SOAPEnvelope envelope = soapPart.getEnvelope();

		envelope.addNamespaceDeclaration(NAMESPACE_PREFIX,
				MessageFormat.format(URN_ARIBA_BUYER, requisition.getNamespaceXMLNSvariant()));

		SOAPBody soapBody = envelope.getBody();
		/**
		 * <pre>
		  Example for adding your custom elements in SOAPBody:
		*
		  1. addChildElements(soapBody, "ElemName"); - adding an element without a value:
		  	<urn:ElemName/>
		*
		  2. addChildElements(soapBody, "ElemName1", "ElemName2", "ElemName3", "ElemName4"); - adding nested elements:
			<urn:ElemName1>
				<urn:ElemName2>
					<urn:ElemName3>
						<urn:ElemName4/>
					</urn:ElemName3>
				</urn:ElemName2>
			</urn:ElemName1>
		*
		  3. addChildElementsWithValue(soapBody, "Value", "ElemName"); - adding an element with a value:
		  	<urn:ElemName>Value</urn:ElemName>
		*
		  4. addChildElementsWithValue(soapBody, "Value4", "ElemName1", "ElemName2", "ElemName3", "ElemName4"); - adding nested elements with a value in the last element:
			<urn:ElemName1>
				<urn:ElemName2>
					<urn:ElemName3>
						<urn:ElemName4>Value4</urn:ElemName4>
					</urn:ElemName3>
				</urn:ElemName2>
			</urn:ElemName1>
		*
		  5. SOAPElement soapElement3 = addChildElements(soapBody, "ElemName1", "ElemName2", "ElemName3"); - adding nested elements with values
		     addChildElementsWithValue(soapElement3, "Value4_1", "ElemName4_1");
		     addChildElements(soapElement3, "ElemName4_2_1", "ElemName4_2_2");
		     addChildElementsWithValue(soapElement3, "Value4_3_2", "ElemName4_3_1", "ElemName4_3_2");
		*
			<urn:ElemName1>
				<urn:ElemName2>
					<urn:ElemName3>
						<urn:ElemName4_1>Value4_1</urn:ElemName4_1>
						<urn:ElemName4_2_1>
							<urn:ElemName4_2_2/>
						</urn:ElemName4_2_1>
						<urn:ElemName4_3_1>
							<urn:ElemName4_3_2>Value4_3_2</urn:ElemName4_3_2>
						</urn:ElemName4_3_1>
					</urn:ElemName3>
				</urn:ElemName2>
			</urn:ElemName1>
		 *
		 * </pre>
		 */

		fillSoapBody(soapBody, requisition);

		request.saveChanges();

		LOGGER.debug(DEBUG_CREATED_SOAP_REQUEST_MESSAGE, soapMessageToString(request));
		return request;
	}

	private static void fillSoapBody(SOAPElement soapBody, Requisition requisition) throws SOAPException {
		SOAPElement item = addChildElements(soapBody, "RequisitionImportPullRequest",
				"Requisition_RequisitionImportPull_Item", "item");

		addChildDefaultLine(soapBody, requisition);

		addChildElementsWithValue(item, requisition.getHeaderComment(), "ImportedHeaderCommentStaging");
		addChildElementsWithValue(item, "false", "ImportedHeaderExternalCommentStaging");

		SOAPElement lineItems = addChildElements(item, "LineItems");
		for (RequisitionItem itemParam : requisition.getItems()) {
			SOAPElement lineItem = addChildElements(lineItems, "item");
			addLineItem(lineItem, itemParam);
		}

		addChildElementsWithValue(item, requisition.getHeaderName(), "Name");
		addChildElementsWithValue(item, requisition.getOriginatingSystem(), "OriginatingSystem");
		addChildElementsWithValue(item, requisition.getOriginatingSystemReferenceId(), "OriginatingSystemReferenceID");

		SOAPElement preparer = addChildElements(item, "Preparer");
		addChildElementsWithValue(preparer, requisition.getPasswordAdapter(), "PasswordAdapter");
		addChildElementsWithValue(preparer, requisition.getPreparer(), "UniqueName");

		SOAPElement requester = addChildElements(item, "Requester");
		addChildElementsWithValue(requester, requisition.getPasswordAdapter(), "PasswordAdapter");
		addChildElementsWithValue(requester, requisition.getRequester(), "UniqueName");

		addChildElementsWithValue(item, requisition.getHeaderUniqueName(), "UniqueName");

	}

	private static void addChildDefaultLine(SOAPElement soapBody, Requisition requisition) throws SOAPException {
		SOAPElement defaultLineItem = addChildElements(soapBody, "DefaultLineItem");
		addChildElementsWithValue(defaultLineItem, requisition.getDeliverTo(), "DeliverTo");

		addChildElementsWithValue(defaultLineItem, getValidatedDate(requisition.getNeedByDate()), "NeedBy");
	}

	private static void addLineItem(SOAPElement item, RequisitionItem itemParam) throws SOAPException {
		addChildElementsWithValue(item, itemParam.getBillingAddress(), "BillingAddress", "UniqueName");

		SOAPElement commodityCode = addChildElements(item, "CommodityCode");
		addChildElementsWithValue(commodityCode, itemParam.getCommodityCode(), "UniqueName");

		addChildDescription(item, itemParam);

		addChildElementsWithValue(item, itemParam.getDeliverTo(), "ImportedDeliverToStaging");
		addChildElementsWithValue(item, itemParam.getItemComment(), "ImportedLineCommentStaging");
		addChildElementsWithValue(item, "false", "ImportedLineExternalCommentStaging");

		addChildElementsWithValue(item, getValidatedDate(itemParam.getNeedByDate()), "ImportedNeedByStaging");

		addChildElementsWithValue(item, String.valueOf(itemParam.getNumberInCollection()), "NumberInCollection");
		addChildElementsWithValue(item, String.valueOf(itemParam.getOriginatingSystemLineNumber()),
				"OriginatingSystemLineNumber");
		addChildElementsWithValue(item, String.valueOf(itemParam.getQuantity()), "Quantity");
		addChildElementsWithValue(item, itemParam.getShipTo(), "ShipTo", "UniqueName");
		addChildElementsWithValue(item, itemParam.getSupplierName(), "Supplier", "UniqueName");
	}

	private static String getValidatedDate(Date needByDate) {
		return needByDate != null ? dateFormat.format(needByDate).toString() : null;
	}

	private static void addChildDescription(SOAPElement item, RequisitionItem itemParam) throws SOAPException {
		SOAPElement description = addChildElements(item, "Description");

		SOAPElement commonCommodityCode = addChildElements(description, "CommonCommodityCode");
		addChildElementsWithValue(commonCommodityCode, itemParam.getCommonCommodityCodeDomain(), "Domain");
		addChildElementsWithValue(commonCommodityCode, itemParam.getCommonCommodityCodeName(), "UniqueName");

		addChildElementsWithValue(description, itemParam.getDescription(), "Description");
		addChildElementsWithValue(description, itemParam.getManPartNumber(), "ManPartNumber");

		SOAPElement price = addChildElements(description, "Price");
		addChildElementsWithValue(price, String.valueOf(itemParam.getItemPrice()), "Amount");
		addChildElementsWithValue(price, itemParam.getCurrency(), "Currency", "UniqueName");

		addChildElementsWithValue(description, itemParam.getSupplierPartNumber(), "SupplierPartNumber");
		addChildElementsWithValue(description, itemParam.getUnitOfMeasure(), "UnitOfMeasure", "UniqueName");

	}

	private static SOAPElement addChildElementsWithValue(SOAPElement currentElement, String newElementValue,
			String... newElementsNames) throws SOAPException {
		for (String elementName : newElementsNames) {
			currentElement = currentElement.addChildElement(elementName, NAMESPACE_PREFIX);
		}
		if (newElementValue != null) {
			currentElement = currentElement.addTextNode(newElementValue);
		}
		return currentElement;
	}

	private static SOAPElement addChildElements(SOAPElement currentElement, String... newElementsNames)
			throws SOAPException {
		return addChildElementsWithValue(currentElement, null, newElementsNames);
	}

	private static void handleIllegalParameters(URL url, Requisition requisition, String user, String password) {
		checkAndHandleParameter(url, ERROR_PARAMETER_URL_MESSAGE);
		checkAndHandleParameter(requisition, ERROR_PARAMETER_REQUISITION_MESSAGE);
		checkAndHandleParameter(user, ERROR_PARAMETER_USER_MESSAGE);
		checkAndHandleParameter(password, ERROR_PARAMETER_PASSWOR_MESSAGE);
	}

	private static void checkAndHandleParameter(Object param, String message) {
		if (param == null || (param instanceof String && param.equals(EMPTY_STRING))) {
			LOGGER.error(message);
			throw new IllegalArgumentException(message);
		}
	}

	private static String getAuthorizationHeader(String user, String password) {
		StringBuilder authorization = new StringBuilder();
		authorization.append(BASIC).append(user).append(":").append(password);
		return authorization.toString();
	}

	private static void validateResponse(SOAPMessage soapResponse) throws SOAPException {
		String responseMessage = soapMessageToString(soapResponse);
		if (!isSubmitSuccessful(soapResponse)) {
			String message = MessageFormat.format(ERROR_SUBMIT_FAILED_WITH_RESPONSE, responseMessage);
			throw new SOAPException(message);
		}

		LOGGER.debug(DEBUG_SOAP_RESPONSE_МESSAGE, responseMessage);
	}

	private static boolean isSubmitSuccessful(SOAPMessage soapResponse) {
		try {
			SOAPBody body = soapResponse.getSOAPBody();
			Node responseStatusNode = body.getElementsByTagName(STATUS_STRING).item(0);
			if (responseStatusNode == null) {
				return false;
			}

			String responseStatusValue = responseStatusNode.getTextContent();
			if (responseStatusValue == null) {
				return false;
			}

			ResponseSuccessfulStatus.valueOf(responseStatusValue.toUpperCase());
		} catch (IllegalArgumentException | SOAPException e) {
			return false;
		}

		return true;
	}

	private static String soapMessageToString(SOAPMessage soapMessage) {
		String message = EMPTY_STRING;

		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			soapMessage.writeTo(out);
			message = new String(out.toByteArray());
		} catch (IOException | SOAPException e) {
			LOGGER.error(ERROR_COULD_NOT_READ_SOAP_MESSAGE);
		}

		return message;
	}
}