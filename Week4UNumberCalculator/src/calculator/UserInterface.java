package calculator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
//import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

//import java.awt.Scrollbar;

import calculator.BusinessLogic;




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

public class UserInterface {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager
	   for this application. Rather we manually control the location of each graphical element for
	   exact control of the look and feel. */
	
	// These set the width and positioning of buttons
	private final double BUTTON_WIDTH = 60;
	private final double BUTTON_OFFSET = BUTTON_WIDTH / 2;

	// These are the application values required by the user interface
	
	// list of units	
	String[] unitList = {"no-unit-selected",
			"meter", "kilometer", "feet",
			"seconds", "minutes", "hours", 
			"grams", "pounds", "kilograms",
			"mps", "kmph",
			"mps2", "kmph2",
			"Newton", "Dyne",
			"km3s-2",
			"m2","km2","feet2",
			"kg.m.s-1", "g.cm.s-1",
			"m3", "km3", "feet3",
			"hr2", "sec2"
				};
	
	private Label label_IntegerCalculator = new Label("This is a UNumber Calculator");
	private Label label_Operand1 = new Label("First operand");
	private TextField text_Operand1 = new TextField();          // No initial value
	private Label label_Operand1ErrorTerm = new Label("Error Term");
	private TextField text_Operand1ErrorTerm = new TextField();
	private CheckBox checkPrecise1 = new CheckBox("Precise Value");
	
	Group rootPopUp1;
	Scene scenePopUp1;
	static Stage stagePopUp1;
	
	TitledPane td1, tt1, tm1, tv1, ta1, tf1, tgp1, tar1, tvo1; //panes for accordion
	ObservableList<Button> distance1 = FXCollections.observableArrayList();
	ObservableList<Button> time1 = FXCollections.observableArrayList();
	ObservableList<Button> mass1 = FXCollections.observableArrayList();
	ObservableList<Button> velocity1 = FXCollections.observableArrayList();
	ObservableList<Button> acceleration1 = FXCollections.observableArrayList();
	ObservableList<Button> force1 = FXCollections.observableArrayList();
	ObservableList<Button> gravitationalParameter1 = FXCollections.observableArrayList();
	ObservableList<Button> area1 = FXCollections.observableArrayList();
	ObservableList<Button> volume1 = FXCollections.observableArrayList();
	
	Label labelSelectAUnit1 = new Label("Select unit 1:");
	static Button btnSelectAUnit1 = new Button("Select a unit 1");

	Button btnUnit1000 = createButton1 ("no-unit-selected", "0");
	
	Button btnUnit1001 = createButton1 ("meter", "1");
	Button btnUnit1002 = createButton1 ("kilometer", "2");
	Button btnUnit1003 = createButton1 ("feet", "3");
	
	Button btnUnit1004 = createButton1 ("seconds", "4");
	Button btnUnit1005 = createButton1 ("minutes", "5");
	Button btnUnit1006 = createButton1 ("hours", "6");
	
	Button btnUnit1007 = createButton1 ("grams", "7");
	Button btnUnit1008 = createButton1 ("pounds", "8");
	Button btnUnit1009 = createButton1 ("kilograms", "9");
	
	Button btnUnit1010 = createButton1 ("mps", "10");
	Button btnUnit1011 = createButton1 ("kmph", "11");
	
	Button btnUnit1012 = createButton1 ("mps2", "12");
	Button btnUnit1013 = createButton1 ("kmph2", "13");
	
	Button btnUnit1014 = createButton1 ("Newton", "14");
	Button btnUnit1015 = createButton1 ("Dyne", "15");
	
	Button btnUnit1016 = createButton1 ("km3s-2", "16");
	
	Button btnUnit1017 = createButton1 ("m2", "17");
	Button btnUnit1018 = createButton1 ("km2", "18");
	Button btnUnit1019 = createButton1 ("feet2", "19");
	
	Button btnUnit1022 = createButton1 ("m3", "22");
	Button btnUnit1023 = createButton1 ("km3", "23");
	Button btnUnit1024 = createButton1 ("feet3", "24");
	
	Button btnUnit1025 = createButton1 ("hr2", "25");
	Button btnUnit1026 = createButton1 ("sec2", "26");
	
	Button button1Selected = btnUnit1000;
	
	String outputSelectedUnit1 = "";
	int outputSelectedUnit1Index = 0;
	
	String unit1Name = "no-unit-selected";
	int unit1Index = 0;
	Unit unit1 = new Unit(0);
	
	private Button createButton1 (String buttonText, String txtIndex) {
		Button button = new Button(buttonText);
		button.setOnAction(eve->{btnSelectAUnit1.setText(button.getText());
		outputSelectedUnit1 = button.getText(); 
		outputSelectedUnit1Index = Integer.parseInt(txtIndex);
		stagePopUp1.close();});
		unit1 = new Unit(outputSelectedUnit1Index);
		return button;
	}
	
	private Label label_Operand2 = new Label("Second operand");
	private TextField text_Operand2 = new TextField();			// No initial value
	private Label label_Operand2ErrorTerm = new Label("Error Term");
	private TextField text_Operand2ErrorTerm = new TextField();
	
	private CheckBox checkPrecise2 = new CheckBox("Precise Value");
	
	Group rootPopUp2;
	Scene scenePopUp2;
	static Stage stagePopUp2;
	
	TitledPane td2, tt2, tm2, tv2, ta2, tf2, tgp2, tar2, tvo2; //panes for accordion
	ObservableList<Button> distance2 = FXCollections.observableArrayList();
	ObservableList<Button> time2 = FXCollections.observableArrayList();
	ObservableList<Button> mass2 = FXCollections.observableArrayList();
	ObservableList<Button> velocity2 = FXCollections.observableArrayList();
	ObservableList<Button> acceleration2 = FXCollections.observableArrayList();
	ObservableList<Button> force2 = FXCollections.observableArrayList();
	ObservableList<Button> gravitationalParameter2 = FXCollections.observableArrayList();
	ObservableList<Button> area2 = FXCollections.observableArrayList();
	ObservableList<Button> volume2 = FXCollections.observableArrayList();
	
	Label labelSelectAUnit2 = new Label("Select unit 2:");
	static Button btnSelectAUnit2 = new Button("Select a unit 2");

	Button btnUnit2000 = createButton2 ("no-unit-selected", "0");

	Button btnUnit2001 = createButton2 ("meter", "1"); 
	Button btnUnit2002 = createButton2 ("kilometer", "2");
	Button btnUnit2003 = createButton2 ("feet", "3");
	
	Button btnUnit2004 = createButton2 ("seconds", "4");
	Button btnUnit2005 = createButton2 ("minutes", "5");
	Button btnUnit2006 = createButton2 ("hours", "6");
	
