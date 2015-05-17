package com.kangsoo.pharmacy.receiver;

import android.content.Context;

import com.kangsoo.pharmacy.broadcast.AbstractBroadcastHandler;
import com.kangsoo.pharmacy.broadcast.NotificationBroadcastHandler;

public final class BackgroundScanReceiver extends AbstractScanBroadcastReceiver {

    @Override
    protected AbstractBroadcastHandler createBroadcastHandler(Context context) {
        return new NotificationBroadcastHandler(context);
    }
}
