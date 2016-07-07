package com.sheep3.asyn;

import android.os.Message;

import com.sheep3.connect.LoginConnect;

/**
 * Created by sheep3 on 2016/6/16.
 */
public class MessageThread extends Thread {
    MessageHandler handler;

    public MessageThread(MessageHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        Message msg = handler.obtainMessage();
        try {
            LoginConnect.getMessage();
            msg.what = 1;
            handler.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
            msg.what = 0;
            handler.sendMessage(msg);
        }
    }
}