	Button btnUnit2007 = createButton2 ("grams", "7");
	Button btnUnit2008 = createButton2 ("pounds", "8");
	Button btnUnit2009 = createButton2 ("kilograms", "9");
	
	Button btnUnit2010 = createButton2 ("mps", "10");
	Button btnUnit2011 = createButton2 ("kmph", "11");
	
	Button btnUnit2012 = createButton2 ("mps2", "12");
	Button btnUnit2013 = createButton2 ("kmph2", "13");
	
	Button btnUnit2014 = createButton2 ("Newton", "14");
	Button btnUnit2015 = createButton2 ("Dyne", "15");
	
	Button btnUnit2016 = createButton2 ("km3s-2", "16");
	
	Button btnUnit2017 = createButton1 ("m2", "17");
	Button btnUnit2018 = createButton1 ("km2", "18");
	Button btnUnit2019 = createButton1 ("feet2", "19");
	
	Button btnUnit2022 = createButton1 ("m3", "22");
	Button btnUnit2023 = createButton1 ("km3", "23");
	Button btnUnit2024 = createButton1 ("feet3", "24");
	
	Button btnUnit2025 = createButton1 ("hr2", "25");
	Button btnUnit2026 = createButton1 ("sec2", "26");
	
	String outputSelectedUnit2 = "";
	int outputSelectedUnit2Index = 0;
	
	Button button2Selected = btnUnit2000;
	
	String unit2Name = "no-unit-selected";
	int unit2Index = 0;
	Unit unit2 = new Unit(0);
	
	private Button createButton2 (String buttonText, String txtIndex) {
		Button button = new Button(buttonText);
		button.setOnAction(eve->{btnSelectAUnit2.setText(button.getText());
		outputSelectedUnit2 = button.getText(); 
		outputSelectedUnit2Index = Integer.parseInt(txtIndex);
		stagePopUp2.close();});
		unit2 = new Unit(outputSelectedUnit2Index);
		return button;
	}
	
	private Label label_Result = new Label("Result");
	private Label label_ResultErrorTerm = new Label("Error Term");
	private TextField text_Result = new TextField();			// No initial value
	private TextField text_ResultErrorTerm = new TextField();
	
