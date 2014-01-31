
package com.css490.homework3.views;

import java.util.LinkedList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

/**
 * Custom RadioGroup to be used with ToggleButtons allowing only one ToggleButton to be selected
 * at a time
 * 
 * @author Tim Mikeladze
 */
public class ButtonGroup extends RadioGroup {
	
	private LinkedList<ToggleButton> buttons;
	private ToggleButton selectedButton;
	private int selectedIndex;
	
	/**
	 * Instantiates a new button group.
	 * 
	 * @param context the context
	 * @param attrs the attrs
	 */
	public ButtonGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	/**
	 * Instantiates a new button group.
	 * 
	 * @param context the context
	 */
	public ButtonGroup(Context context) {
		super(context);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		populateButtons();
	}
	
	/**
	 * Toggle buttons. Given a selected button all the other buttons become unselected
	 * 
	 * @param button the button that has been selected
	 */
	private void toggleButtons(ToggleButton button) {
		int i = 0;
		for (ToggleButton tb : buttons) {
			if (tb != button) {
				tb.setChecked(false);
			}
			else if (tb == button) {
				selectedIndex = i;
			}
			i++;
		}
		button.setChecked(true);
		
	}
	
	/**
	 * Adds all child buttons to a LinkedList for future access. Called after view is inflated.
	 * Should be called after dynamically adding buttons to the view to refresh buttons
	 */
	public void populateButtons() {
		buttons = new LinkedList<ToggleButton>();
		for (int i = 0; i < getChildCount(); ++i) {
			final ToggleButton tb = (ToggleButton) getChildAt(i);
			buttons.add(tb);
			if (tb.isChecked()) {
				selectedButton = tb;
				selectedIndex = i;
			}
			tb.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					toggleButtons(tb);
				}
			});
		}
	}
	
	/**
	 * Gets the selected button.
	 * 
	 * @return the selected button
	 */
	public ToggleButton getSelectedButton() {
		return selectedButton;
	}
	
	/**
	 * Gets the selected index.
	 * 
	 * @return the selected index
	 */
	public int getSelectedIndex() {
		return selectedIndex;
	}
	
}
