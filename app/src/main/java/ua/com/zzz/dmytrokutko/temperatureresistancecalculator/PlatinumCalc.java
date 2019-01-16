package ua.com.zzz.dmytrokutko.temperatureresistancecalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PlatinumCalc extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "PlatinumCalc";

    String spinnerNumber;
    EditText etValue;
    TextView tvResult;
    RadioGroup rGroup, rgAlpha;
    RadioButton rbPtChecked, rbAlphaChecked, rbDefaultPt, rbDefaultAlpha;
    Button btnCalculate;
    Spinner spinner;
    RTDCalc rtdCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.platinum_calc);

        setInitialData();
    }

    private void setInitialData() {
        initView();
        initListener();
    }

    private void initListener() {
        spinner.setOnItemSelectedListener(this);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onClick(View view) {
                onRadioClick();

                String alpha = rbAlphaChecked.getText().toString();
                double R1 = Double.valueOf(etValue.getText().toString());
                int R0;
                double result;

                switch (rbPtChecked.getText().toString()) {
                    case "Pt100":
                        R0 = 100;
                        result = rtdCalc.calculatePlatinum(R1, R0, alpha);
                        result = roundNumber(result);
                        tvResult.setText("Result: " + String.valueOf(result));
                        break;

                    case "Pt500":
                        R0 = 500;
                        result = rtdCalc.calculatePlatinum(R1, R0, alpha);
                        result = roundNumber(result);
                        tvResult.setText("Result: " + String.valueOf(result));
                        break;

                    case "Pt1000":
                        R0 = 1000;
                        result = rtdCalc.calculatePlatinum(R1, R0, alpha);
                        result = roundNumber(result);
                        tvResult.setText("Result: " + String.valueOf(result));
                        break;

                    default:
                        Log.d(TAG, "onClick: Something goes wrong(");
                        break;
                }
            }
        });
    }

    /**
     * Round value
     *
     * @param result
     * @return
     */
    private double roundNumber(double result) {
        double round = result;
        round = new BigDecimal(round).setScale(Integer.valueOf(spinnerNumber), RoundingMode.UP).doubleValue();
        return round;
    }

    /**
     * Init all UI components
     */
    private void initView() {
        etValue = findViewById(R.id.etValue);
        rGroup = findViewById(R.id.rgPt);
        rgAlpha = findViewById(R.id.rgAlpha);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);
        spinner = findViewById(R.id.spinner);
        rtdCalc = new RTDCalc();

        //Init and set default radio buttons
        rbDefaultPt = findViewById(R.id.rb100);
        rbDefaultAlpha = findViewById(R.id.rbAlpha1);
        rbDefaultPt.setChecked(true);
        rbDefaultAlpha.setChecked(true);

        //Array adapter for spinner
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    /**
     * Radio buttons
     */
    public void onRadioClick() {
        int selectedId = rGroup.getCheckedRadioButtonId();
        int selectedAlphaId = rgAlpha.getCheckedRadioButtonId();
        rbPtChecked = findViewById(selectedId);
        rbAlphaChecked = findViewById(selectedAlphaId);
    }

    /**
     * Spinner
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerNumber = adapterView.getItemAtPosition(i).toString().trim();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //...
    }
}
