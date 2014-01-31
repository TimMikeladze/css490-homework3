
package com.css490.homework3.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Displays calories burned in a dialog
 * 
 * @author Tim Mikeladze
 */
public class ResultsDialog {
	
	private static final String TITLE = "";
	private static final String BUTTON_NEUTRAL_TEXT = "Okay";
	private AlertDialog alertDialog;
	
	/**
	 * Instantiates a new results dialog.
	 * 
	 * @param context the context
	 */
	public ResultsDialog(Context context) {
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(TITLE);
		alertDialog.setCancelable(true);
		alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, BUTTON_NEUTRAL_TEXT, (DialogInterface.OnClickListener) null);
	}
	
	/**
	 * Shows the dialog
	 * 
	 * @param results the results to show in the dialog
	 */
	public void show(String results) {
		alertDialog.setMessage(results);
		alertDialog.show();
	}
}
