package ua.com.zzz.dmytrokutko.temperatureresistancecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnPlatinum, btnCopper, btnNickel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialData();
    }

    private void setInitialData() {
        initView();
        initListener();
    }

    private void initListener() {
        btnPlatinum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlatinumCalc.class);
                startActivity(intent);
            }
        });

        btnCopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CopperCalc.class);
                startActivity(intent);
            }
        });

        btnNickel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NickelCalc.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btnPlatinum = findViewById(R.id.btnPlatinum);
        btnCopper = findViewById(R.id.btnCopper);
        btnNickel = findViewById(R.id.btnNickel);
    }
}
