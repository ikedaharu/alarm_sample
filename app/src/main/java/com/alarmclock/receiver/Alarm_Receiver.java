package com.alarmclock.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.alarmclock.activity.Alarm_Stop;

public class Alarm_Receiver extends BroadcastReceiver{

    /**
     *
     * @param context
     * @param receivedIntent
     */
    @Override
    public void onReceive(Context context, Intent receivedIntent) {
        //アラームを受け取って起動するActivityを指定、起動
        Intent notification = new Intent(context,Alarm_Stop.class);
        //画面起動に必要
        notification.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(notification);
    }
}
