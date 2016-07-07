package com.sheep3.asyn;

import android.os.Message;

import com.sheep3.connect.LoginConnect;

/**
 * Created by sheep3 on 2016/6/16.
 */
public class QuitThread extends Thread {
    QuitHandler handler;

    public QuitThread(QuitHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        Message msg = handler.obtainMessage();
        try {
            boolean quit = LoginConnect.quit();
            if (quit){
                msg.what = 1;
                handler.sendMessage(msg);
            }else {
                msg.what = 0;
                handler.sendMessage(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.what = 0;
            handler.sendMessage(msg);
        }
    }
}
