package sale.message.processing;

/**
 * This class processes each product sales and appends them in a report sales.
 * Uses a message parser to parse the incoming messages and collects the product
 * information and prepares a product object for sale processing. Ignores
 * processing of any invalid messages and processes until it reaches the log
 * report limit.
 * 
 * @author kuldeep.verma
 */
public class SalesProcessor {

	private SalesReport salesReport;

	/**
	 * Product adjustment e.g. Add, Subtract
	 */
	private PriceAdjustments priceAdjustments;

	private ProductDetails productDetails;

	public SalesProcessor() {
		setSalesReport(new SalesReport());
	}

	/**
	 * Process notices and appends product information to the relevant product in
	 * the SalesReport
	 * 
	 * @param saleMessage
	 * @return
	 */
	public void processMessage(String saleMessage) {

		SalesMessageConverter messageconverter;

		// convert the given message
		messageconverter = new SalesMessageConverter(saleMessage);

		String productType = messageconverter.getProductType();

		// if product type is empty do nothing.
		if (productType.isEmpty()) {
			return;
		}

		// Returns an existing product else returns a new Product object
		this.productDetails = getSalesReport().getProductDetails(productType);

		// Set the product details from the processed message
		this.productDetails.setProductQuantity(messageconverter.getProductQuantity());
		this.productDetails.setTotalQuantity(messageconverter.getProductQuantity());
		this.productDetails.setProductPrice(messageconverter.getProductPrice());
		this.productDetails.setAdjustmentType(messageconverter.getAdjustmentType());

		// Prepare the product details for adjustment
		this.priceAdjustments = new PriceAdjustments(this.productDetails);

		// Set the total value of the product.
		setProductTotalPrice();

		// Set the sale reports
		getSalesReport().setReports(saleMessage);

		// Update the product with the new details
		getSalesReport().updateProductDetails(productDetails);
	}

	// Set or Append Total product price based on any adjustment if given.
	// Also appends the log for adjustments made.
	private void setProductTotalPrice() {
		double adjustedPrice;
		double productValue;

		if (!this.productDetails.getAdjustmentType().isEmpty()) {
			adjustedPrice = this.priceAdjustments.getAdjustedPrice();
			getSalesReport().setAdjustmentsReport(this.priceAdjustments.adjustmentReport());
			this.productDetails.setTotalPrice(adjustedPrice);
		} else {
			productValue = this.productDetails.calculatePrice(this.productDetails.getProductQuantity(),
					this.productDetails.getProductPrice());
			this.productDetails.addToTotalPrice(productValue);
		}
	}

	/**
	 * @return the Sales Report
	 */
	public SalesReport getSalesReport() {
		return salesReport;
	}

	/**
	 * @param salesReport
	 *            the SalesReport to set
	 */
	public void setSalesReport(SalesReport salesReport) {
		this.salesReport = salesReport;
	}

}