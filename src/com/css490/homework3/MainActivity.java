
package com.css490.homework3;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.css490.homework3.util.EditTextValidator;
import com.css490.homework3.views.ButtonGroup;
import com.css490.homework3.views.ResultsDialog;

/**
 * The Main activity, shows the calorie calculator
 * 
 * @author Tim Mikeladze
 */
public class MainActivity extends Activity implements OnTouchListener, OnEditorActionListener {
	
	private ButtonGroup pacesButtonGroup;
	private ButtonGroup activitesButtonGroup;
	
	private EditText timeEditText;
	private EditText weightEditText;
	
	private RelativeLayout layout;
	private InputMethodManager inputMethodManager;
	
	private ResultsDialog resultsDialog;
	
	// @formatter:off
	private final int[][] formulaConstants = {
			// Slow, Medium Fast
			{ 177, 195, 224 }, // Walking
			{ 472, 590, 738 }, // Running 
			{ 354, 472, 590 } // Cycling
	};
	// @formatter:on
	private DecimalFormat decimalFormat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pacesButtonGroup = (ButtonGroup) findViewById(R.id.radiogroup_paces);
		activitesButtonGroup = (ButtonGroup) findViewById(R.id.radiogroup_activities);
		
		timeEditText = (EditText) findViewById(R.id.edittext_time);
		timeEditText.setOnEditorActionListener(this);
		weightEditText = (EditText) findViewById(R.id.edittext_weight);
		weightEditText.setOnEditorActionListener(this);
		
		inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		
		layout = (RelativeLayout) findViewById(R.id.layout_main);
		layout.setOnTouchListener(this);
		
		resultsDialog = new ResultsDialog(this);
		
		decimalFormat = new DecimalFormat("0.00#");
		
	}
	
	/**
	 * Calculates the calories burned and shows a dialog with the results
	 * 
	 * @param v the view clicked
	 */
	public void calculate(View v) {
		inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		if (EditTextValidator.isNumericPositive(timeEditText) && EditTextValidator.isNumericPositive(weightEditText)) {
			double time = Double.parseDouble(timeEditText.getText()
															.toString());
			double weight = Double.parseDouble(weightEditText.getText()
																.toString());
			double results = ((weight * formulaConstants[activitesButtonGroup.getSelectedIndex()][pacesButtonGroup.getSelectedIndex()]) / 130)
					* (time / 60);
			
			resultsDialog.show("You've burned " + decimalFormat.format(results) + " calories");
		}
	}
	
	@Override
	/**
	 * Checks if background is pressed and closes the keyboard
	 */
	public boolean onTouch(View v, MotionEvent e) {
		if (v.getId() == layout.getId()) {
			inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		}
		return true;
	}
	
	@Override
	/**
	 * Calculates the calories and shows a dialog when "Done" clicked on keyboard 
	 */
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			calculate(null);
		}
		return false;
	}
	
}
