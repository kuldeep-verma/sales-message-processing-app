package sale.message.processing;

/**
 * Parses a sales message and obtains product details.
 * 
 * @author kuldeep.verma
 */

public class SalesMessageConverter {

	private String productType;

	private double productPrice;

	private int productQuantity;

	private String adjustmentType;

	public SalesMessageConverter(String message) {
		this.productType = "";
		this.productPrice = 0.0;
		this.productQuantity = 0;
		this.adjustmentType = "";
		convertMessage(message);
	}

	/**
	 * @param message
	 *            to be parsed
	 * @return if message is not valid
	 */
	private void convertMessage(String message) {
		if (message == null || message.isEmpty()) {
			return;
		}
		// groups all white spaces as a delimiter
		String[] msgArr = message.trim().split(Constants.WHITE_SPACE_DELIMITER);
		String firstWord = msgArr[0];
		if (msgArr.length == 3 && msgArr[1].contains(Constants.AT)) {
			convertMsgType1(msgArr);
		} else if (firstWord.matches(Constants.NUMBER_REGEX)) { // starts with number
			convertMsgType2(msgArr);
		} else if (firstWord.matches(Constants.ADJUSTMENT_TYPES)) {
			convertMsgType3(msgArr);
		} else {
			Constants.toPrint("Invalid sales message");
		}
	}

	// apple at 10p
	private void convertMsgType1(String[] messageArray) {
		if (messageArray.length > 3 || messageArray.length < 3) {
			return;
		}
		productType = adjustType(messageArray[0]);
		productPrice = parsePrice(messageArray[2]);
		productQuantity = 1; // Will always be 1 for message type 1
	}

	// 20 sales of apples at 10p each
	private void convertMsgType2(String[] messageArray) {
		if (messageArray.length > 7 || messageArray.length < 7) {
			return;
		}
		productType = adjustType(messageArray[3]);
		productPrice = parsePrice(messageArray[5]);
		productQuantity = Integer.parseInt(messageArray[0]);
	}

	// Add 20p apples i.e.change in price
	private void convertMsgType3(String[] messageArray) {
		if (messageArray.length > 3 || messageArray.length < 3) {
			return;
		}
		adjustmentType = messageArray[0]; // Add, Subtract etc.
		productType = adjustType(messageArray[2]);
		productQuantity = 0;
		productPrice = parsePrice(messageArray[1]);
	}

	/*
	 * To match product name like Apple and Apples
	 */
	public String adjustType(String rawType) {
		String parsedType = "";
		String typeWithoutLastChar = rawType.substring(0, rawType.length() - 1);

		if (rawType.endsWith("o")) {
			parsedType = String.format("%soes", typeWithoutLastChar);
		} else if (rawType.endsWith("y")) {
			parsedType = String.format("%sies", typeWithoutLastChar);
		} else if (rawType.endsWith("h")) {
			parsedType = String.format("%shes", typeWithoutLastChar);
		} else if (!rawType.endsWith("s")) {
			parsedType = String.format("%ss", rawType);
		} else {
			parsedType = String.format("%s", rawType);
		}
		return parsedType.toLowerCase();
	}

	/*
	 * get price only
	 */
	public double parsePrice(String rawPrice) {
		double price = Double.parseDouble(rawPrice.replaceAll("p", ""));
		if (!rawPrice.contains(".")) {
			price = (Double) price / 100d;
		}
		return price;
	}

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType
	 *            the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the productPrice
	 */
	public double getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice
	 *            the productPrice to set
	 */
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @return the productQuantity
	 */
	public int getProductQuantity() {
		return productQuantity;
	}

	/**
	 * @param productQuantity
	 *            the productQuantity to set
	 */
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	/**
	 * @return the adjustmentType
	 */
	public String getAdjustmentType() {
		return adjustmentType;
	}

	/**
	 * @param adjustmentType
	 *            the adjustmentType to set
	 */
	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}
}
