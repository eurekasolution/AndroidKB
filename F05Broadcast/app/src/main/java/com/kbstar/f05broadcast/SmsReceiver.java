package com.kbstar.f05broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "SMSReceiver";


    // 문자메시지를 받으면 자동으로 호출
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "----------------------------- onReceive()");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if(messages != null && messages.length>0)
        {
            String sender = messages[0].getOriginatingAddress();
            Log.i(TAG, "------------------------- from : " + sender);

            String msg = messages[0].getMessageBody();
            Log.i(TAG, "------------------------- msg : " + msg);

            Date time = new Date(messages[0].getTimestampMillis());
            Log.i(TAG, "------------------------- time : " + time);

        }



    }

    private SmsMessage[] parseSmsMessage(Bundle bundle)
    {
        //int[] lotto = new int[6];
        Object[] objs = (Object[] ) bundle.get("pdus");
        SmsMessage[] msg = new SmsMessage[objs.length];

        int smsCount = objs.length;

        for(int i=0; i<smsCount; i++) {
            // 단말 OS버전에 따라 사용 메소드가 다름
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                msg[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                msg[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }

        return msg;
    }

}