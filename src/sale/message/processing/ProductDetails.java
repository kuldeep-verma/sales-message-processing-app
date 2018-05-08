package sale.message.processing;

/**
 * This class contains the product details such as it's type, price quantity,
 * and stores total price value and quantity of a product. Also stores the last
 * performed adjustment operation such as Add, Subtract and Multiply.
 * 
 * @author kuldeep.verma
 */
public class ProductDetails {

	private int totalQuantity;

	private double totalPrice;

	private double productPrice;

	private int productQuantity;

	// adjustmentType e.g., Add or Subtract or Multiply
	private String adjustmentType;

	private String productType;

	public ProductDetails(String type) {
		this.totalPrice = 0.0;
		this.totalQuantity = 0;
		this.productType = type;
		this.adjustmentType = null;
	}

	public double calculatePrice(int productQuantity, double productPrice) {
		return productQuantity * productPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void addToTotalPrice(double productPrice) {
		this.totalPrice += productPrice;
	}

	public void setTotalQuantity(int quantity) {
		this.totalQuantity += quantity;
	}

	public int getTotalQuantity() {
		return this.totalQuantity;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String type) {
		this.productType = type;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getAdjustmentType() {
		return adjustmentType;
	}

	// Set the product adjustmentOperator to the provided operator e.g. Add,
	// Subtract, or Multiply.
	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

}
