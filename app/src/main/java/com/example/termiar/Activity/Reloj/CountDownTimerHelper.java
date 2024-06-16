package com.example.termiar.Activity.Reloj;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.termiar.R;

public class CountDownTimerHelper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_count_down_timer_helper);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private static final String PREF_TIME_LEFT = "timeLeftInMillis";

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private SharedPreferences prefs;

    public CountDownTimerHelper(SharedPreferences prefs) {
        this.prefs = prefs;
        timeLeftInMillis = prefs.getLong(PREF_TIME_LEFT, 600000); // Valor por defecto: 10 minutos en milisegundos
    }

    public void startTimer(CountDownListener listener) {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                listener.onTick(timeLeftInMillis);
            }

            @Override
            public void onFinish() {
                listener.onFinish();
            }
        }.start();
    }

    public void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void saveTimerState() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(PREF_TIME_LEFT, timeLeftInMillis);
        editor.apply();
    }

    public interface CountDownListener {
        void onTick(long millisUntilFinished);

        void onFinish();
    }

    public long getTimeLeftInMillis() {
        return timeLeftInMillis;
    }

}
