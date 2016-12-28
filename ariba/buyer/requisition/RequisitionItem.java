package ariba.buyer.requisition;

import java.util.Date;

/**
 * Class for requisition's items
 *
 */
public class RequisitionItem {
	private Date needByDate;
	private String shipTo;
	private String deliverTo;
	private String commodityCode;
	private String billingAddress;
	private String manPartNumber;
	private String description;
	private String currency;
	private String supplierPartNumber;
	private String unitOfMeasure;
	private String itemComment;
	private String supplierName;
	private String supplierContact;
	private String supplierSetId;
	private String supplierLocation;
	private Double quantity;
	private Double itemPrice;
	private Long numberInCollection;
	private Long originatingSystemLineNumber;
	private String commonCommodityCodeDomain;
	private String commonCommodityCodeName;

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
	 * Returns deliver to
	 *
	 * @return deliverTo
	 */
	public String getDeliverTo() {
		return deliverTo;
	}

	/**
	 * Returns commodity code
	 *
	 * @return commodityCode
	 */
	public String getCommodityCode() {
		return commodityCode;
	}

	/**
	 * Returns billing address
	 *
	 * @return billingAddress
	 */
	public String getBillingAddress() {
		return billingAddress;
	}

	/**
	 * Returns man part number
	 *
	 * @return manPartNumber
	 */
	public String getManPartNumber() {
		return manPartNumber;
	}

	/**
	 * Returns description
	 *
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns currency
	 *
	 * @return currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Returns supplier part number
	 *
	 * @return supplierPartNumber
	 */
	public String getSupplierPartNumber() {
		return supplierPartNumber;
	}

	/**
	 * Returns unit of measure
	 *
	 * @return unitOfMeasure
	 */
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	/**
	 * Returns item comment
	 *
	 * @return itemComment
	 */
	public String getItemComment() {
		return itemComment;
	}

	/**
	 * Returns supplier name
	 *
	 * @return supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * Returns supplier contact
	 *
	 * @return supplierContact
	 */
	public String getSupplierContact() {
		return supplierContact;
	}

	/**
	 * Returns supplier set id
	 *
	 * @return supplierSetId
	 */
	public String getSupplierSetId() {
		return supplierSetId;
	}

	/**
	 * Returns supplier location
	 *
	 * @return supplierLocation
	 */
	public String getSupplierLocation() {
		return supplierLocation;
	}

	/**
	 * Returns quantity
	 *
	 * @return quantity
	 */
	public Double getQuantity() {
		return quantity;
	}

	/**
	 * Returns item price
	 *
	 * @return itemPrice
	 */
	public Double getItemPrice() {
		return itemPrice;
	}

	/**
	 * Returns number in collection
	 *
	 * @return numberInCollection
	 */
	public Long getNumberInCollection() {
		return numberInCollection;
	}

	/**
	 * Returns originating system line number
	 *
	 * @return originatingSystemLineNumber
	 */
	public Long getOriginatingSystemLineNumber() {
		return originatingSystemLineNumber;
	}

	/**
	 * Returns common commodity code domain
	 *
	 * @return commonCommodityCodeDomain
	 */
	public String getCommonCommodityCodeDomain() {
		return commonCommodityCodeDomain;
	}

	/**
	 * Returns common commodity code name
	 *
	 * @return commonCommodityCodeName
	 */
	public String getCommonCommodityCodeName() {
		return commonCommodityCodeName;
	}

	/**
	 * Sets need by date
	 *
	 * @param needByDate
	 */
	public RequisitionItem needByDate(Date needByDate) {
		this.needByDate = needByDate;
		return this;
	}

	/**
	 * Sets ship to
	 *
	 * @param shipTo
	 */
	public RequisitionItem shipTo(String shipTo) {
		this.shipTo = shipTo;
		return this;
	}

	/**
	 * Sets deliver to
	 *
	 * @param deliverTo
	 */
	public RequisitionItem deliverTo(String deliverTo) {
		this.deliverTo = deliverTo;
		return this;
	}

	/**
	 * Sets commodity code
	 *
	 * @param commodityCode
	 */
	public RequisitionItem commodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
		return this;
	}

	/**
	 * Sets billing address
	 *
	 * @param billingAddress
	 */
	public RequisitionItem billingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
		return this;
	}

	/**
	 * Sets man part number
	 *
	 * @param manPartNumber
	 */
	public RequisitionItem manPartNumber(String manPartNumber) {
		this.manPartNumber = manPartNumber;
		return this;
	}

	/**
	 * Sets description
	 *
	 * @param description
	 */
	public RequisitionItem description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Sets currency
	 *
	 * @param currency
	 */
	public RequisitionItem currency(String currency) {
		this.currency = currency;
		return this;
	}

	/**
	 * Sets supplier part number
	 *
	 * @param supplierPartNumber
	 */
	public RequisitionItem supplierPartNumber(String supplierPartNumber) {
		this.supplierPartNumber = supplierPartNumber;
		return this;
	}

	/**
	 * Sets unit of measure
	 *
	 * @param unitOfMeasure
	 */
	public RequisitionItem unitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
		return this;
	}

	/**
	 * Sets item comment
	 *
	 * @param itemComment
	 */
	public RequisitionItem itemComment(String itemComment) {
		this.itemComment = itemComment;
		return this;
	}

	/**
	 * Sets supplier name
	 *
	 * @param supplierName
	 */
	public RequisitionItem supplierName(String supplierName) {
		this.supplierName = supplierName;
		return this;
	}

	/**
	 * Sets supplier contact
	 *
	 * @param supplierContact
	 */
	public RequisitionItem supplierContact(String supplierContact) {
		this.supplierContact = supplierContact;
		return this;
	}

	/**
	 * Sets supplier set id
	 *
	 * @param supplierSetId
	 */
	public RequisitionItem supplierSetId(String supplierSetId) {
		this.supplierSetId = supplierSetId;
		return this;
	}

	/**
	 * Sets supplier location
	 *
	 * @param supplierLocation
	 */
	public RequisitionItem supplierLocation(String supplierLocation) {
		this.supplierLocation = supplierLocation;
		return this;
	}

	/**
	 * Sets quantity
	 *
	 * @param quantity
	 */
	public RequisitionItem quantity(Double quantity) {
		this.quantity = quantity;
		return this;
	}

	/**
	 * Sets item price
	 *
	 * @param itemPrice
	 */
	public RequisitionItem itemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
		return this;
	}

	/**
	 * Sets number in collection
	 *
	 * @param numberInCollection
	 */
	public RequisitionItem numberInCollection(Long numberInCollection) {
		this.numberInCollection = numberInCollection;
		return this;
	}

	/**
	 * Sets originating system line number
	 *
	 * @param originatingSystemLineNumber
	 */
	public RequisitionItem originatingSystemLineNumber(Long originatingSystemLineNumber) {
		this.originatingSystemLineNumber = originatingSystemLineNumber;
		return this;
	}

	/**
	 * Sets common commodity code domain
	 *
	 * @param commonCommodityCodeDomain
	 */
	public RequisitionItem commonCommodityCodeDomain(String commonCommodityCodeDomain) {
		this.commonCommodityCodeDomain = commonCommodityCodeDomain;
		return this;
	}

	/**
	 * Sets common commodity code name
	 *
	 * @param commonCommodityCodeName
	 */
	public RequisitionItem commonCommodityCodeName(String commonCommodityCodeName) {
		this.commonCommodityCodeName = commonCommodityCodeName;
		return this;
	}

}