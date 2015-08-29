package com.teamnexters.bidulgi.nice;

import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {
	
	NiceSMSCallBack receiver;
	
	public SMSReceiver(NiceSMSCallBack receiver) {
		this.receiver=receiver;
	}
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if ("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) {

			Bundle bundle = intent.getExtras();
			Object messages[] = (Object[])bundle.get("pdus");
			SmsMessage smsMessage[] = new SmsMessage[messages.length];

			for(int i = 0; i < messages.length; i++) {
			    smsMessage[i] = SmsMessage.createFromPdu((byte[])messages[i]);
			}
			String origNumber = smsMessage[0].getOriginatingAddress();

			String message = smsMessage[0].getMessageBody().toString();
			int startIndex = message.indexOf(":[")+2;
			if(origNumber.equals("16001522")){
				receiver.onNiceSMSReceive(message.substring(startIndex,startIndex+6));
			}

		}
	}

}
