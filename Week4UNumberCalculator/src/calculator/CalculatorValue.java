package calculator;

import java.math.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * <p> Title: CalculatorValue Class. </p>
 *
 * <p> Description: A component of a JavaFX demonstration application that performs computations </p>
 *
 * <p> Copyright: Lynn Robert Carter © 2019 </p>
 *
 * @author Lynn Robert Carter
 * @author N Krishna Meherwan
 *
 * @version 4.06    2019-03-01 Error Term Rounding
 * @version 4.05 	2019-02-19 Square root implementation
 * @version 4.04    2019-02-18 ERT and MVR implementation in Double Calculator
 * @version 4.03    2019-02-15 MeasuredValueRecognizer implementation.
 * @version 4.02    2019-02-14 Double calculator implementation.
 * @version 4.01	2019-02-08 Minor documentation update.
 * @version 4.00	2017-10-17 The JavaFX-based GUI for the implementation of a calculator.
 *
 */
public class CalculatorValue {

	/**********************************************************************************************

	Attributes

	**********************************************************************************************/

	// These are the major values that define a calculator value
	UNumber measuredValue = new UNumber(0.0);
	UNumber errorTerm = new UNumber(0.0);
	String errorMessage = "";
	String errorMessageET = "";
	String inputFromConsole = "";
	String inputETFromConsole = "";
	int inputETIndexofError = 0;
	int inputIndexofError = 0;

	int sign = 1;
	/**********************************************************************************************

	Constructors

	**********************************************************************************************/

	/*****
	 * This is the default constructor
	 */
	public CalculatorValue() {
	}

	/*****
	 * This constructor creates a calculator value based on a long integer. For future calculators, it
	 * is best to avoid using this constructor.
	 */
	public CalculatorValue(long v) {
		measuredValue = new UNumber(v);
	}

	/*****
	 * This copy constructor creates a duplicate of an already existing calculator value
	 */
	public CalculatorValue(CalculatorValue v) {
		measuredValue = v.measuredValue;
		errorTerm = v.errorTerm;
		errorMessage = v.errorMessage;
	}

	/*****
	 * This constructor creates a calculator value from a string... Due to the nature
	 * of the input, there is a high probability that the input has errors, so the
	 * routine returns the value with the error message value set to empty or the string
	 * of an error message.
	 */
	public CalculatorValue(String s) {

		if (s.substring(0, 1).equals("+") || s.substring(0, 1).equals("-")) {
			if (s.length() == 1) return;
			if (s.substring(0, 1).equals("-")) {
				sign = -1;
			}
			s = s.substring(1, s.length());
			String string = MeasuredValueRecognizer.checkMeasureValue(s);
			if (string.equals("")) {
				if (!s.equals("")) {
					UNumber tempmeasuredValue = convertToUNum(s);
					measuredValue = tempmeasuredValue;
					measuredValue.mpy(new UNumber(sign));
				}
				inputIndexofError = 0;
				inputFromConsole = "";
				errorMessage = "";
			} else {
				errorMessage = MeasuredValueRecognizer.measuredValueErrorMessage;
				inputFromConsole = MeasuredValueRecognizer.measuredValueInput;
				inputIndexofError = MeasuredValueRecognizer.measuredValueIndexofError;
			}
		} else {
			String string = MeasuredValueRecognizer.checkMeasureValue(s);
			if (string.equals("")) {
				UNumber tempmeasuredValue = convertToUNum(s);
				if (tempmeasuredValue.getCharacteristic() > 20) {
					measuredValue = tempmeasuredValue;
				} else {
					measuredValue = new UNumber(s, tempmeasuredValue.getCharacteristic(), true, 20);
				}
				inputIndexofError = 0;
				inputFromConsole = "";
				errorMessage = "";
			} else {
				errorMessage = MeasuredValueRecognizer.measuredValueErrorMessage;
				inputFromConsole = MeasuredValueRecognizer.measuredValueInput;
				inputIndexofError = MeasuredValueRecognizer.measuredValueIndexofError;
			}
		}

	}

