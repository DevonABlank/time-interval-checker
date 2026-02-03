package umgc.assignments.project3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;

/**
 * Programmer: Devon Blank
 * Date: 12/3/2025
 * Purpose: Implements the JavaFX graphical user interface for Programming Project 4.
 * This class creates the window, text fields, and buttons that allow the user to enter two
 * time intervals and a single time to check. When the buttons are clicked, Project4 constructs
 * Time and Interval<Time> objects, uses their comparison method to determine how the intervals relate,
 * and displays clear messages indicating subinterval, overlap, disjoint status and whether the entered
 * time falls into one or both intervals.
 * */

public class Project4 extends Application {

	//Text fields for interval 1
	private TextField interval1StartField;
	private TextField interval1EndField;

	//Text fields for interval 2
	private TextField interval2StartField;
	private TextField interval2EndField;

	//Text field for the single time check
	private TextField timeToCheckField;

	//Label to display results / messages
	private TextField resultField;

	@Override
	public void start(Stage primaryStage){

		//Header labels
		Label emptyHeader = new Label("");
		Label startHeader = new Label("Start Time");
		Label endHeader = new Label("End Time");

		//Create labels
		Label interval1Label = new Label("Time Interval 1 ");
		Label interval2Label = new Label("Time Interval 2 ");
		Label timeToCheckLabel = new Label("Time to check: ");

		//Text Fields
		interval1StartField = new TextField();
		interval1EndField = new TextField();
		interval2StartField = new TextField();
		interval2EndField = new TextField();
		timeToCheckField = new TextField();

		//Initial Text
		interval1StartField.setText("10:30 AM");
		interval1EndField.setText("12:30 PM");
		interval2StartField.setText("11:05 AM");
		interval2EndField.setText("1:00 PM");
		timeToCheckField.setText("12:50 PM");

		//Buttons
		Button compareIntervalsButton = new Button("Compare Intervals");
		compareIntervalsButton.setMaxWidth(Double.MAX_VALUE);

		Button checkTimeButton = new Button("Check Time");
		checkTimeButton.setMaxWidth(Double.MAX_VALUE);

		//Result label
		resultField = new TextField();
		resultField.setEditable(false);

		//Set up layout with a gridpane
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(15));
		pane.setHgap(10);
		pane.setVgap(10);

		//Make 4 columns so we can center headers and align fields
		ColumnConstraints column0 = new ColumnConstraints();
		ColumnConstraints column1 = new ColumnConstraints();
		ColumnConstraints column2 = new ColumnConstraints();
		ColumnConstraints column3 = new ColumnConstraints();
		pane.getColumnConstraints().addAll(column0, column1, column2, column3);

		GridPane.setHalignment(startHeader, HPos.CENTER);
		GridPane.setHalignment(endHeader, HPos.CENTER);

		//Add components to grid; row, column positions
		//Header row
		pane.add(emptyHeader, 0, 0);
		pane.add(startHeader, 1, 0);
		pane.add(endHeader, 3, 0);

		//Interval 1 row
		pane.add(interval1Label, 0, 1);
		pane.add(interval1StartField, 1, 1);
		pane.add(interval1EndField, 3, 1);

		//Interval 2 row
		pane.add(interval2Label, 0, 2);
		pane.add(interval2StartField, 1, 2);
		pane.add(interval2EndField, 3, 2);

		// Compare button row
		pane.add(compareIntervalsButton, 0, 3, 4, 1);

		//Time to check row
		pane.add(timeToCheckLabel, 0, 4);
		pane.add(timeToCheckField, 1, 4, 3, 1);

		//Check buttons row
		pane.add(checkTimeButton, 0, 5, 4, 1);

		//Result row
		pane.add(resultField, 0, 6, 4, 1);

		//Wire up button actions
		compareIntervalsButton.setOnAction(event -> handleCompareIntervals());
		checkTimeButton.setOnAction(event -> handleCheckTime());

		//Create and show scene
		Scene scene = new Scene(pane, 600, 300);
		primaryStage.setTitle("Time Interval Checker");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Handles the CompareIntervals button click
	 * Compares the two intervals and displays one of:
	 * -"Interval 1 is a subinterval of interval 2"
	 * -"Interval 2 is a subinterval of interval 1"
	 * -"The intervals overlap"
	 * -"The intervals are disjoint"
	 */
	private void handleCompareIntervals() {
		try{
			Interval<Time> interval1 = buildInterval(
					interval1StartField.getText(),
					interval1EndField.getText()
			);

			Interval<Time> interval2 = buildInterval(
					interval2StartField.getText(),
					interval2EndField.getText()
			);

			//Check for subinterval relationships first
			if (interval1.subinterval(interval2)){
				resultField.setText("Interval 2 is a sub-interval of interval 1");
			}else if (interval2.subinterval(interval1)){
				resultField.setText("Interval 1 is a sub-interval of interval 2");
			}else if (interval1.overlaps(interval2)){
				resultField.setText("The intervals overlap");
			}else{
				resultField.setText("The intervals are disjoint");
			}
		}catch (InvalidTime e){
			resultField.setText("Error: " + e.getMessage());
		}catch (IllegalArgumentException e){
			resultField.setText("Error: " + e.getMessage());
		}
	}

	/**
	 * Handles the CheckTime button click
	 * Checks whether the given time is within each interval and displays one of:
	 * - "Both intervals contain the time HH:MM AM/PM"
	 * - "only interval 1 contains the time HH:MM AM/PM"
	 * - "Only Interval 2 contains the time HH:MM AM/PM"
	 * - "Neither interval contains the time HH:MM AM/PM"
	 */

	private void handleCheckTime() {
		try{
			Interval<Time> interval1 = buildInterval(
					interval1StartField.getText(),
					interval1EndField.getText()
			);
			Interval<Time> interval2 = buildInterval(
					interval2StartField.getText(),
					interval2EndField.getText()
			);

			Time timeToCheck = new Time(timeToCheckField.getText());

			boolean inInterval1 = interval1.within(timeToCheck);
			boolean inInterval2 = interval2.within(timeToCheck);

			String timeString = timeToCheck.toString();

			if (inInterval1 && inInterval2){
				resultField.setText("Both intervals contain the time " + timeString);
			} else if (inInterval1){
				resultField.setText("Only Interval 1 contains the time " + timeString);
			} else if (inInterval2){
				resultField.setText("Only Interval 2 contains the time " + timeString);
			} else {
				resultField.setText("Neither interval contains the time " + timeString);
			}
		}catch (InvalidTime e) {
			resultField.setText("Error: " + e.getMessage());
		}catch (IllegalArgumentException e){
			resultField.setText("Error: " + e.getMessage());
		}
	}

/**
 * Helper method to build an Interval<Time> from two strings.
 *
 * @param startString the start time string
 * @param endString the end time string
 * @return an Interval<Time> representing [start, end]
 * @throws InvalidTime if either time string is invalid
 * @throws IllegalArgumentException if start > end
 */

private Interval<Time> buildInterval(String startString, String endString)
	throws InvalidTime, IllegalArgumentException {

	Time startTime = new Time(startString);
	Time endTime = new Time(endString);

	return new Interval<>(startTime, endTime);
	}

	/**
	 * Main method to launch the JavaFX application
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
