package com.example.gameracing;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int diem = 100;
    SeekBar sbnguoi1, sbnguoi2, sbnguoi3;
    Button btnStart;

    TextView score;

    CheckBox checkBoxGreen, checkBoxRed, checkBoxOrange;


    Random rd = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        score.setText(diem + "");
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBoxGreen.isChecked() || checkBoxRed.isChecked() || checkBoxOrange.isChecked()) {
                    sbnguoi1.setProgress(0);
                    sbnguoi2.setProgress(0);
                    sbnguoi3.setProgress(0);

                    btnStart.setVisibility(View.INVISIBLE);
                    CountDownTimer run = new CountDownTimer(20000, 200) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            int one = rd.nextInt(5);
                            int two = rd.nextInt(5);
                            int three = rd.nextInt(5);

                            if (sbnguoi1.getProgress() >= sbnguoi1.getMax()){
                                this.cancel();
                                Toast.makeText(MainActivity.this, "Green win", Toast.LENGTH_SHORT).show();
                                btnStart.setVisibility(View.VISIBLE);
                                if (checkBoxGreen.isChecked()) {
                                    diem += 10;
                                    Toast.makeText(MainActivity.this, "Dung roi fen", Toast.LENGTH_SHORT).show();
                                }else {
                                    diem -= 5;
                                    Toast.makeText(MainActivity.this, "Sai roi fen", Toast.LENGTH_SHORT).show();
                                }
                                score.setText(diem + "");
                            }
                            if (sbnguoi2.getProgress() >= sbnguoi2.getMax()){
                                this.cancel();
                                Toast.makeText(MainActivity.this, "Red win", Toast.LENGTH_SHORT).show();
                                btnStart.setVisibility(View.VISIBLE);
                                if (checkBoxRed.isChecked()) {
                                    diem += 10;
                                    Toast.makeText(MainActivity.this, "Dung roi fen", Toast.LENGTH_SHORT).show();
                                }else {
                                    diem -= 5;
                                    Toast.makeText(MainActivity.this, "Sai roi fen", Toast.LENGTH_SHORT).show();
                                }
                                score.setText(diem + "");
                            }
                            if (sbnguoi3.getProgress() >= sbnguoi3.getMax()){
                                this.cancel();
                                Toast.makeText(MainActivity.this, "Orange win", Toast.LENGTH_SHORT).show();
                                btnStart.setVisibility(View.VISIBLE);
                                if (checkBoxOrange.isChecked()) {
                                    diem += 10;
                                    Toast.makeText(MainActivity.this, "Dung roi fen", Toast.LENGTH_SHORT).show();
                                }else {
                                    diem -= 5;
                                    Toast.makeText(MainActivity.this, "Sai roi fen", Toast.LENGTH_SHORT).show();
                                }
                                score.setText(diem + "");
                            }

                            sbnguoi1.setProgress(sbnguoi1.getProgress() + one);
                            sbnguoi2.setProgress(sbnguoi2.getProgress() + two);
                            sbnguoi3.setProgress(sbnguoi3.getProgress() + three);
                        }

                        @Override
                        public void onFinish() {

                        }
                    }.start();
                }else{
                    Toast.makeText(MainActivity.this, "Vui long dat cuoc truoc khi choi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        checkBoxGreen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBoxRed.setChecked(false);
                    checkBoxOrange.setChecked(false);
                }
            }
        });
        checkBoxRed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBoxGreen.setChecked(false);
                    checkBoxOrange.setChecked(false);
                }
            }
        });
        checkBoxOrange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBoxRed.setChecked(false);
                    checkBoxGreen.setChecked(false);
                }
            }
        });

    }

    private void AnhXa() {
        btnStart = findViewById(R.id.btn);
        sbnguoi1 = findViewById(R.id.nguoi1);
        sbnguoi2 = findViewById(R.id.nguoi2);
        sbnguoi3 = findViewById(R.id.nguoi3);

        checkBoxGreen = findViewById(R.id.green);
        checkBoxRed = findViewById(R.id.red);
        checkBoxOrange = findViewById(R.id.orange);

        score = findViewById(R.id.score);
    }
}