	/**********************************************************************************************

	Getters and Setters

	**********************************************************************************************/

	/*****
	 * This is the start of the getters and setters
	 *
	 * Get the error message
	 */
	public String getErrorMessage() {
		return errorMessage + "_" + inputFromConsole + "_" + inputIndexofError;
	}

	/*****
	 * Get the error message for error term.
	 */
	public String getErrorMessageforET() {
		return errorMessageET + "_" + inputETFromConsole + "_" + inputETIndexofError;
	}

	/*****
	 * Set the current value of a calculator value to a specific long integer
	 */
	public void setValue(double v) {
		measuredValue = new UNumber(v);
	}

	/*****
	 * Get the current error term of a calculator value to a specific long integer
	 */
	public void setErrorTerm(String v) {

		String string = ErrorTermRecognizer.checkErrorTerm(v);
		if (string.equals("")) {
			errorTerm = convertToUNum(v);
			// System.out.println("Error TErm" + errorTerm);
			inputETIndexofError = 0;
			inputETFromConsole = "";
			errorMessageET = "";
		} else {
			errorMessageET = ErrorTermRecognizer.errorTermErrorMessage;
			inputETFromConsole = ErrorTermRecognizer.errorTermInput;
			inputETIndexofError = ErrorTermRecognizer.errorTermIndexofError;
		}
	}
	/*****
	 * Set the current value of a calculator error message to a specific string
	 */
	public void setErrorMessage(String m) {
		errorMessage = m;
	}

	/*****
	 * Set the current value of a calculator value to the value of another (copy)
	 */
	public void setValue(CalculatorValue v) {
		measuredValue = v.measuredValue;
		errorMessage = v.errorMessage;
	}

	/*****
	 * Given an string as input, this method converts the representation into UNumber.
	 */

	public UNumber convertToUNum(String str) {

		String inputValue = str;
		String[] tokens = new String[2];
		if (inputValue.contains("e")) {
			tokens = inputValue.split("e");
			inputValue = tokens[0];
		} else if (inputValue.contains("E")) {
			tokens = inputValue.split("E");
			inputValue = tokens[0];
		}

		String digits = "";
		int ndx = 0;
		int exp = 0;

		if (tokens[1] != null) {
			if (tokens[1].charAt(0) == '+' || tokens[1].charAt(0) == '-') {
				exp = Integer.parseInt(tokens[1]);
			} else {
				exp = Integer.parseInt(tokens[1]);
			}
		}

		boolean decimalPointFound = false;
		while (ndx < inputValue.length()) {
			if (decimalPointFound) {
				if (inputValue.charAt(ndx) >= '0' && inputValue.charAt(ndx) <= '9') {
					digits += inputValue.charAt(ndx++);
				} else {
					System.out.println("*** ERROR *** A non-digit found after a decimal point was found.");
					System.exit(0);
				}
			} else {
				if (inputValue.charAt(ndx) == '.') {
					decimalPointFound = true;
					ndx++;
				} else if (inputValue.charAt(ndx) >= '0' && inputValue.charAt(ndx) <= '9') {
					digits += inputValue.charAt(ndx++);
					exp++;
				}

				else {
					System.out.println("*** ERROR *** A non-digit that is also not a decimal point was found.");
					System.exit(0);
				}

			}
		}

		UNumber inputUNumber = new UNumber(digits, exp, true);
		return inputUNumber;
	}

	public void convertMeasuredValue (String x) {
		measuredValue.mpy(new UNumber(x));
		errorTerm.mpy(new UNumber(x));
	}

	/**********************************************************************************************

	The toStringMV() Method

	**********************************************************************************************/

