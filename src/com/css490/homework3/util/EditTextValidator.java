
package com.css490.homework3.util;

import android.widget.EditText;

/**
 * Validates a given EditText using a regular expression or built in functions
 * 
 * @author Tim Mikeladze
 */
public class EditTextValidator {
	
	public static final String REGEX_DECIMAL_POSITIVE = "[-+]?\\d*\\.?\\d+";
	
	/**
	 * Checks if is numeric and positive.
	 * 
	 * @param editText the edit text
	 * @return true, if is numeric
	 */
	public static boolean isNumericPositive(EditText editText) {
		return validate(editText, REGEX_DECIMAL_POSITIVE, "Incorrect value", true);
	}
	
	/**
	 * Validate
	 * 
	 * @param editText the EditText to check
	 * @param regex the regular expression to use
	 * @param message the message to display
	 * @param checkEmpty error on empty inputs
	 * @return true, if validation successful
	 */
	public static boolean validate(EditText editText, String regex, String message, boolean checkEmpty) {
		boolean valid = true;
		String value = editText.getText()
								.toString();
		
		if (checkEmpty && value.isEmpty()) {
			valid = false;
			editText.setError("Missing value");
			
		}
		else if (!value.matches(regex)) {
			valid = false;
			editText.setError("Incorrect value");
			editText.requestFocus();
		}
		return valid;
	}
}
