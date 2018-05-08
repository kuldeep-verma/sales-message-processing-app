/**
 * 
 */
package sale.message.processing;

/**
 * This class contains all the constants used in application
 * 
 * @author kuldeep.b.verma
 *
 */
public class Constants {
	public static final String REPORT_LINE_1 = "|Product           |Quantity   |Value      |";
	public static final String REPORT_LINE_SEPARATOR = "-------------------------------------------";
	public static final String TOTAL_SALES_FORMAT = "|%-30s|%-11.2f|";
	public static final String REPORT_10TH_MESSAGES = "---Report after processing 10 messages---";
	public static final String TOTAL_SALES = "Total Sales";
	public static final String REPORT_PRODUCT_FORMAT = "|%-18s|%-11d|%-11.2f|";
	public static final String REPORT_50TH_MESSAGE = "System processed 50 messages and stop accepting new messages. Adjustment report is as below:\n";
	public static final String END_OF_REPORT = "End\n\n";
	public static final String AT = "at";
	public static final String WHITE_SPACE_DELIMITER = "\\s+";
	public static final String NUMBER_REGEX = "^\\d+";
	public static final String ADJUSTMENT_TYPES = "Add|Subtract|Multiply";

	public static void toPrint(String content) {
		System.out.println(content);
	}
}
