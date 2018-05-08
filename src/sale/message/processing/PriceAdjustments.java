package sale.message.processing;

/**
 * Performs Add, Subtract and Multiply adjustment operations for a given
 * product.
 * 
 * @author kuldeep.verma
 */
public class PriceAdjustments {

	private double adjustedPrice;

	private ProductDetails product;

	public PriceAdjustments(ProductDetails product) {
		this.product = product;
		this.adjustedPrice = 0.0;
	}

	/**
	 * Call method based upon adjustment type
	 * 
	 * @returns adjusted price value
	 */
	public double getAdjustedPrice() {
		switch (product.getAdjustmentType().toLowerCase()) {
		case "add":
			this.adjustedPrice = addPrice();
			break;
		case "subtract":
			this.adjustedPrice = subtractPrice();
			break;
		case "multiply":
			this.adjustedPrice = multiplyPrice();
			break;
		default:
			break;
		}
		return this.adjustedPrice;
	}

	// Adds product total price with the requested price value.
	public double addPrice() {
		return this.product.getTotalPrice() + (this.product.getTotalQuantity() * this.product.getProductPrice());
	}

	// Subtracts product total price with the requested price value.
	public double subtractPrice() {
		return this.product.getTotalPrice() - (this.product.getTotalQuantity() * this.product.getProductPrice());
	}

	// Multiplies product total price and quantity with the requested price and
	// appends to existing total value.
	public double multiplyPrice() {
		return this.product.getTotalPrice() + (this.product.getTotalPrice() * this.product.getProductPrice())
				+ (this.product.getTotalQuantity() * this.product.getProductPrice());
	}

	// Performed Add 20p to 21 apples and price adjusted from 2.10p to 6.30p
	public String adjustmentReport() {
		String adjustmentReport = String.format("Performed %s %.2fp to %d %s and price adjusted from %.2fp to %.2fp",
				this.product.getAdjustmentType(), this.product.getProductPrice(), this.product.getTotalQuantity(),
				this.product.getProductType(), this.product.getTotalPrice(), this.adjustedPrice);
		return adjustmentReport;
	}

}
