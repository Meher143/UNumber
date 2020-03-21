package calculator;


/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with 
 * String objects and passes work to other classes to deal with all other aspects of the 
 * computation.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2019 </p>
 * 
 * @author Lynn Robert Carter
 * @author Ramya Madhulika
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
/****
 * This class exclusively deals with the units of the operands and returns two things,
 * factor by which an operand must be multiplied with in order to have a standardized mode of arithmetics,
 * and the other is a boolean saying whether given operands are compatible to perform expected arithmetics.
 * */
public class Unit {
	
	int unitSpecifier;
	String unitName;
	private String metricMultiplier = "1";
	int resultUS;
	
	String[] unitList = {"no-unit-selected",
			"meter", "kilometer", "feet",
			"seconds", "minutes", "hours", 
			"grams", "pounds", "kilograms",
			"mps", "kmph",
			"mps2", "kmph2",
			"Newton", "Dyne",
			"m3s-2",
			"m2","km2","feet2",
			"kg.m.s-1", "g.cm.s-1",
			"m3", "km3", "feet3",
			"hr2", "sec2"
			};
	
	public Unit() {
		unitSpecifier = 0;
		unitName = unitList[0];
	}
	
	public Unit (int unitSpecifier) {
		this.unitSpecifier = unitSpecifier;
		this.unitName = unitList[unitSpecifier];
	}
	
	/****
	 * Setters and getters
	 * */
	int getUnitSpecifier() {
		return this.unitSpecifier;
	}
	
	String getUnit() {
		return unitList[unitSpecifier];
	}
	
	void setUnitSpecifier(int newUnitSpecifier) {
		this.unitSpecifier = newUnitSpecifier;
	}
	
	/**** 
	 * setters
	 * */
	public String getMetricMultiplier() {
		return this.metricMultiplier;
	}
	
	/****
	 * toString()
	 * */
	public String toString() {
		return unitName;
	}
	