	// These are the buttons that perform the calculator operations when pressed
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("\u00D7");				
	private Button button_Div = new Button("\u00F7");
	private Button button_Sqrt = new Button("\u221A");
	// These are the labels that are used to display error messages. Since they are empty, nothing
	// shows on the user interface.
	private Label label_errOperand1 = new Label("");
	private Label label_errOperand1ET = new Label("");
	private Text errOperand1MVPart1 = new Text();
	private Text errOperand1MVPart2 = new Text();
	private Text errOperand1ETPart1 = new Text();
	private Text errOperand1ETPart2 = new Text();
	private TextFlow errOperand1MeasuredValue;
	private TextFlow errOperand1ErrorTerm;
	private Label label_errOperand2 = new Label("");
	private Label label_errOperand2ET = new Label("");
	private Text errOperand2MVPart1 = new Text();
	private Text errOperand2MVPart2 = new Text();
	private Text errOperand2ETPart1 = new Text();
	private Text errOperand2ETPart2 = new Text();
	private TextFlow errOperand2MeasuredValue;
	private TextFlow errOperand2ErrorTerm;
	private Label label_errResult = new Label("");
	private Label label_ResultUnit= new Label("Result unit");
	private TextField text_ResultUnit = new TextField();

	
	private double buttonSpace;		// This is the white space between the operator buttons.
	
	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();

	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/**********
	 * This constructor initializes all of the elements of the graphical user interface. These
	 * assignments determine the location, size, font, color, and change and event handlers for
	 * each GUI object.
	 */
	public UserInterface(Pane theRoot) {
				
		// There are five gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 6;
		
		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_IntegerCalculator, "Arial", 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, 10);
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH/10, Pos.BASELINE_LEFT, 10, 80);
		
		// Establish the first text input operand field and when anything changes in operand 1 measured value,
		// measured value process all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH/4 + 100, Pos.BASELINE_LEFT, 10, 110, true);
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
		
		// Establish an error message for the first operand just above it with, left aligned
		setupLabelUI(label_errOperand1, "Arial", 14,Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 22, 165);
		label_errOperand1.setTextFill(Color.RED);
		
		// Label the first operand error term just above it, left aligned
		setupLabelUI(label_Operand1ErrorTerm, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/4 + 140, 80);
				
				
		// Establish the Error Term textfield for the first operand.  If anything changes, process
		// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1ErrorTerm, "Arial", 18, 250, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/4 + 140, 110, true);
		text_Operand1ErrorTerm.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1ErrorTerm(); });
		
		// Establish an error message for the first operand just above it with, left aligned
		setupLabelUI(label_errOperand1ET, "Arial", 14,Calculator.WINDOW_WIDTH-10 , Pos.BASELINE_LEFT,Calculator.WINDOW_WIDTH/4 + 143, 165);
		label_errOperand1ET.setTextFill(Color.RED); 
		
		// Move focus to the second operand when the user presses the enter (return) key
		text_Operand1.setOnAction((event) -> { text_Operand2.requestFocus(); });
		
		distance1 = FXCollections.observableArrayList(btnUnit1000, btnUnit1001, btnUnit1002, btnUnit1003);
		time1 = FXCollections.observableArrayList(btnUnit1000, btnUnit1004, btnUnit1005, btnUnit1006, btnUnit1025, btnUnit1026);
		mass1 = FXCollections.observableArrayList(btnUnit1000, btnUnit1007, btnUnit1008, btnUnit1009);
		velocity1 = FXCollections.observableArrayList(btnUnit1000, btnUnit1010, btnUnit1011);
		acceleration1 = FXCollections.observableArrayList(btnUnit1000, btnUnit1012, btnUnit1013);
		force1 = FXCollections.observableArrayList(btnUnit1000, btnUnit1014, btnUnit1015);
		gravitationalParameter1 = FXCollections.observableArrayList(btnUnit1000, btnUnit1016);
		area1 = FXCollections.observableArrayList(btnUnit1000, btnUnit1017, btnUnit1018, btnUnit1019);
		volume1 = FXCollections.observableArrayList(btnUnit1000, btnUnit1022, btnUnit1023, btnUnit1024);
		
		td1 = new TitledPane("distance", new ListView<>(distance1));
		tt1 = new TitledPane("time", new ListView<>(time1));
		tm1 = new TitledPane("mass", new ListView<>(mass1));
		tv1 = new TitledPane("velocity", new ListView<>(velocity1));
		ta1 = new TitledPane("acceleration", new ListView<>(acceleration1));
		tf1 = new TitledPane("force", new ListView<>(force1));
		tgp1 = new TitledPane("gravitational constant", new ListView<>(gravitationalParameter1));
		tar1 = new TitledPane("area", new ListView<>(area1));
		tvo1 = new TitledPane("volume", new ListView<>(volume1));
		
		Accordion accordion1 = new Accordion();
		ScrollBar scroll = new ScrollBar();
		scroll.setOrientation(Orientation.VERTICAL);

		accordion1.setMinWidth(600);
		accordion1.setPrefHeight(600);
		// scroll.setPrefHeight(accordion1.getHeight());
		// scroll.setPrefWidth(accordion1.getWidth());
		// TitledPane sc = new TitledPane();
		// sc.setContent(scroll);
		accordion1.getPanes().addAll(td1, tt1, tm1, tv1, ta1, tf1, tgp1, tar1, tvo1);
		// Unit1
		setupLabelUI(labelSelectAUnit1, "Arial", 18, 200, Pos.BASELINE_LEFT, 640, 80);		
		setupButtonUI(btnSelectAUnit1, "Arial", 14, 35, Pos.BASELINE_LEFT, 640, 110);
		btnSelectAUnit1.setOnAction(e->{stagePopUp1.showAndWait();});
		btnSelectAUnit1.setMinWidth(35);
		
		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH/10, Pos.BASELINE_LEFT, 10, 225);

		// Label the second operand error term just above it, left aligned
		setupLabelUI(label_Operand2ErrorTerm, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/4 + 150, 225);
				
		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH/4 + 100, Pos.BASELINE_LEFT, 10, 255, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2(); });
		
		// Establish the Error Term textfield for the first operand.  If anything changes, process
		// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2ErrorTerm, "Arial", 18, 250, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/4+140, 255, true);
		text_Operand2ErrorTerm.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2ErrorTerm(); });
		
		// Move the focus to the result when the user presses the enter (return) key
		text_Operand2.setOnAction((event) -> { text_Result.requestFocus(); });
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errOperand2, "Arial", 14, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 310);
		label_errOperand2.setTextFill(Color.RED);
		
		// // Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errOperand2ET, "Arial", 14,Calculator.WINDOW_WIDTH-10 , Pos.BASELINE_LEFT,Calculator.WINDOW_WIDTH/4+143, 310);
		label_errOperand2ET.setTextFill(Color.RED);
		
		distance2 = FXCollections.observableArrayList(btnUnit2000, btnUnit2001, btnUnit2002, btnUnit2003);;
		time2 = FXCollections.observableArrayList(btnUnit2000, btnUnit2004, btnUnit2005, btnUnit2006, btnUnit2025, btnUnit2026);
		mass2 = FXCollections.observableArrayList(btnUnit2000, btnUnit2007, btnUnit2008, btnUnit2009);
		velocity2 = FXCollections.observableArrayList(btnUnit2000, btnUnit2010, btnUnit2011);
		acceleration2 = FXCollections.observableArrayList(btnUnit2000, btnUnit2012, btnUnit2013);
		force2 = FXCollections.observableArrayList(btnUnit2000, btnUnit2014, btnUnit2015);
		gravitationalParameter2 = FXCollections.observableArrayList(btnUnit2000, btnUnit2016);
		area2 = FXCollections.observableArrayList(btnUnit2000, btnUnit2017, btnUnit2018, btnUnit2019);
		volume2 = FXCollections.observableArrayList(btnUnit2000, btnUnit2022, btnUnit2023, btnUnit2024);
		
		td2 = new TitledPane("distance", new ListView<>(distance2));
		tt2 = new TitledPane("time", new ListView<>(time2));
		tm2 = new TitledPane("mass", new ListView<>(mass2));
		tv2 = new TitledPane("velocity", new ListView<>(velocity2));
		ta2 = new TitledPane("acceleration", new ListView<>(acceleration2));
		tf2 = new TitledPane("force", new ListView<>(force2));
		tgp2 = new TitledPane("gravitational constant", new ListView<>(gravitationalParameter2));
		tar2 = new TitledPane("area", new ListView<>(area2));
		tvo2 = new TitledPane("volume", new ListView<>(volume2));
		Accordion accordion2 = new Accordion();
		accordion2.getPanes().addAll(td2, tt2, tm2, tv2, ta2, tf2, tgp2, tar2, tvo2);
		accordion2.setMinWidth(300);
		accordion2.setMinHeight(600);
		
		//Unit2
		setupLabelUI(labelSelectAUnit2, "Arial", 18, 200, Pos.BASELINE_LEFT, 640, 225);		
		setupButtonUI(btnSelectAUnit2, "Arial", 14, 35, Pos.BASELINE_LEFT, 640, 255);
		btnSelectAUnit2.setOnAction(e->{stagePopUp2.showAndWait();});
		btnSelectAUnit2.setMinWidth(35);
		
		// Label the result just above the result output field, left aligned
		setupLabelUI(label_Result, "Arial", 18, Calculator.WINDOW_WIDTH/4, Pos.BASELINE_LEFT, 10, 380);
		
		// Label the result just above the result output field, left aligned
		setupLabelUI(label_ResultErrorTerm, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/4+140, 380);
		
		// Establish the result output field.  It is not editable, so the text can be selected and copied, 
		// but it cannot be altered by the user.  The text is left aligned.
		setupTextUI(text_Result, "Arial", 18, Calculator.WINDOW_WIDTH/4 + 100, Pos.BASELINE_LEFT, 10, 410, false);
		
		// Establish the Error Term textfield for the first operand.  If anything changes, process
		// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_ResultErrorTerm, "Arial", 18, 250, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/4+140, 410, true);
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errResult, "Arial", 18, Calculator.WINDOW_WIDTH/4, Pos.BASELINE_LEFT, 10 , 450);
		label_errResult.setTextFill(Color.RED);
		
		// 
		setupLabelUI(label_ResultUnit, "Arial", 18, 200, Pos.BASELINE_LEFT, 640, 380);
		
		//
		setupTextUI(text_ResultUnit, "Arial", 18, 150, Pos.BASELINE_LEFT, 640, 410, false);
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * buttonSpace-BUTTON_OFFSET-50, 490);
		button_Add.setOnAction((event) -> { addOperands(); });
		
		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * buttonSpace-BUTTON_OFFSET-50, 490);
		button_Sub.setOnAction((event) -> { subOperands(); });
		
		// Establish the MPY "×" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * buttonSpace-BUTTON_OFFSET-50, 490);
		button_Mpy.setOnAction((event) -> { mpyOperands(); });
		
		// Establish the DIV "÷" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * buttonSpace-BUTTON_OFFSET-50, 490);
		button_Div.setOnAction((event) -> { divOperands(); });
		
		// Establish the DIV "÷" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * buttonSpace-BUTTON_OFFSET-50, 490);
		button_Sqrt.setOnAction((event) -> { sqrtOperand(); });
		
		//Error Message for the Measured Value for operand 1
		errOperand1MVPart1.setFill(Color.BLACK);
		errOperand1MVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
		errOperand1MVPart2.setFill(Color.RED);
	    errOperand1MVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errOperand1MeasuredValue = new TextFlow(errOperand1MVPart1, errOperand1MVPart2);
	    errOperand1MeasuredValue.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errOperand1MeasuredValue.setLayoutX(19);  
		errOperand1MeasuredValue.setLayoutY(140);
		
		//Error Message for the Measured Value for operand 1
		errOperand1ETPart1.setFill(Color.BLACK);
		errOperand1ETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
		errOperand1ETPart2.setFill(Color.RED);
		errOperand1ETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
		errOperand1ErrorTerm = new TextFlow(errOperand1ETPart1, errOperand1ETPart2);
		errOperand1ErrorTerm.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errOperand1ErrorTerm.setLayoutX(Calculator.WINDOW_WIDTH/4 + 152);  
		errOperand1ErrorTerm.setLayoutY(140);
		
		//Error Message for the Measured Value for operand 2
		errOperand2MVPart1.setFill(Color.BLACK);
		errOperand2MVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
		errOperand2MVPart2.setFill(Color.RED);
		errOperand2MVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
		errOperand2MeasuredValue = new TextFlow(errOperand2MVPart1, errOperand2MVPart2);
		errOperand2MeasuredValue.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errOperand2MeasuredValue.setLayoutX(19);  
		errOperand2MeasuredValue.setLayoutY(285);
		
		//Error Message for the Error Term for operand 2
		errOperand2ETPart1.setFill(Color.BLACK);
		errOperand2ETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
		errOperand2ETPart2.setFill(Color.RED);
		errOperand2ETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
		errOperand2ErrorTerm = new TextFlow(errOperand2ETPart1, errOperand2ETPart2);
		errOperand2ErrorTerm.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errOperand2ErrorTerm.setLayoutX(Calculator.WINDOW_WIDTH/4 + 152);  
		errOperand2ErrorTerm.setLayoutY(285);
	    
		
		setupcheckBoxUI(checkPrecise1, "Arial", 15, 110, Pos.CENTER, Calculator.WINDOW_WIDTH/9+30, 82);

		checkPrecise1.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	        	if(new_val) {
		        	disableErrorTerms("operand1");
	        	}else {
	        		enableErrorTerms("operand1");
	        	}
	        }
	    });
		
		setupcheckBoxUI(checkPrecise2, "Arial", 15, 110, Pos.CENTER, Calculator.WINDOW_WIDTH/9+55, 227);

		checkPrecise2.selectedProperty().addListener(new ChangeListener<Boolean>() {
	        public void changed(ObservableValue<? extends Boolean> ov,
	            Boolean old_val, Boolean new_val) {
	        	if(new_val) {
		        	disableErrorTerms("operand2");
	        	}else {
	        		enableErrorTerms("operand2");
	        	}
	        }
	    });
		
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_IntegerCalculator, label_Operand1, text_Operand1, label_errOperand1, label_errOperand1ET,
				errOperand1ErrorTerm,label_errOperand2ET, label_Operand2,label_Operand1ErrorTerm, errOperand2ErrorTerm, text_Operand1ErrorTerm, text_Operand2, text_Operand2ErrorTerm, 
				label_Operand2ErrorTerm, label_errOperand2, label_Result, text_Result, label_errResult,label_ResultErrorTerm, errOperand1MeasuredValue,
				errOperand2MeasuredValue, text_ResultErrorTerm, button_Add, button_Sub, button_Mpy, button_Div, button_Sqrt, label_ResultUnit, text_ResultUnit, checkPrecise1, checkPrecise2);
		
		rootPopUp1 = new Group();
		rootPopUp1.getChildren().addAll(accordion1);
		
		theRoot.getChildren().addAll(labelSelectAUnit1, btnSelectAUnit1);
		
		scenePopUp1 = new Scene(rootPopUp1, 200, 300);
		
		stagePopUp1 = new Stage();
		stagePopUp1.setScene(scenePopUp1);
		stagePopUp1.setTitle("Select a unit 1");

		btnUnit1000.setOnAction((event) -> { button1Selected = (btnUnit1000); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm(); });
		btnUnit1001.setOnAction((event) -> { button1Selected = (btnUnit1001); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit1002.setOnAction((event) -> { button1Selected = (btnUnit1002); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit1003.setOnAction((event) -> { button1Selected = (btnUnit1003); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		
		btnUnit1004.setOnAction((event) -> { button1Selected = (btnUnit1004); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit1005.setOnAction((event) -> { button1Selected = (btnUnit1005); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm(); });
		btnUnit1006.setOnAction((event) -> { button1Selected = (btnUnit1006); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm(); });
		
		btnUnit1007.setOnAction((event) -> { button1Selected = (btnUnit1007); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit1008.setOnAction((event) -> { button1Selected = (btnUnit1008); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm(); });
		btnUnit1009.setOnAction((event) -> { button1Selected = (btnUnit1009); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		
		btnUnit1010.setOnAction((event) -> { button1Selected = (btnUnit1010); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit1011.setOnAction((event) -> { button1Selected = (btnUnit1011); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		
		btnUnit1012.setOnAction((event) -> { button1Selected = (btnUnit1012); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit1013.setOnAction((event) -> { button1Selected = (btnUnit1013); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm(); });
		
		btnUnit1014.setOnAction((event) -> { button1Selected = (btnUnit1014); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit1015.setOnAction((event) -> { button1Selected = (btnUnit1015); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		
		btnUnit1016.setOnAction((event) -> { button1Selected = (btnUnit1016); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		
		btnUnit1017.setOnAction((event) -> { button1Selected = (btnUnit1017); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit1018.setOnAction((event) -> { button1Selected = (btnUnit1018); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit1019.setOnAction((event) -> { button1Selected = (btnUnit1019); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});

		btnUnit1022.setOnAction((event) -> { button1Selected = (btnUnit1022); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit1023.setOnAction((event) -> { button1Selected = (btnUnit1023); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit1024.setOnAction((event) -> { button1Selected = (btnUnit1024); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});

		btnUnit1025.setOnAction((event) -> { button1Selected = (btnUnit1025); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit1026.setOnAction((event) -> { button1Selected = (btnUnit1026); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});

		rootPopUp2 = new Group();
		rootPopUp2.getChildren().addAll(accordion2);
		
		theRoot.getChildren().addAll(labelSelectAUnit2, btnSelectAUnit2);
		
		scenePopUp2 = new Scene(rootPopUp2, 200, 300);
		
		stagePopUp2 = new Stage();
		stagePopUp2.setScene(scenePopUp2);
		stagePopUp2.setTitle("Select a unit 2");
		
		btnUnit2000.setOnAction((event) -> { button2Selected = (btnUnit2000); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit2001.setOnAction((event) -> { button2Selected = (btnUnit2001); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit2002.setOnAction((event) -> { button2Selected = (btnUnit2002); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit2003.setOnAction((event) -> { button2Selected = (btnUnit2003); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		
		btnUnit2004.setOnAction((event) -> { button2Selected = (btnUnit2004); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm(); });
		btnUnit2005.setOnAction((event) -> { button2Selected = (btnUnit2005); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit2006.setOnAction((event) -> { button2Selected = (btnUnit2006); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm(); });
		
		btnUnit2007.setOnAction((event) -> { button2Selected = (btnUnit2007); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm(); });
		btnUnit2008.setOnAction((event) -> { button2Selected = (btnUnit2008); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit2009.setOnAction((event) -> { button2Selected = (btnUnit2009); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		
		btnUnit2010.setOnAction((event) -> { button2Selected = (btnUnit2010); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit2011.setOnAction((event) -> { button2Selected = (btnUnit2011); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		
		btnUnit2012.setOnAction((event) -> { button2Selected = (btnUnit2012); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit2013.setOnAction((event) -> { button2Selected = (btnUnit2013); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		
		btnUnit2014.setOnAction((event) -> { button2Selected = (btnUnit2014); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm(); });
		btnUnit2015.setOnAction((event) -> { button2Selected = (btnUnit2015); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm(); });
		
		btnUnit2016.setOnAction((event) -> { button2Selected = (btnUnit2016); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		
		btnUnit2017.setOnAction((event) -> { button2Selected = (btnUnit2017); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit2018.setOnAction((event) -> { button2Selected = (btnUnit2018); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit2019.setOnAction((event) -> { button2Selected = (btnUnit2019); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		
		btnUnit2022.setOnAction((event) -> { button2Selected = (btnUnit2022); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit2023.setOnAction((event) -> { button2Selected = (btnUnit2023); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit2024.setOnAction((event) -> { button2Selected = (btnUnit2024); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		
		btnUnit2025.setOnAction((event) -> { button2Selected = (btnUnit2025); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		btnUnit2026.setOnAction((event) -> { button2Selected = (btnUnit2026); setOperand1(); setOperand1ErrorTerm(); setOperand2(); setOperand2ErrorTerm();});
		
		
	}
	
	private void disableErrorTerms(String str) {
		if (str.equals("operand1")) {
			perform.setoperand1ETDefined();
			text_Operand1ErrorTerm.setText("");
			setOperand1();
			setOperand2();
			setOperand1ErrorTerm();
			setOperand2ErrorTerm();
			text_Operand1ErrorTerm.setEditable(false);
		} else if (str.equals("operand2")) {
			perform.setoperand2ETDefined();
			text_Operand2ErrorTerm.setText("");
			setOperand1();
			setOperand2();
			setOperand1ErrorTerm();
			setOperand2ErrorTerm();
			text_Operand2ErrorTerm.setEditable(false);
		}
		text_ResultErrorTerm.setText("");
	}
	
	private void enableErrorTerms(String str) {
		if (str.equals("operand1")) {
			perform.operand1ETDefined = false;
			text_Operand1ErrorTerm.setText("");
			text_Operand1ErrorTerm.setEditable(true);
		} else if (str.equals("operand2")) {
			perform.operand2ETDefined = false;
			text_Operand2ErrorTerm.setText("");
			text_Operand2ErrorTerm.setEditable(true);
		}
		text_ResultErrorTerm.setText("");
	}


	/**********
	 * Private local method to initialize the standard fields for a label
	 * 
	 * @param l		The Label object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the label
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
		
	/**********
	 * Private local method to initialize the standard fields for a text field
	 * 
	 * @param t		The TextField object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the text field
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 * @param e		true if the field should be editable, else false
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 * 
	 * @param b		The Button object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the Button
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a CheckBox
	 * 
	 * @param c		The CheckBox object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the CheckBox
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupcheckBoxUI(CheckBox c, String ff, double f, double w, Pos p, double x, double y){
		c.setFont(Font.font(ff, f));
		c.setMinWidth(w);
		c.setAlignment(p);
		c.setLayoutX(x);
		c.setLayoutY(y);		
	}
	
	
	/**********************************************************************************************

	User Interface Actions
	
	**********************************************************************************************/

	/**********
	 * Private local method used to set the value of the first operand given a text value. The 
	 * method uses the business logic class to perform the work of checking the string to see it is
	 * a valid value and if so, saving that value internally for future computations. If there is 
	 * an error when trying to convert the string into a value, the called business logic method 
	 * returns false and actions are taken to display the error message appropriately.
	 */
	private void setOperand1() {
		text_Result.setText("");                           // Any change of an operand probably
		text_ResultErrorTerm.setText("");
		text_ResultUnit.setText("");
		// text_Operand1ErrorTerm.setText("");
		// text_Operand1ErrorTerm.setText("");
		label_Result.setText("Result");						// invalidates the result, so we clear
		label_Result.setTextFill(Color.BLACK);				// the old result and the error 
		label_errResult.setText("");	                    // message
		
		btnSelectAUnit1.setText(button1Selected.getText());
		unit1Name = button1Selected.getText();
		
		for(int i=0;i<unitList.length;i++) {
			if(unit1Name.equals(unitList[i])) {
				unit1Index = i;
				break;
			}
		}
		stagePopUp1.close();								// Once a unit is selected the pop up must be closed.
		unit1 = new Unit(unit1Index);
		
		if (perform.setOperand1(text_Operand1.getText())) {	// Set the operand and see if there was
			label_errOperand1.setText("");	                // an error. If no error, clear this 
			errOperand1MVPart2.setText("");
			errOperand1MVPart1.setText("");
			System.out.println("true");
			if (text_Operand2.getText().length() == 0)		// operands error. If the other operand 
				label_errOperand2.setText("");				// is empty, clear its error as well.
		}
		else {												// If there's an issue with the operand,
			performGo1();									// display the error
		}													// message that was generated. This error generation is done using the
															// function mentioned here.
	}
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is
	 * exactly the same as used for the first operand, above.
	 */
	private void setOperand1ErrorTerm() {
		text_Result.setText("");							// Any change of an operand probably
		text_ResultErrorTerm.setText("");
		text_ResultUnit.setText("");
		label_Result.setText("Result");						// invalidates the result, so we clear
		label_Result.setTextFill(Color.BLACK);				// the old result and the error 
		label_errResult.setText("");	                    // message
		
		
		
		// System.out.println(text_Operand1ErrorTerm.getText() + "-->" + perform.setOperand1ErrorTerm(text_Operand1ErrorTerm.getText()));
		if (perform.setOperand1ErrorTerm(text_Operand1ErrorTerm.getText())) {	// Set the operand and see if there was
			// System.out.println("reached4");
			label_errOperand1ET.setText("");	                // an error. If no error, clear this 
			errOperand1ETPart2.setText("");
			errOperand1ETPart1.setText("");
			// System.out.println("true");
			if (text_Operand2.getText().length() == 0)		// operands error. If the other operand 
				label_errOperand2.setText("");				// is empty, clear its error as well.
		}
		else {												// If there's an issue with the operand,
			performGo1();									// display the error
		}
	}
	
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is
	 * exactly the same as used for the first operand, above.
	 */
	private void setOperand2() {
		text_Result.setText("");							// See setOperand1's comments. The logic
		text_ResultErrorTerm.setText("");
		text_ResultUnit.setText("");
		label_Result.setText("Result");						// is the same!
		label_Result.setTextFill(Color.BLACK);
		label_errResult.setText("");
		
		btnSelectAUnit2.setText(button2Selected.getText());
		unit2Name = button2Selected.getText();
		
		for(int i=0;i<unitList.length;i++) {
			if(unit2Name.equals(unitList[i])) {
				unit2Index = i;
				break;
			}
		}
		stagePopUp2.close();											// Once a unit is selected the pop up must be closed.
		unit2 = new Unit(unit2Index);
		
		if (perform.setOperand2(text_Operand2.getText())) {
			label_errOperand2.setText("");
			errOperand2MVPart2.setText("");
			errOperand2MVPart1.setText("");
			if (text_Operand1.getText().length() == 0)
				label_errOperand1.setText("");
		}
		else
			performGo2();
	}
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is
	 * exactly the same as used for the first operand, above.
	 */
	private void setOperand2ErrorTerm() {
		text_Result.setText("");							// Any change of an operand probably
		text_ResultErrorTerm.setText("");
		text_ResultUnit.setText("");
		label_Result.setText("Result");						// invalidates the result, so we clear
		label_Result.setTextFill(Color.BLACK);				// the old result and the error 
		label_errResult.setText("");	                    // message
		
		if (perform.setOperand2ErrorTerm(text_Operand2ErrorTerm.getText())) {	// Set the operand and see if there was
			// System.out.println("reached5");
			label_errOperand2ET.setText("");	                // an error. If no error, clear this 
			errOperand2ETPart2.setText("");
			errOperand2ETPart1.setText("");
			// System.out.println("true");
			if (text_Operand1.getText().length() == 0)		// operands error. If the other operand 
				label_errOperand1.setText("");				// is empty, clear its error as well.
		}
		else {												// If there's an issue with the operand,
			performGo2();									// display the error
		}
	}
	
	/**********
	 * This method is called when an binary operation button has been pressed. It assesses if there 
	 * are issues with either of the binary operands or they are not defined. If not return false 
	 * (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssues() {
		label_Result.setText("Result");
		label_Result.setTextFill(Color.BLACK);					// Assume no errors
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if
		String[] tokens1 = errorMessage1.split("_");
		String errorMessage1MV = tokens1[0];
		String errorMessage11 = perform.getOperand1ErrorMessageforET();	// Fetch the error messages, if
		String[] tokens11 = errorMessage11.split("_");
		String errorMessage1ET = tokens11[0];
		
		String errorMessage2 = perform.getOperand2ErrorMessage();	// Fetch the error messages, if
		String[] tokens2 = errorMessage1.split("_");
		String errorMessage2MV = tokens2[0];
		String errorMessage22 = perform.getOperand2ErrorMessageforET();	// Fetch the error messages, if
		String[] tokens22 = errorMessage22.split("_");
		String errorMessage2ET = tokens22[0];
		if (errorMessage1.length() > 3) {						// Check the first.  If not empty
			label_errOperand1.setText(errorMessage1MV);			// there's an error, so display it.
			if (errorMessage11.length() > 3) {
				System.out.println("Reached");
				label_errOperand1ET.setText(errorMessage1ET);
				return true;
			}
			if (errorMessage2.length() > 3) {					// Do the same with the 2nd operand
				label_errOperand2.setText(errorMessage2MV);
				return true;									// Return true if both have errors
			}
			if (errorMessage22.length() > 3) {
				label_errOperand2ET.setText(errorMessage1ET);
				return true;
			}
			else {
				return true;									// Return true if only the first
			}													// has an error
		}
		
		else if (errorMessage11.length() > 3) {
			System.out.println(errorMessage11 + "line no: 785");
			label_errOperand1ET.setText(errorMessage1ET);
			System.out.println("Reached");
			if (errorMessage2.length() > 3) {					// Do the same with the 2nd operand
				label_errOperand2.setText(errorMessage2MV);
				return true;									// Return true if both have errors
			}
			if (errorMessage22.length() > 3) {
				label_errOperand2ET.setText(errorMessage2ET);
				return true;
			}
			else {
				System.out.println("Reached-1");
				return true;									// Return true if only the first
			}													// has an error
		}
		
		else if (errorMessage2.length() > 3) {					// No error with the first, so check
			label_errOperand2.setText(errorMessage2MV);			// the second operand the same way
			
			if (errorMessage22.length() > 3) {
				label_errOperand2ET.setText(errorMessage2ET);
				return true;
			}
			else {
				return true;									// Return true if only the first
			}													// has an error
			
		} 
		
		else if (errorMessage22.length() > 3) {
			label_errOperand2ET.setText(errorMessage2ET);
			return true;
		}
		
		// If the code reaches here, neither the first nor the second has an error condition. The
		// following code check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {					// Is first operand defined? If not,
			label_errOperand1.setText("No value found");		// it is an issue for this operator
			if (!perform.getOperand2Defined()) {				// Check the second operand. If it
				label_errOperand2.setText("No value found");	// is not defined, two messages 
				return true;									// should be displayed. Signal there
			}													// are issues by returning true.
			return true;
		} else if (!perform.getOperand2Defined()) {				// If the first is defined, check the
			label_errOperand2.setText("No value found");		// second. Both operands must be
			return true;										// defined. Signal there are issues
		}
		
		return false;											// No issues, so return false
	}
	
	/**********
	 * This method is called when an unary operation button has been pressed. It assesses if there 
	 * are issues with either of the unary operands or they are not defined. If not return false 
	 * (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean unaryOperandIssues() {
		label_Result.setText("Result");
		label_Result.setTextFill(Color.BLACK);						// Assume no errors
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if
		String[] tokens1 = errorMessage1.split("_");
		String errorMessage1MV = tokens1[0];
		String errorMessage11 = perform.getOperand1ErrorMessageforET();	// Fetch the error messages, if
		String[] tokens11 = errorMessage11.split("_");
		String errorMessage1ET = tokens11[0];						// Fetch the error messages, if
																	// any, from the two operands
		if (errorMessage1.length() > 3) {						// Check the first.  If not empty
			label_errOperand1.setText(errorMessage1MV); 
			if (errorMessage11.length() > 3) {
				label_errOperand1ET.setText(errorMessage1ET);					// there's an error, so display it.
			}
		//																// has an error
		}
		// If the code reaches here, neither the first nor the second has an error condition. The
		// following code check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {					// Is first operand defined? If not,
			label_errOperand1.setText("No value found");		// it is an issue for this operator
			return true;										// are issues by returning true.
			
		} 
		
		return false;											// No issues, so return false
	}

	/**********************************************************************************************
	 * This portion of the class defines the actions that take place when the various calculator
	 * buttons (add, subtract, multiply, divide and square root) are pressed.
	 */

	/**********
	 * This add routine is called when the user pressed the add button. It calls the business logic
	 * class to do the actual work. Notice that this method is really more about doing things with
	 * the user interface. That is, it interacts with the user and delegates all of the computation
	 * work to the business logic class and the other classes that it uses. This class and its 
	 * methods are work with Strings.
	 */
	private void addOperands(){
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) {							// If there are issues, return 
			return;												// without doing anything		
		}
		
		setOperand1();
		setOperand1ErrorTerm();
		setOperand2();
		setOperand2ErrorTerm();
		
		if(!unit1.checkIfValidForAddition(unit2) && !(unit1Index==0) && !(unit2Index==0)) {
			text_Result.setText("");
			text_ResultErrorTerm.setText("");
			label_errResult.setText("Units not compatable for addition");
			return;
		}
		
		perform.setOperand1WithConversion(unit1.getMetricMultiplier());
		perform.setOperand2WithConversion(unit2.getMetricMultiplier());
		
		// If the operands are defined and valid, request the business logic method to do the 
		// addition and return the result as a String. If there is a problem with the actual 
		// computation, an empty string is returned. 
		String theAnswer = perform.addition();					// The business logic does the add
		String[] answersTokens = theAnswer.split(" ");
		label_errResult.setText("");							// Reset the result error messages
		if (theAnswer.length() > 0) {							// See if a result was returned
			text_Result.setText(answersTokens[0]);				// If so, display it and change the
			Unit result = unit1.getResultantUnitAfterAddition(unit2);
			text_ResultUnit.setText(result.toString());
			if (!text_Operand1ErrorTerm.getText().equals("") || !text_Operand2ErrorTerm.getText().equals("")) {
				text_ResultErrorTerm.setText(answersTokens[1]);
			}
			
			label_Result.setText("Sum");						// title of the field to "Sum"
		}
		else {													// There is no result.
			text_Result.setText("");							// Do not display a result.				
			label_Result.setText("Result");						// Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage());	// Display error message.
		}
	}

	/**********
	 * This subtraction routine is called when the user pressed the minus button. It calls the business logic
	 * class to do the actual work. Notice that this method is really more about doing things with
	 * the user interface. That is, it interacts with the user and delegates all of the computation
	 * work to the business logic class and the other classes that it uses. This class and its 
	 * methods are work with Strings.
	 */
	private void subOperands(){
		if (binaryOperandIssues()) 								// If there are issues, return 
			return;												// without doing anything
		
		
		setOperand1();
		setOperand1ErrorTerm();
		setOperand2();
		setOperand2ErrorTerm();
		
		if(!unit1.checkIfValidForSubtraction(unit2) && !(unit1Index==0) && !(unit2Index==0)) {
			text_Result.setText("");
			text_ResultErrorTerm.setText("");
			label_errResult.setText("Units not compatable for subtraction");
			return;
		}
		
		perform.setOperand1WithConversion(unit1.getMetricMultiplier());
		perform.setOperand2WithConversion(unit2.getMetricMultiplier());

		
		// If the operands are defined and valid, request the business logic method to do the 
		// addition and return the result as a String. If there is a problem with the actual 
		// computation, an empty string is returned. 
		String theAnswer = perform.subtraction();					// The business logic does the add
		String[] answersTokens = theAnswer.split(" ");
		label_errResult.setText("");							// Reset the result error messages
		if (theAnswer.length() > 0) {							// See if a result was returned
			text_Result.setText(answersTokens[0]);				// If so, display it and change the
			Unit result = unit1.getResultantUnitAfterSubtraction(unit2);
			text_ResultUnit.setText(result.toString());
			if (!text_Operand1ErrorTerm.getText().equals("") || !text_Operand2ErrorTerm.getText().equals("")) {
				text_ResultErrorTerm.setText(answersTokens[1]);
			}
			
			label_Result.setText("Subtraction");				// title of the field to "Subtraction"
		}
		else {													// There is no result.
			text_Result.setText("");							// Do not display a result.				
			label_Result.setText("Result");						// Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage());	// Display error message.
		}
	}

	/**********
	 * This product routine is called when the user pressed the multiply button. It calls the business logic
	 * class to do the actual work. Notice that this method is really more about doing things with
	 * the user interface. That is, it interacts with the user and delegates all of the computation
	 * work to the business logic class and the other classes that it uses. This class and its 
	 * methods are work with Strings.
	 */
	private void mpyOperands(){
		if (binaryOperandIssues()) 								// If there are issues, return 
			return;												// without doing anything
		
		setOperand1();
		setOperand1ErrorTerm();
		setOperand2();
		setOperand2ErrorTerm();
		
		if(!unit1.checkIfValidForMultiplication(unit2) && !(unit1Index==0) && !(unit2Index==0)) {
			text_Result.setText("");
			text_ResultErrorTerm.setText("");
			label_errResult.setText("Units not compatable for multiplication");
			return;
		}

		perform.setOperand1WithConversion(unit1.getMetricMultiplier());
		perform.setOperand2WithConversion(unit2.getMetricMultiplier());
		
		// If the operands are defined and valid, request the business logic method to do the 
		// addition and return the result as a String. If there is a problem with the actual 
		// computation, an empty string is returned. 
		String theAnswer = perform.multiplication();			// The business logic does the multiplication
		String[] answersTokens = theAnswer.split(" ");
		label_errResult.setText("");							// Reset the result error messages
		if (theAnswer.length() > 0) {							// See if a result was returned
			text_Result.setText(answersTokens[0]);				// If so, display it and change the
			Unit result = unit1.getResultantUnitAfterMultiplication(unit2);
			text_ResultUnit.setText(result.toString());
			if (!text_Operand1ErrorTerm.getText().equals("") || !text_Operand2ErrorTerm.getText().equals("")) {
				text_ResultErrorTerm.setText(answersTokens[1]);
			}
			
			label_Result.setText("Product");					// title of the field to "Product"
		}
		else {													// There is no result.
			text_Result.setText("");							// Do not display a result.				
			label_Result.setText("Result");						// Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage());	// Display error message.
		}			
	}

	/**********
	 * This division routine is called when the user pressed the divide button. It calls the business logic
	 * class to do the actual work. Notice that this method is really more about doing things with
	 * the user interface. That is, it interacts with the user and delegates all of the computation
	 * work to the business logic class and the other classes that it uses. This class and its 
	 * methods are work with Strings.
	 *
	 * If the divisor is zero, the divisor is declared to be invalid.
	 * 
	 */
	private void divOperands(){
		if (binaryOperandIssues()) 								// If there are issues, return 
			return;												// without doing anything
		
		setOperand1();
		setOperand1ErrorTerm();
		setOperand2();
		setOperand2ErrorTerm();
		
		if(!unit1.checkIfValidForDivision(unit2) && !(unit1Index==0) && !(unit2Index==0)) {
			text_Result.setText("");
			text_ResultErrorTerm.setText("");
			label_errResult.setText("Units not compatable for division");
			return;
		}

		perform.setOperand1WithConversion(unit1.getMetricMultiplier());
		perform.setOperand2WithConversion(unit2.getMetricMultiplier());
		
		// If the operands are defined and valid, request the business logic method to do the 
		// addition and return the result as a String. If there is a problem with the actual 
		// computation, an empty string is returned. 
		String theAnswer = perform.division();					// The business logic does the add
		String[] answersTokens = theAnswer.split(" ");
		label_errResult.setText("");							// Reset the result error messages
		if (theAnswer.length() > 0) {							// See if a result was returned
			text_Result.setText(answersTokens[0]);				// If so, display it and change the
			Unit result = unit1.getResultantUnitAfterDivision(unit2);
			text_ResultUnit.setText(result.toString());
			if (!text_Operand1ErrorTerm.getText().equals("") || !text_Operand2ErrorTerm.getText().equals("")) {
				text_ResultErrorTerm.setText(answersTokens[1]);
			}
			
			label_Result.setText("Quotient");					// title of the field to "Division"
		}
		else {													// There is no result.
			text_Result.setText("");							// Do not display a result.				
			label_Result.setText("Result");						// Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage());	// Display error message.
		}
	}
	
	/**********
	 * This square root routine is called when the user pressed the square root button. It calls the business logic
	 * class to do the actual work. Notice that this method is really more about doing things with
	 * the user interface. That is, it interacts with the user and delegates all of the computation
	 * work to the business logic class and the other classes that it uses. This class and its 
	 * methods are work with Strings.
	 */
	private void sqrtOperand(){
		text_Operand2.setText("");
		text_Operand2ErrorTerm.setText("");
		if (unaryOperandIssues()) 								// If there are issues, return 
			return;												// without doing anything
		
		setOperand1();
		setOperand1ErrorTerm();

		if(!unit1.checkIfValidForSquareRoot() && !(unit1Index==0)) {
			text_Result.setText("");
			text_ResultErrorTerm.setText("");
			label_errResult.setText("Units not compatable for square root");
			return;
		}
		
		

		// If the operands are defined and valid, request the business logic method to do the 
		// addition and return the result as a String. If there is a problem with the actual 
		// computation, an empty string is returned. 
		String theAnswer = perform.squareroot();					// The business logic does the add
		String[] answersTokens = theAnswer.split(" ");
		label_errResult.setText("");							// Reset the result error messages
		if (theAnswer.length() > 0) {							// See if a result was returned
			text_Result.setText(answersTokens[0]);				// If so, display it and change the
			Unit result = unit1.getResultantUnitAfterSquareRoot();
			text_ResultUnit.setText(result.toString());
			if (!text_Operand1ErrorTerm.getText().equals("")) {
				text_ResultErrorTerm.setText(answersTokens[1]);
			}
			
			label_Result.setText("Root of");					// title of the field to "Root of"
		}
		else {													// There is no result.
			text_Result.setText("");							// Do not display a result.				
			label_Result.setText("Result");						// Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage());	// Display error message.
		}
	}
	
	private void performGo1() {
		String errMessage = perform.getOperand1ErrorMessage();
		String[] tokens = errMessage.split("_");
		if (!tokens[0].equals("")) {
			
			if (tokens.length > 1) {
			label_errOperand1.setText(tokens[0]);
			if (Integer.parseInt(tokens[2]) <= -1) return;
			String input = tokens[1];
			errOperand1MVPart1.setText(input.substring(0, Integer.parseInt(tokens[2])));
			errOperand1MVPart2.setText("\u21EB");
		}
		}
		else {

			String errMessageET = perform.getOperand1ErrorMessageforET();

			String[] tokens1 = errMessageET.split("_");
			if (!tokens1[0].equals("")) {

				if (tokens1.length > 1) {
					label_errOperand1ET.setText(tokens1[0]);

					if (Integer.parseInt(tokens1[2]) <= -1) return ;
					String input1 = tokens1[1];
					errOperand1ETPart1.setText(input1.substring(0, Integer.parseInt(tokens1[2])));
					errOperand1ETPart2.setText("\u21EB");
				}
			}
		}
	
	}
	
	private void performGo2() {
		String errMessage = perform.getOperand2ErrorMessage();
		String[] tokens = errMessage.split("_");
		if (!tokens[0].equals("")) {
			if (tokens.length > 1) {
			label_errOperand2.setText(tokens[0]);
			if (Integer.parseInt(tokens[2]) <= -1) return;
			String input = tokens[1];
			errOperand2MVPart1.setText(input.substring(0, Integer.parseInt(tokens[2])));
			errOperand2MVPart2.setText("\u21EB");
		}
		}
		else {
			System.out.println("reached2");
			String errMessageET = perform.getOperand2ErrorMessageforET();
			System.out.println(errMessageET);
			String[] tokens1 = errMessageET.split("_");
			if (!tokens1[0].equals("")) {
				if (tokens1.length > 1) {
					label_errOperand2ET.setText(tokens1[0]);
					if (Integer.parseInt(tokens1[2]) <= -1) return ;
					String input1 = tokens1[1];
					errOperand2ETPart1.setText(input1.substring(0, Integer.parseInt(tokens1[2])));
					errOperand2ETPart2.setText("\u21EB");
				}
			}			 
		}
		
	}
}
