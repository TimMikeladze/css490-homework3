
package com.css490.homework3.views;

import java.util.LinkedList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

public class CustomRadioGroup extends RadioGroup {

    private LinkedList<ToggleButton> buttons;
    private ToggleButton selectedButton;

    public CustomRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRadioGroup(Context context) {
        super(context);
    }

    private void toggleButtons(ToggleButton button) {
        for (ToggleButton tb : buttons) {
            if (tb != button) {
                tb.setChecked(false);
            }
        }
        button.setChecked(true);
    }

    public ToggleButton getSelectedButton() {
        return selectedButton;
    }

    public void populateButtons() {
        buttons = new LinkedList<ToggleButton>();
        for (int i = 0; i < getChildCount(); ++i) {
            final ToggleButton tb = (ToggleButton) getChildAt(i);
            buttons.add(tb);
            if(tb.isSelected()) {
                selectedButton = tb;
            }
            tb.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleButtons(tb);
                }
            });
        }
    }


}
