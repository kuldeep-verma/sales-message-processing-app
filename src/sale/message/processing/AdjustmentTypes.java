/**
 * 
 */
package sale.message.processing;

/**
 * @author kuldeep.b.verma
 *
 */
public enum AdjustmentTypes {
	ADD("add"), SUBTRACT("subtract"), MULTIPLY("multiply");
	
	private String adjustmentType;
	
	private AdjustmentTypes(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	/**
	 * @return the adjustmentType
	 */
	public String getAdjustmentType() {
		return adjustmentType;
	}
}
