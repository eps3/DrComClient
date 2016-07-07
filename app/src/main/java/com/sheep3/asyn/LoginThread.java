package com.sheep3.asyn;

import android.os.Message;

import com.sheep3.connect.LoginConnect;
import com.sheep3.qust.MainActivity;

/**
 * Created by 社ep on 2016/6/16.
 */
public class LoginThread extends Thread {
    LoginHandler handler;
    String name;
    String pwd;
    public LoginThread(LoginHandler handler,String name, String pwd){
        this.name = name;
        this.pwd = pwd;
        this.handler = handler;
    }
    @Override
    public void run() {
        MainActivity.IS_LOGIN=true;
        Message msg = handler.obtainMessage();
        try {
            //LoginConnect.quit();
            boolean login = LoginConnect.login(name, pwd);
            if (login){
                msg.what = 1;
                handler.sendMessage(msg);
            } else {
                msg.what = 0;
                handler.sendMessage(msg);
            }

        } catch (Exception e) {
            msg.what = 0;
            handler.sendMessage(msg);
            e.printStackTrace();
        }
    }
}
