package ua.com.zzz.dmytrokutko.temperatureresistancecalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PlatinumCalc extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String spinnerValue;
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
        setContentView(R.layout.activity_platinum_calc);

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
                    case "R0 = 100":
                        R0 = 100;
                        result = rtdCalc.calculatePlatinum(R1, R0, alpha, spinnerValue);
                        tvResult.setText("Result: " + String.valueOf(result));
                        break;

                    case "R0 = 500":
                        R0 = 500;
                        result = rtdCalc.calculatePlatinum(R1, R0, alpha, spinnerValue);
                        tvResult.setText("Result: " + String.valueOf(result));
                        break;

                    case "R0 = 1000":
                        R0 = 1000;
                        result = rtdCalc.calculatePlatinum(R1, R0, alpha, spinnerValue);
                        tvResult.setText("Result: " + String.valueOf(result));
                        break;

                    default:
                        Toast.makeText(PlatinumCalc.this, "Error!!!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    /**
     * Init all UI components
     */
    private void initView() {
        etValue = findViewById(R.id.etValuePt);
        rGroup = findViewById(R.id.rgPt);
        rgAlpha = findViewById(R.id.rgAlphaPt);
        btnCalculate = findViewById(R.id.btnCalculatePt);
        tvResult = findViewById(R.id.tvResultPt);
        spinner = findViewById(R.id.spinnerPt);
        rtdCalc = new RTDCalc();

        //Init and set default radio buttons
        rbDefaultPt = findViewById(R.id.rb100Pt);
        rbDefaultAlpha = findViewById(R.id.rbAlpha1Pt);
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
        spinnerValue = adapterView.getItemAtPosition(i).toString().trim();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //...
    }
}
