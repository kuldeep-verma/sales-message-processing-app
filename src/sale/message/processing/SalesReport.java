package sale.message.processing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Stores information about the sales message and any logs any sale adjustments
 * made to products.
 * 
 * Showing report on every 10th record and stops accepting any message after 50
 * message and then showing adjustment report.
 *
 * @author kuldeep.verma
 */
public class SalesReport {

	private HashMap<String, ProductDetails> allMessages = new HashMap<>();

	private double totalSalesValue;

	private List<String> report;

	private List<String> adjustmentsReport;

	public SalesReport() {
		this.report = new ArrayList<>();
		this.adjustmentsReport = new ArrayList<>();
		this.totalSalesValue = 0.0;
	}

	public ProductDetails getProductDetails(String type) {
		return allMessages.getOrDefault(type, new ProductDetails(type));
	}

	public void updateProductDetails(ProductDetails product) {
		allMessages.put(product.getProductType(), product);
	}

	public List<String> getReports() {
		return report;
	}

	public void setReports(String report) {
		this.report.add(report);
	}

	public List<String> getAdjustmentsReport() {
		return adjustmentsReport;
	}

	public void setAdjustmentsReport(String adjustmentReport) {
		this.adjustmentsReport.add(adjustmentReport);
	}

	public double getTotalSalesValue() {
		return totalSalesValue;
	}

	public void appendTotalSalesValue(double productTotalPrice) {
		totalSalesValue += productTotalPrice;
	}

	public void setTotalSalesValue(double productTotalPrice) {
		totalSalesValue = productTotalPrice;
	}

	/*
	 * Report outputs sales information to console after every 10th row. Displays in
	 * a table formatted structure and stops execution of the application after 50th
	 * message.
	 */
	public void showReport() {

		// Report after 10th message and not at the beginning.
		if (!report.isEmpty() && (report.size() % 10) == 0) {
			setTotalSalesValue(0.0);
			Constants.toPrint(Constants.REPORT_10TH_MESSAGES);
			Constants.toPrint(Constants.REPORT_LINE_1);
			Constants.toPrint(Constants.REPORT_LINE_SEPARATOR);
			allMessages.forEach((k, v) -> formatReports(v));
			Constants.toPrint(Constants.REPORT_LINE_SEPARATOR);
			Constants.toPrint(String.format(Constants.TOTAL_SALES_FORMAT, Constants.TOTAL_SALES, getTotalSalesValue()));
			Constants.toPrint(Constants.REPORT_LINE_SEPARATOR);
			Constants.toPrint(Constants.END_OF_REPORT);
			try {
				// pause for 2 seconds
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Constants.toPrint(e.getMessage());
				Thread.currentThread().interrupt();
			}
		}

		// Report and stop execution after 50th message
		if ((report.size() % 50) == 0 && !report.isEmpty()) {
			Constants.toPrint(Constants.REPORT_50TH_MESSAGE);
			// Display all the adjustment reports so far recorded.
			getAdjustmentsReport().forEach(System.out::println);
			System.exit(1);
		}
	}

	// Format the report with right padding structure. populates product details on
	// each line.
	public void formatReports(ProductDetails product) {
		String item = String.format(Constants.REPORT_PRODUCT_FORMAT, product.getProductType(),
				product.getTotalQuantity(), product.getTotalPrice());
		appendTotalSalesValue(product.getTotalPrice());
		Constants.toPrint(item);
	}

}
