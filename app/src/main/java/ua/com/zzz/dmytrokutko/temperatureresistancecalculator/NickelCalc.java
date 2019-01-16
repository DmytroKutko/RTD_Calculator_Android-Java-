package ua.com.zzz.dmytrokutko.temperatureresistancecalculator;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class NickelCalc extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String spinnerValue;
    EditText etValue;
    TextView tvResult;
    RadioGroup rGroup;
    RadioButton rbNicChecked, rbDefaultNic;
    Button btnCalculate;
    Spinner spinner;
    RTDCalc rtdCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickel_calc);
        
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

                double R1 = Double.valueOf(etValue.getText().toString());
                int R0;
                double result;

                switch (rbNicChecked.getText().toString()) {
                    case "R0 = 100":
                        R0 = 100;
                        result = rtdCalc.calculateNickel(R1, R0, spinnerValue);
                        tvResult.setText("Result: " + String.valueOf(result));
                        break;

                    case "R0 = 500":
                        R0 = 500;
                        result = rtdCalc.calculateNickel(R1, R0, spinnerValue);
                        tvResult.setText("Result: " + String.valueOf(result));
                        break;

                    case "R0 = 1000":
                        R0 = 1000;
                        result = rtdCalc.calculateNickel(R1, R0, spinnerValue);
                        tvResult.setText("Result: " + String.valueOf(result));
                        break;

                    default:
                        Toast.makeText(NickelCalc.this, "Error!!!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void initView() {
        etValue = findViewById(R.id.etValueNic);
        rGroup = findViewById(R.id.rgNic);
        btnCalculate = findViewById(R.id.btnCalculateNic);
        tvResult = findViewById(R.id.tvResultNic);
        spinner = findViewById(R.id.spinnerNic);
        rtdCalc = new RTDCalc();

        //Init and set default radio buttons
        rbDefaultNic = findViewById(R.id.rb100Nic);
        rbDefaultNic.setChecked(true);

        //Array adapter for spinner
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public void onRadioClick() {
        int selectedId = rGroup.getCheckedRadioButtonId();
        rbNicChecked = findViewById(selectedId);
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
