package ariba.buyer.requisition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class for requisition
 *
 */
public class Requisition {
	private Date needByDate;
	private String shipTo;
	private String businessUnit;
	private String deliverTo;
	private String headerComment;
	private String headerName;
	private String originatingSystem;
	private String originatingSystemId;
	private String passwordAdapter;
	private String preparer;
	private String requester;
	private String headerUniqueName;
	private String namespaceXMLNSvariant;
	private List<RequisitionItem> items = new ArrayList<RequisitionItem>();

	/**
	 * Returns need by date
	 *
	 * @return needByDate
	 */
	public Date getNeedByDate() {
		return needByDate;
	}

	/**
	 * Returns ship to
	 *
	 * @return shipTo
	 */
	public String getShipTo() {
		return shipTo;
	}

	/**
	 * Returns business unit
	 *
	 * @return businessUnit
	 */
	public String getBusinessUnit() {
		return businessUnit;
	}

	/**
	 * Returns deliver to
	 *
	 * @return deliverTo
	 */
	public String getDeliverTo() {
		return deliverTo;
	}

	/**
	 * Returns header comment
	 *
	 * @return headerComment
	 */
	public String getHeaderComment() {
		return headerComment;
	}

	/**
	 * Returns header name
	 *
	 * @return headerName
	 */
	public String getHeaderName() {
		return headerName;
	}

	/**
	 * Returns originating system
	 *
	 * @return originatingSystem
	 */
	public String getOriginatingSystem() {
		return originatingSystem;
	}

	/**
	 * Returns originating system id
	 *
	 * @return originatingSystemId
	 */
	public String getOriginatingSystemId() {
		return originatingSystemId;
	}

	/**
	 * Returns password adapter
	 *
	 * @return passwordAdapter
	 */
	public String getPasswordAdapter() {
		return passwordAdapter;
	}

	/**
	 * Returns preparer
	 *
	 * @return preparer
	 */
	public String getPreparer() {
		return preparer;
	}

	/**
	 * Returns requester
	 *
	 * @return requester
	 */
	public String getRequester() {
		return requester;
	}

	/**
	 * Returns header unique name
	 *
	 * @return headerUniqueName
	 */
	public String getHeaderUniqueName() {
		return headerUniqueName;
	}

	/**
	 * Returns namespace XMLNS variant
	 *
	 * @return namespaceXMLNSvariant
	 */
	public String getNamespaceXMLNSvariant() {
		return namespaceXMLNSvariant;
	}

	/**
	 * Returns items
	 *
	 * @return items
	 */
	public List<RequisitionItem> getItems() {
		return items;
	}

	/**
	 * Sets need by date
	 *
	 * @param needByDate
	 */
	public Requisition needByDate(Date needByDate) {
		this.needByDate = needByDate;
		return this;
	}

	/**
	 * Sets ship to
	 *
	 * @param shipTo
	 */
	public Requisition shipTo(String shipTo) {
		this.shipTo = shipTo;
		return this;
	}

	/**
	 * Sets business unit
	 *
	 * @param businessUnit
	 */
	public Requisition businessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
		return this;
	}

	/**
	 * Sets deliver to
	 *
	 * @param deliverTo
	 */
	public Requisition deliverTo(String deliverTo) {
		this.deliverTo = deliverTo;
		return this;
	}

	/**
	 * Sets header comment
	 *
	 * @param headerComment
	 */
	public Requisition headerComment(String headerComment) {
		this.headerComment = headerComment;
		return this;
	}

	/**
	 * Sets header name
	 *
	 * @param headerName
	 */
	public Requisition headerName(String headerName) {
		this.headerName = headerName;
		return this;
	}

	/**
	 * Sets originating system
	 *
	 * @param originatingSystem
	 */
	public Requisition originatingSystem(String originatingSystem) {
		this.originatingSystem = originatingSystem;
		return this;
	}

	/**
	 * Sets originating system id
	 *
	 * @param originatingSystemId
	 */
	public Requisition originatingSystemId(String originatingSystemId) {
		this.originatingSystemId = originatingSystemId;
		return this;
	}

	/**
	 * Sets password adapter
	 *
	 * @param passwordAdapter
	 */
	public Requisition passwordAdapter(String passwordAdapter) {
		this.passwordAdapter = passwordAdapter;
		return this;
	}

	/**
	 * Sets preparer
	 *
	 * @param preparer
	 */
	public Requisition preparer(String preparer) {
		this.preparer = preparer;
		return this;
	}

	/**
	 * Sets requester
	 *
	 * @param requester
	 */
	public Requisition requester(String requester) {
		this.requester = requester;
		return this;
	}

	/**
	 * Sets header unique name
	 *
	 * @param headerUniqueName
	 */
	public Requisition headerUniqueName(String headerUniqueName) {
		this.headerUniqueName = headerUniqueName;
		return this;
	}

	/**
	 * Sets namespace XMLNS variant
	 *
	 * @param namespaceXMLNSvariant
	 */
	public Requisition namespaceXMLNSvariant(String namespaceXMLNSvariant) {
		this.namespaceXMLNSvariant = namespaceXMLNSvariant;
		return this;
	}

	/**
	 * Sets items
	 *
	 * @param items
	 */
	public Requisition items(List<RequisitionItem> items) {
		this.items = items;
		return this;
	}

}