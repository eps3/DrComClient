package com.sheep3.asyn;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.sheep3.qust.MainActivity;
import com.sheep3.qust.SuccessActivity;

/**
 * Created by sheep3 on 2016/6/16.
 */
public class LoginHandler extends Handler {
    MainActivity mainActivity;
    public LoginHandler(MainActivity mainActivity){
        this.mainActivity =mainActivity;
    }
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                Toast.makeText(mainActivity, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mainActivity, SuccessActivity.class);
                mainActivity.startActivity(intent);
                mainActivity.finish();
                break;
            case 0:
                Toast.makeText(mainActivity, "登录失败", Toast.LENGTH_SHORT).show();
                break;
        }
        MainActivity.IS_LOGIN = false;
    }
}
