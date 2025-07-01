package com.alarmclock.activity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import java.util.Calendar;

public class Alarm_Activity extends AppCompatActivity
                    implements View.OnClickListener{


    private InputMethodManager mInputMethodManager;
    private RelativeLayout mLayout;
    PendingIntent mAlarmSender;
    String place;
    int hour;
    int min;

    /**
     * Alarm_Activityの画面を構成するメソッド
     * @param saveInstanceState
     */
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.alarm);
        
        mLayout = findViewById(R.id.mainLayout);
        //キーボード表示を制御するためのオブジェクト
        mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        //buttonを取得
        Button btnSet = findViewById(R.id.set);
        Button btnCancel = findViewById(R.id.cancel);
        TimePicker tPicker = findViewById(R.id.timePicker);

        btnSet.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        tPicker.setIs24HourView(true);
    }

    /**
     * 画面のタッチイベントメソッド
     * @param event
     * @return
     */
    // EditText編集時に背景をタップしたらキーボードを閉じるようにするタッチイベントの処理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //キーボードを隠す
        // 予測変換表示もソフトキーボードも非表示にする
        mInputMethodManager.hideSoftInputFromWindow(mLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        //背景にフォーカスを移す
        mLayout.requestFocus();
        return false;
    }

    /**
     * クリックイベントメソッド
     * @param v
     */
    @Override
    public void onClick(View v){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        int id = v.getId();

        //アラーム時間設定
        TimePicker tPicker = findViewById(R.id.timePicker);
        hour = tPicker.getCurrentHour();
        min = tPicker.getCurrentMinute();

        //switch (v.getId()){
            if (id == R.id.set) {
                // アラームを設定する
                mAlarmSender = this.getPendingIntent();

                // アラーム時間設定
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(System.currentTimeMillis());
                // 設定した時刻をカレンダーに設定
                cal.set(Calendar.HOUR_OF_DAY, hour);
                cal.set(Calendar.MINUTE, min);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);

                // 過去だったら明日にする
                if (cal.getTimeInMillis() < System.currentTimeMillis()) {
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                }
                bundle.putInt("hour", hour);
                bundle.putInt("min", min);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            } else if(id == R.id.cancel) {
                finish();
            }
    }

    /**
     * アラーム時に起動するアプリケーションを登録
     * @return 起動するアプリケーション
     */
    private PendingIntent getPendingIntent() {
        // アラーム時に起動するアプリケーションを登録
        Intent intent = new Intent(this, Alarm_Stop.class);
        return PendingIntent.getService(this, PendingIntent.FLAG_ONE_SHOT, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}