	/*****
	 * This is the enhanced toString method for measured value
	 *
	 * This function changes the notation of the measured value based on predefined threshold values
	 */
	public String toStringMV(BigDecimal num) {
		if ((num.compareTo(new BigDecimal("1E7")) >= 0)  || (num.compareTo(new BigDecimal("1E-5")) <= 0)) {

			NumberFormat formatter = new DecimalFormat("0.0E0");
			formatter.setRoundingMode(RoundingMode.FLOOR);
			formatter.setMinimumFractionDigits(num.precision());
			return formatter.format(num);
		} else {
			return num + "";
		}
	}
	/**********************************************************************************************

	The toStringET() Method

	**********************************************************************************************/

	/*****
	 * This is the enhanced toString method for error term
	 *
	 * This function changes the notation of the error term based on predefined threshold values
	 */
	public String toStringET(BigDecimal num) {

		if ((num.compareTo(new BigDecimal("9E3")) >= 0)  || (num.compareTo(new BigDecimal("1E-3")) <= 0)) {

			NumberFormat formatter = new DecimalFormat("0.0E0");
			formatter.setRoundingMode(RoundingMode.HALF_UP);
			formatter.setMinimumFractionDigits(num.scale());
			return formatter.format(num);
		} else {
			return num + "";
		}

	}

	/**********************************************************************************************

	The toString() Method

	**********************************************************************************************/

	/*****
	 * This is the default toString method
	 *
	 * This routine has been updated to accommodate the rounding of measured value per the given error term
	 */
	public String toString() {

		BigDecimal one = new BigDecimal(1.0);
		BigDecimal ten = new BigDecimal(10.0);

		BigDecimal newMV = new BigDecimal(measuredValue.toDecimalString());
		BigDecimal newET = new BigDecimal(errorTerm.toDecimalString());

		BigDecimal exp = new BigDecimal(1.0);

		if (newET.compareTo(new BigDecimal("0.00000000000000000000")) != 0) {
			if (newET.compareTo(one) >= 0) {
				BigDecimal temp = new BigDecimal(newET.toString());
				BigDecimal temp1 = new BigDecimal(errorTerm.toDecimalString());
				while (temp.compareTo(ten) >= 0) {
					temp = temp.divide(ten);
					exp = exp.multiply(ten);
				}
				temp1 = temp1.divide(exp);
				newET = (temp1.setScale(0, RoundingMode.UP)).multiply(exp);
			} else if (newET.compareTo(one) < 0) {
				int zeros = 0;

				BigDecimal temp2 = new BigDecimal(errorTerm.toString());

				while (temp2.compareTo(one) < 0) {
					temp2 = temp2.multiply(ten);
					zeros += 1;
				}

				newET = (newET.setScale(zeros, RoundingMode.CEILING));

				String text = newET.toString();
				int integerPlaces = text.indexOf('.');
				int decimalPlaces = text.length() - integerPlaces - 1;
				exp = one.scaleByPowerOfTen(decimalPlaces * -1);
			}

			BigDecimal tempres = new BigDecimal(measuredValue.toDecimalString());
			tempres = tempres.divide(exp);
			tempres = tempres.setScale(0, RoundingMode.UP);
			newMV = tempres.multiply(exp);
		} else {
			newMV = new BigDecimal(measuredValue.toDecimalString());
			newMV = newMV.stripTrailingZeros();
		}

		return this.toStringMV(newMV) + " " + this.toStringET(newET);
	}

