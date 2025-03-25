package com.example.gameracing;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RunActivity extends AppCompatActivity {
    private SeekBar sbRacer1, sbRacer2, sbRacer3;
    private CheckBox cbRacer1, cbRacer2, cbRacer3;
    private Button btnStart, btnRestart;
    private TextView tvScore;
    private int score = 100;
    private int selectedRacer = 0;
    private boolean gameRunning = false;
    private Random random = new Random();
    private CountDownTimer raceTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);

        sbRacer1 = findViewById(R.id.sb_racer1);
        sbRacer2 = findViewById(R.id.sb_racer2);
        sbRacer3 = findViewById(R.id.sb_racer3);
        cbRacer1 = findViewById(R.id.cb_racer1);
        cbRacer2 = findViewById(R.id.cb_racer2);
        cbRacer3 = findViewById(R.id.cb_racer3);
        btnStart = findViewById(R.id.btn_start);
        btnRestart = findViewById(R.id.btn_restart);
        tvScore = findViewById(R.id.tv_score);

        sbRacer1.setEnabled(false);
        sbRacer2.setEnabled(false);
        sbRacer3.setEnabled(false);
        btnRestart.setVisibility(View.GONE);

        // Đảm bảo chỉ chọn được 1 checkbox
        cbRacer1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbRacer2.setChecked(false);
                cbRacer3.setChecked(false);
            }
        });

        cbRacer2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbRacer1.setChecked(false);
                cbRacer3.setChecked(false);
            }
        });

        cbRacer3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbRacer1.setChecked(false);
                cbRacer2.setChecked(false);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRace();
            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    private void startRace() {
        if (gameRunning) return;

        // Kiểm tra checkbox đã được chọn chưa
        if (cbRacer1.isChecked()) {
            selectedRacer = 1;
        } else if (cbRacer2.isChecked()) {
            selectedRacer = 2;
        } else if (cbRacer3.isChecked()) {
            selectedRacer = 3;
        } else {
            Toast.makeText(this, "Bạn cần chọn một nhân vật để đặt cược!", Toast.LENGTH_SHORT).show();
            return;
        }

        gameRunning = true;
        btnStart.setEnabled(false);
        cbRacer1.setEnabled(false);
        cbRacer2.setEnabled(false);
        cbRacer3.setEnabled(false);

        sbRacer1.setProgress(0);
        sbRacer2.setProgress(0);
        sbRacer3.setProgress(0);

        raceTimer = new CountDownTimer(10000, 200) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (!gameRunning) {
                    cancel();
                    return;
                }

                sbRacer1.setProgress(sbRacer1.getProgress() + random.nextInt(5) + 1);
                sbRacer2.setProgress(sbRacer2.getProgress() + random.nextInt(5) + 1);
                sbRacer3.setProgress(sbRacer3.getProgress() + random.nextInt(5) + 1);

                if (sbRacer1.getProgress() >= 100 || sbRacer2.getProgress() >= 100 || sbRacer3.getProgress() >= 100) {
                    gameRunning = false;
                    checkWinner();
                    cancel();
                }
            }

            @Override
            public void onFinish() {
                if (gameRunning) {
                    gameRunning = false;
                    checkWinner();
                }
            }
        }.start();
    }

    private void checkWinner() {
        int winner = -1;

        if (sbRacer1.getProgress() >= 100) winner = 1;
        else if (sbRacer2.getProgress() >= 100) winner = 2;
        else if (sbRacer3.getProgress() >= 100) winner = 3;

        if (winner == selectedRacer) {
            score += 10;
            Toast.makeText(this, "Nhân vật " + winner + " thắng! Bạn được cộng 10 điểm!", Toast.LENGTH_LONG).show();
        } else {
            score -= 5;
            Toast.makeText(this, "Nhân vật " + winner + " thắng! Bạn bị trừ 5 điểm!", Toast.LENGTH_LONG).show();
        }

        tvScore.setText("Điểm: " + score);
        btnRestart.setVisibility(View.VISIBLE);
    }

    private void resetGame() {
        sbRacer1.setProgress(0);
        sbRacer2.setProgress(0);
        sbRacer3.setProgress(0);

        cbRacer1.setEnabled(true);
        cbRacer2.setEnabled(true);
        cbRacer3.setEnabled(true);

        cbRacer1.setChecked(false);
        cbRacer2.setChecked(false);
        cbRacer3.setChecked(false);

        btnStart.setEnabled(true);
        btnRestart.setVisibility(View.GONE);
        selectedRacer = -1;
        gameRunning = false;
    }
}