	/****
	 * check if valid gives a boolean telling the units can be operated directly or not
	 * */
	boolean checkIfValidForAddition(Unit that) {

		switch(this.unitSpecifier) {
		case 1:
			switch(that.unitSpecifier) {
			case 1: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 1; return true;
			case 2: this.metricMultiplier = "1.0"; that.metricMultiplier = "1000.0"; resultUS = 1; return true;
			case 3: this.metricMultiplier = "3.28084"; that.metricMultiplier = "1.0"; resultUS = 3; return true;
			default: return false;
			}
		case 2:
			switch(that.unitSpecifier) {
			case 1: this.metricMultiplier = "1000.0"; that.metricMultiplier = "1.0"; resultUS = 1; return true;
			case 2: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 2; return true;
			case 3: this.metricMultiplier = "3280.84"; that.metricMultiplier = "1.0"; resultUS = 3; return true;
			default: return false;
			}
		case 3:
			switch(that.unitSpecifier) {
			case 1: this.metricMultiplier = "1.0"; that.metricMultiplier = "3.2804"; resultUS = 3; return true;
			case 2: this.metricMultiplier = "1.0"; that.metricMultiplier = "3280.84"; resultUS = 3; return true;
			case 3: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 3; return true;
			default: return false;
			}
		case 4:
			switch(that.unitSpecifier) {
			case 4: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 4; return true;
			case 5: this.metricMultiplier = "1.0"; that.metricMultiplier = "60.0"; resultUS = 4; return true;
			case 6: this.metricMultiplier = "1.0"; that.metricMultiplier = "3600.0"; resultUS = 4; return true;
			default: return false;
			}
		case 5:
			switch(that.unitSpecifier) {
			case 4: this.metricMultiplier = "60.0"; that.metricMultiplier = "1.0"; resultUS = 4; return true;
			case 5: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 5; return true;
			case 6: this.metricMultiplier = "1.0"; that.metricMultiplier = "60.0"; resultUS = 5; return true;
			default: return false;
			}
		case 6:
			switch(that.unitSpecifier) {
			case 4: this.metricMultiplier = "3600.0"; that.metricMultiplier = "1.0"; resultUS = 4; return true;
			case 5: this.metricMultiplier = "60.0"; that.metricMultiplier = "1.0"; resultUS = 5; return true;
			case 6: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 6; return true;
			default: return false;
			}
		case 7:
			switch(that.unitSpecifier) {
			case 7: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 7; return true;
			case 8: this.metricMultiplier = "1.0"; that.metricMultiplier = "453.592"; resultUS = 7; return true;
			case 9: this.metricMultiplier = "1.0"; that.metricMultiplier = "1000.0"; resultUS = 7; return true;
			default: return false;
			}
		case 8:
			switch(that.unitSpecifier) {
			case 7: this.metricMultiplier = "453.592"; that.metricMultiplier = "1.0"; resultUS = 7; return true;
			case 8: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 8; return true;
			case 9: this.metricMultiplier = "1.0"; that.metricMultiplier = "2.20462"; resultUS = 8; return true;
			default: return false;
			}
		case 9:
			switch(that.unitSpecifier) {
			case 7: this.metricMultiplier = "1000.0"; that.metricMultiplier = "1.0"; resultUS = 7; return true;
			case 8: this.metricMultiplier = "2.20462"; that.metricMultiplier = "1.0"; resultUS = 8; return true;
			case 9: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 9; return true;
			default: return false;
			}
		case 10:
			switch(that.unitSpecifier) {
			case 10: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 10; return true;
			case 11: this.metricMultiplier = "1.0"; that.metricMultiplier = "0.27777777777"; resultUS = 10; return true;
			default: return false;
			}
		case 11:
			switch(that.unitSpecifier) {
			case 10: this.metricMultiplier = "0.27777777777"; that.metricMultiplier = "1.0"; resultUS=10; return true;
			case 11: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 11; return true;
			default: return false;
			}
		case 12:
			switch(that.unitSpecifier) {
			case 12: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 12; return true;
			case 13: this.metricMultiplier = "1.0"; that.metricMultiplier = "0.00007716049"; resultUS = 12; return true;
			default: return false;
			}
		case 13:
			switch(that.unitSpecifier) {
			case 12: this.metricMultiplier = "0.00007716049"; that.metricMultiplier = "1.0"; resultUS=12; return true;
			case 13: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 13; return true;
			default: return false;
			}
		case 14:
			switch(that.unitSpecifier) {
			case 14: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 14; return true;
			case 15: this.metricMultiplier = "100000"; that.metricMultiplier = "1.0"; resultUS = 15; return true;
			default: return false;
			}
		case 15:
			switch(that.unitSpecifier) {
			case 14: this.metricMultiplier = "1.0"; that.metricMultiplier = "100000"; resultUS=15; return true;
			case 15: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 15; return true;
			default: return false;
			}
		case 16:
			switch(that.unitSpecifier) {
			case 16: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 16;return true;
			default: return false;
			}
		default: return false;
		}
	}
	
	boolean checkIfValidForSubtraction(Unit that) {
		return checkIfValidForAddition(that);
	}
	