	/*****
	 * This is the debug toString method
	 *
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String debugToString() {
		return "measuredValue = " + measuredValue + "\nerrorMessage = " + errorMessage + "\n";
	}

	/*****
	 * This is the debug toString method with error term
	 *
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String debugToStringET() {
		return "measuredValue = " + measuredValue + "errorTerm = " + errorTerm + "\nerrorMessage = " + errorMessage + "\n";
	}

	/**********************************************************************************************

	The computation methods

	**********************************************************************************************/


	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 *
	 * Since this is addition and we do not yet support units, we don't recognize any errors.
	 */
	public void add(CalculatorValue v) {

		UNumber value1 = new UNumber(measuredValue);
		UNumber value2 = new UNumber(v.measuredValue);

		UNumber value1ET = new UNumber(errorTerm);
		UNumber value2ET = new UNumber(v.errorTerm);


		UNumber resultMV = new UNumber(value1);
		resultMV.add(value2);
		UNumber resultET = new UNumber(value1ET);
		resultET.add(value2ET);


		measuredValue = resultMV;
		errorTerm = resultET;
		errorMessage = "";
	}

	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 *
	 * Since this is subtraction and we do not yet support units, we don't recognize any errors.
	 */
	public void sub(CalculatorValue v) {

		UNumber value1 = new UNumber(measuredValue);
		UNumber value2 = new UNumber(v.measuredValue);

		UNumber value1ET = new UNumber(errorTerm);
		UNumber value2ET = new UNumber(v.errorTerm);

		UNumber resultMV = new UNumber(value1);
		resultMV.sub(value2);
		UNumber resultET = new UNumber(value1ET);
		resultET.add(value2ET);


		measuredValue = resultMV;
		errorTerm = resultET;
		errorMessage = "";
	}

	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 *
	 * Since this is multiplication and we do not yet support units, we don't recognize any errors.
	 */
	public void mpy(CalculatorValue v) {

		UNumber value1 = new UNumber(measuredValue);
		UNumber value2 = new UNumber(v.measuredValue);

		UNumber value1ET = new UNumber(errorTerm);
		UNumber value2ET = new UNumber(v.errorTerm);

		UNumber resultMV = new UNumber(value1);
		resultMV.mpy(value2);

		UNumber resultET1 = new UNumber(value1ET);
		resultET1.mpy(value2);
		UNumber resultET2 = new UNumber(value2ET);
		resultET2.mpy(value1);
		UNumber resultET = new UNumber(resultET1);
		resultET.add(resultET2);

		measuredValue = resultMV;
		errorTerm = resultET;

		errorMessage = "";
		errorMessageET = "";

	}

	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 *
	 * Since this is division and we do not yet support units, we don't recognize any errors.
	 */
	public void div(CalculatorValue v) {

		if (v.measuredValue.getDouble() == 0.0) {
			errorMessage = "Division not possible by zero";
			return;
		}

		UNumber value1 = new UNumber(measuredValue);
		UNumber value2 = new UNumber(v.measuredValue);

		UNumber value1ET = new UNumber(errorTerm);
		UNumber value2ET = new UNumber(v.errorTerm);

		UNumber resultMV = new UNumber(value1);
		resultMV.div(value2);

		UNumber resultET1 = new UNumber(value1ET);
		resultET1.mpy(value2);
		UNumber resultET2 = new UNumber(value2ET);
		resultET2.mpy(value1);
		UNumber resultET = new UNumber(resultET1);
		resultET.add(resultET2);
		resultET.div(value2);
		resultET.div(value2);

		measuredValue = resultMV;
		errorTerm = resultET;

		errorMessage = "";
		errorMessageET = "";

	}

	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 *
	 * Since this is square root and we do not yet support units, we don't recognize any errors.
	 */
	public void sqrt() {

		// System.out.println(measuredValue);

		if (measuredValue.isNegative()) {
			errorMessage = "SquareRoot not possible for negative numbers";
			return;
		}

		UNumber ValueErrorFraction = new UNumber(errorTerm);
		UNumber Value = new UNumber(measuredValue);

		ValueErrorFraction.div(new UNumber(measuredValue.getDouble()));
		Value.sqrt();
		ValueErrorFraction.mpy(measuredValue);
		ValueErrorFraction.mpy(new UNumber(0.5));

		measuredValue = Value;
		errorTerm = ValueErrorFraction;
		errorMessage = "";
		errorMessageET = "";

	}

	/*
	 * Sets the error term when it is not given.
	 */
	public void setErrorTerm() {

		UNumber FractionPart = new UNumber(0.05);
		UNumber value = new UNumber(measuredValue);
		value.mpy(FractionPart);
		errorTerm = value;
	}
}
