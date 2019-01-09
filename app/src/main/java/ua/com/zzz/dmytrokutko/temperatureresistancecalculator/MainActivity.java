package ua.com.zzz.dmytrokutko.temperatureresistancecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnPlatinum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        onBtnClickListener();
    }

    private void onBtnClickListener() {
        btnPlatinum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlatinumCalc.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        btnPlatinum = findViewById(R.id.btnPlatinum);
    }
}