	boolean checkIfValidForMultiplication(Unit that) {
		switch(this.unitSpecifier) {
		case 0: 
			switch(that.unitSpecifier) {
			case 1: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 1; return true;
			case 2: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 2; return true;
			case 3: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 3; return true;
			case 4: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 4; return true;
			case 5: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 5; return true;
			case 6: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 6; return true;
			case 7: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 7; return true;
			case 8: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 8; return true;
			case 9: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 9; return true;
			case 10: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 10; return true;
			case 11: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 11; return true;
			case 12: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 12; return true;
			case 13: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 13; return true;
			case 14: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 14; return true;
			case 15: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 15; return true;
			case 16: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 16; return true;
			default: return false;
			}
		case 1:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 1; return true;
			case 1: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0"; resultUS = 17; return true;
			case 2: this.metricMultiplier = "1.0"; that.metricMultiplier = "1000.0"; resultUS = 17; return true;
			case 3: this.metricMultiplier = "3.28084"; that.metricMultiplier = "1.0"; resultUS = 19; return true;
			case 17: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 22; return true;
			case 18: this.metricMultiplier = "1.0"; that.metricMultiplier = "1000000.0"; resultUS = 22; return true;
			case 19: this.metricMultiplier = "10.764"; that.metricMultiplier = "1.0"; resultUS = 24; return true;
			default: return false;
			}
		case 2:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 2; return true;
			case 1: this.metricMultiplier = "1000.0" ; that.metricMultiplier = "1.0"; resultUS = 17; return true;
			case 2: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 18; return true;
			case 3: this.metricMultiplier = "3280.84"; that.metricMultiplier = "1.0"; resultUS = 19; return true;
			case 17: this.metricMultiplier = "1000000.0"; that.metricMultiplier = "1.0"; resultUS = 22; return true;
			case 18: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 23; return true;
			case 19: this.metricMultiplier = "1.076e+7"; that.metricMultiplier = "1.0"; resultUS = 24; return true;
			default: return false;
			}
		case 3:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 3; return true;
			case 1: this.metricMultiplier = "1.0" ; that.metricMultiplier = "3.28084"; resultUS = 19; return true;
			case 2: this.metricMultiplier = "1.0"; that.metricMultiplier = "3280.84"; resultUS = 19; return true;
			case 3: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 19; return true;
			case 17: this.metricMultiplier = "1.0"; that.metricMultiplier = "10.764"; resultUS = 24; return true;
			case 18: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.076e+7"; resultUS = 24; return true;
			case 19: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 24; return true;
			default: return false;
			}
		case 4:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 4; return true;
			case 10: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 1; return true;
			case 11: this.metricMultiplier = "0.00027777777"; that.metricMultiplier = "1.0"; resultUS = 2; return true;
			case 12: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 10; return true;
			case 13: this.metricMultiplier = "0.00027777777"; that.metricMultiplier = "1.0"; resultUS = 11; return true;
			case 14: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 20; return true;
			case 15: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 21; return true;
			default: return false;
			}
		case 5:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 5; return true;
			case 10: this.metricMultiplier = "60.0"; that.metricMultiplier = "1.0"; resultUS = 1; return true;
			case 11: this.metricMultiplier = "0.01666666666"; that.metricMultiplier = "1.0"; resultUS = 2; return true;
			case 12: this.metricMultiplier = "60.0"; that.metricMultiplier = "1.0"; resultUS = 10; return true;
			case 13: this.metricMultiplier = "0.01666666666"; that.metricMultiplier = "1.0"; resultUS = 11; return true;
			case 14: this.metricMultiplier = "60.0"; that.metricMultiplier = "1.0"; resultUS = 20; return true;
			case 15: this.metricMultiplier = "60.0"; that.metricMultiplier = "1.0"; resultUS = 21; return true;
			default: return false;
			}
		case 6:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 6; return true;
			case 10: this.metricMultiplier = "3600.0"; that.metricMultiplier = "1.0"; resultUS = 1; return true;
			case 11: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 2; return true;
			case 12: this.metricMultiplier = "3600.0"; that.metricMultiplier = "1.0"; resultUS = 10; return true;
			case 13: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 11; return true;
			case 14: this.metricMultiplier = "3600.0"; that.metricMultiplier = "1.0"; resultUS = 20; return true;
			case 15: this.metricMultiplier = "3600.0"; that.metricMultiplier = "1.0"; resultUS = 21; return true;
			default: return false;
			}
		case 7:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 7; return true;
			default: return false;
			}
		case 8:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 8; return true;
			default: return false;
			}
		case 9:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 9; return true;
			default: return false;
			}
		case 10:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 10; return true;
			case 4: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 1; return true;
			case 5: this.metricMultiplier = "1.0"; that.metricMultiplier = "60.0"; resultUS = 1; return true;
			case 6: this.metricMultiplier = "1.0"; that.metricMultiplier = "3600.0"; resultUS = 1; return true;
			default: return false;
			}
		case 11:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 11; return true;
			case 4: this.metricMultiplier = "1.0"; that.metricMultiplier = "0.00027777777"; resultUS = 2; return true;
			case 5: this.metricMultiplier = "1.0"; that.metricMultiplier = "0.01666666666"; resultUS = 2; return true;
			case 6: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 2; return true;
			default: return false;
			}
		case 12:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 12; return true;
			case 4: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 10; return true;
			case 5: this.metricMultiplier = "1.0"; that.metricMultiplier = "60.0"; resultUS = 10; return true;
			case 6: this.metricMultiplier = "1.0"; that.metricMultiplier = "3600.0"; resultUS = 10; return true;
			default: return false;
			}
		case 13:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 13; return true;
			case 4: this.metricMultiplier = "1.0"; that.metricMultiplier = "0.00027777777"; resultUS = 11; return true;
			case 5: this.metricMultiplier = "1.0"; that.metricMultiplier = "0.01666666666"; resultUS = 11; return true;
			case 6: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 11; return true;
			default: return false;
			}
		case 14:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 14; return true;
			case 4: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 20; return true;
			case 5: this.metricMultiplier = "1.0"; that.metricMultiplier = "60.0"; resultUS = 20; return true;
			case 6: this.metricMultiplier = "1.0"; that.metricMultiplier = "3600.0"; resultUS = 20; return true;
			default: return false;
			}
		case 15:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 15; return true;
			case 4: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 21; return true;
			case 5: this.metricMultiplier = "1.0"; that.metricMultiplier = "60.0"; resultUS = 21; return true;
			case 6: this.metricMultiplier = "1.0"; that.metricMultiplier = "3600.0"; resultUS = 21; return true;
			default: return false;
			}
		case 16:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 16; return true;
			default: return false;
			}
		case 17:
			switch(that.unitSpecifier) {
			case 1: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 22; return true;
			case 2: this.metricMultiplier = "1.0"; that.metricMultiplier = "1000000.0"; resultUS = 22; return true;
			case 3: this.metricMultiplier = "10.764"; that.metricMultiplier = "1.0"; resultUS = 24; return true;
			default: return false;
			}
		case 18:
			switch(that.unitSpecifier) {
			case 1: this.metricMultiplier = "1000000.0"; that.metricMultiplier = "1.0"; resultUS = 22; return true;
			case 2: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 23; return true;
			case 3: this.metricMultiplier = "1.076e+7"; that.metricMultiplier = "1.0"; resultUS = 24; return true;
			}
		case 19:
			switch(that.unitSpecifier) {
			case 1: this.metricMultiplier = "1.0"; that.metricMultiplier = "10.764"; resultUS = 24; return true;
			case 2: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.076e+7"; resultUS = 24; return true;
			case 3: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 24; return true;
			}
		default: return false;
		}
	}
	
	boolean checkIfValidForDivision(Unit that) {
		switch(this.unitSpecifier) {
		case 0: 
			switch(that.unitSpecifier) {
			case 1: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 1; return true;
			case 2: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 2; return true;
			case 3: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 3; return true;
			case 4: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 4; return true;
			case 5: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 5; return true;
			case 6: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 6; return true;
			case 7: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 7; return true;
			case 8: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 8; return true;
			case 9: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 9; return true;
			case 10: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 10; return true;
			case 11: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 11; return true;
			case 12: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 12; return true;
			case 13: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 13; return true;
			case 14: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 14; return true;
			case 15: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 15; return true;
			case 16: this.metricMultiplier = "1.0" ; that.metricMultiplier = "1.0" ; resultUS = 16; return true;
			default: return false;
			}
		case 1:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 1; return true;
			case 1: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			case 4: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 10; return true;
			case 5: this.metricMultiplier = "1.0"; that.metricMultiplier = "60.0"; resultUS = 10; return true;
			case 6: this.metricMultiplier = "0.001"; that.metricMultiplier = "1.0"; resultUS = 11; return true;
			default: return false;
			}
		case 2:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 2; return true;
			case 2: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			case 4: this.metricMultiplier = "1000.0"; that.metricMultiplier = "1.0"; resultUS = 10; return true;
			case 5: this.metricMultiplier = "1.0"; that.metricMultiplier = "0.01666666666"; resultUS = 11; return true;
			case 6: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 11; return true;
			default: return false;
			}
		case 3:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 3; return true;
			case 3: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			case 4: this.metricMultiplier = "0.3048"; that.metricMultiplier = "1.0"; resultUS = 10; return true;
			case 5: this.metricMultiplier = "0.3048"; that.metricMultiplier = "60.0"; resultUS = 10; return true;
			case 6: this.metricMultiplier = "0.3048"; that.metricMultiplier = "3600.0"; resultUS = 10; return true;
			default: return false;
			}
		case 4:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 4; return true;
			case 4: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			default: return false;
			}
		case 5:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 5; return true;
			case 5: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			default: return false;
			}
		case 6:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 6; return true;
			case 6: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			default: return false;
			}
		case 7:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 7; return true;
			case 7: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			default: return false;
			}
		case 8:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 8; return true;
			case 8: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			default: return false;
			}
		case 9:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 9; return true;
			case 9: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			default: return false;
			}
		case 10:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 10; return true;
			case 10: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			case 4: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 12; return true;
			case 5: this.metricMultiplier = "1.0"; that.metricMultiplier = "60.0"; resultUS = 12; return true;
			case 6: this.metricMultiplier = "1.0"; that.metricMultiplier = "3600.0"; resultUS = 12; return true;
			case 12: resultUS = 4; return true;
			case 13: this.metricMultiplier = "1.0"; that.metricMultiplier = "0.0000771604938"; resultUS = 4; return true;
			default: return false;
			}
		case 11:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 11; return true;
			case 11: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			case 4: this.metricMultiplier = "1.0"; that.metricMultiplier = "0.00027777777"; resultUS = 13; return true;
			case 5: this.metricMultiplier = "1.0"; that.metricMultiplier = "0.01666666666"; resultUS = 13; return true;
			case 6: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 13; return true;
			case 12: this.metricMultiplier = "3.6"; that.metricMultiplier = "1.0"; resultUS = 4; return true;
			case 13: resultUS = 6; return true;
			default: return false;
			}
		case 12:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 12; return true;
			case 12: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			default: return false;
			}
		case 13:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 13; return true;
			case 13: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			default: return false;
			}
		case 14:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 14; return true;
			case 14: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			default: return false;
			}
		case 15:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 15; return true;
			case 15: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			default: return false;
			}
		case 16:
			switch(that.unitSpecifier) {
			case 0: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 16; return true;
			case 16: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			default: return false;
			}
		case 22: 
			switch(that.unitSpecifier) {
			case 16: this.metricMultiplier = "1.0"; that.metricMultiplier = "1e+9"; resultUS = 26; return true;
			case 22: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			default: return false;
			}
		case 23: 
			switch(that.unitSpecifier) {
			case 16: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 26; return true;
			case 23: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			default: return false;
			}
		case 24: 
			switch(that.unitSpecifier) {
			case 16: this.metricMultiplier = "1.0"; that.metricMultiplier = "3.531e+10"; resultUS = 26; return true;
			case 24: this.metricMultiplier = "1.0"; that.metricMultiplier = "1.0"; resultUS = 0; return true;
			default: return false;
			}
		default: return false;
		}
	}	
	
	boolean checkIfValidForSquareRoot() {
		switch(this.unitSpecifier) {
		case 17:
			resultUS = 1;
			return true;
		case 18:
			resultUS = 2;
			return true;
		case 19:
			resultUS = 3;
			return true;
		case 25:
			resultUS = 6;
			return true;
		case 26:
			resultUS = 4;
			return true;
		default:
		return false;
		}
	}
	
	/****
	 * get result unit returns resultant unit after performing the operation
	 * */
	Unit getResultantUnitAfterAddition(Unit that) {
		return new Unit(resultUS);
	}
	
	Unit getResultantUnitAfterSubtraction(Unit that) {
		return new Unit(resultUS);
	}
	
	Unit getResultantUnitAfterMultiplication(Unit that) {
		return new Unit(resultUS);
	}
	
	Unit getResultantUnitAfterDivision(Unit that) {
		return new Unit(resultUS);
	}

	public Unit getResultantUnitAfterSquareRoot() {
		return new Unit(resultUS);
	}
}