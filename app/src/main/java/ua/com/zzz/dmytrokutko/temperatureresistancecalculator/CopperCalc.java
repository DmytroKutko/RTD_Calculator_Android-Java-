package ua.com.zzz.dmytrokutko.temperatureresistancecalculator;

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

public class CopperCalc extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String spinnerValue;
    EditText etValue;
    TextView tvResult;
    RadioGroup rGroup, rgAlpha;
    RadioButton rbCopChecked, rbAlphaChecked, rbDefaultCop, rbDefaultAlpha;
    Button btnCalculate;
    Spinner spinner;
    RTDCalc rtdCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copper_calc);

        setInitialData();
    }

    private void setInitialData() {
        initView();
        initListener();
    }

    private void initView() {
        etValue = findViewById(R.id.etValueCop);
        rGroup = findViewById(R.id.rgCop);
        rgAlpha = findViewById(R.id.rgAlphaCop);
        btnCalculate = findViewById(R.id.btnCalculateCop);
        tvResult = findViewById(R.id.tvResultCop);
        spinner = findViewById(R.id.spinnerCop);
        rtdCalc = new RTDCalc();

        //Init and set default radio buttons
        rbDefaultCop = findViewById(R.id.rb100Cop);
        rbDefaultAlpha = findViewById(R.id.rbAlpha1Cop);
        rbDefaultCop.setChecked(true);
        rbDefaultAlpha.setChecked(true);

        //Array adapter for spinner
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    private void initListener() {
        spinner.setOnItemSelectedListener(this);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioClick();

                String alpha = rbAlphaChecked.getText().toString();
                double R1 = Double.valueOf(etValue.getText().toString());
                int R0;
                double result;

                switch (rbCopChecked.getText().toString()) {
                    case "R0 = 100":
                        R0 = 100;
                        result = rtdCalc.calculateCopper(R1, R0, alpha, spinnerValue);
                        tvResult.setText("Result: " + String.valueOf(result));
                        break;

                    case "R0 = 500":
                        R0 = 500;
                        result = rtdCalc.calculateCopper(R1, R0, alpha, spinnerValue);
                        tvResult.setText("Result: " + String.valueOf(result));
                        break;

                    case "R0 = 1000":
                        R0 = 1000;
                        result = rtdCalc.calculateCopper(R1, R0, alpha, spinnerValue);
                        tvResult.setText("Result: " + String.valueOf(result));
                        break;

                    default:
                        Toast.makeText(CopperCalc.this, "Error!!!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    public void onRadioClick() {
        int selectedId = rGroup.getCheckedRadioButtonId();
        int selectedAlphaId = rgAlpha.getCheckedRadioButtonId();
        rbCopChecked = findViewById(selectedId);
        rbAlphaChecked = findViewById(selectedAlphaId);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerValue = parent.getItemAtPosition(position).toString().trim();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //...
    }
}
