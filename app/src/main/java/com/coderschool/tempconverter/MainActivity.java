package com.coderschool.tempconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import static com.coderschool.tempconverter.R.id.editText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();
        addEventListeners();
    }

    private TextView textView;
    private EditText editView;
    private ToggleButton button;

    private void bindUI() {
        textView = (TextView) findViewById(R.id.textView);
        editView = (EditText) findViewById(editText);
        button = (ToggleButton) findViewById(R.id.toggleButton);
    }

    private void addEventListeners() {
        addEditViewListeners();
        addToggleButtonListeners();
    }

    private void addToggleButtonListeners() {
        button.setText("Celsius --> Fahrenheit");
        button.setTextOn("Fahrenheit --> Celsius");
        button.setTextOff("Celsius --> Fahrenheit");

        //TODO: Add event listener to recalcuate conversion.
    }

    private void addEditViewListeners() {
        editView.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                convertTemperature();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    private void convertTemperature() {
        String s = editView.getText().toString();
        if (s.length() == 0) {
            textView.setText(setString(convert(0.0)));
            editView.setHint("0 degrees celsius");
            return;
        }
        Double input = Double.parseDouble(s.toString());
        Double output = convert(input);
        textView.setText(setString(output));
    }

    private String setString(Double input) {
        //%.1f restricts number to 1 decimal point
        return String.format("%.1f degrees fahrenheit", input);
    }

    private Double convert(Double input) {
        if (!button.isChecked()) {
            return (1.8 * input) + 32;
        } else {
            return (input - 32) * (5 / 9);
        }
    }
}
