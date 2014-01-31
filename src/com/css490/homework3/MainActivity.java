
package com.css490.homework3;

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
import android.widget.ToggleButton;

import com.css490.homework3.views.CustomRadioGroup;

public class MainActivity extends Activity implements OnTouchListener, OnEditorActionListener {

    private CustomRadioGroup paceRadioGroup;
    private CustomRadioGroup activitesRadioGroup;

    private EditText timeEditText;
    private EditText weightEditText;

    private RelativeLayout layout;
    private InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paceRadioGroup = (CustomRadioGroup) findViewById(R.id.radiogroup_paces);
        paceRadioGroup.populateButtons();
        activitesRadioGroup = (CustomRadioGroup) findViewById(R.id.radiogroup_activities);
        activitesRadioGroup.populateButtons();

        timeEditText = (EditText) findViewById(R.id.edittext_time);
        timeEditText.setOnEditorActionListener(this);
        weightEditText = (EditText) findViewById(R.id.edittext_weight);
        weightEditText.setOnEditorActionListener(this);

        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        layout = (RelativeLayout) findViewById(R.id.layout_main);
        layout.setOnTouchListener(this);

    }

    public void calculate(View v) {
        ToggleButton selectedPace = paceRadioGroup.getSelectedButton();
        ToggleButton selectedActivity = activitesRadioGroup.getSelectedButton();

        switch(selectedPace.getId()) {
            case R.id.button_slow:
                if(selectedActivity.getId() == R.id.button_slow) {

                } else if(selectedActivity.getId() == R.id.button_walking) {
                    break;
                }
            case R.id.button_medium:
                break;
            case R.id.button_fast:
                break;
        }
    }

    private void calculate() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {
        if (v.getId() == layout.getId()) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return true;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            calculate(null);
        }
        return false;
    }

}
