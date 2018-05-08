package message.processor;

/**
 * This class contains the main program to run the application.
 * Requires a test input file containing sales message details.s
 * 
 * @author kuldeep.verma
 */

import sale.message.processing.SalesProcessor;

import java.io.BufferedReader;
import java.io.FileReader;

public class MessageProcessor {

	public static void main(String[] args) {
		SalesProcessor salesProcessor = new SalesProcessor();

		// Read inputs from test file
		String line;
		try (BufferedReader inputFile = new BufferedReader(new FileReader("salesFile/sales.txt"))) {
			while ((line = inputFile.readLine()) != null) {
				// process message for each sales message
				salesProcessor.processMessage(line);

				// generate report
				salesProcessor.getSalesReport().showReport();
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

}