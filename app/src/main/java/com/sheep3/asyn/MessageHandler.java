package com.sheep3.asyn;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.sheep3.connect.LoginConnect;
import com.sheep3.qust.MainActivity;
import com.sheep3.qust.SuccessActivity;

/**
 * Created by 社ep on 2016/6/16.
 */
public class MessageHandler extends Handler{
    SuccessActivity successActivity;

    public MessageHandler(SuccessActivity successActivity) {
        this.successActivity = successActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                successActivity.FLUX_T.setText(LoginConnect.FLUX_Result);
                successActivity.TIME_T.setText(LoginConnect.TIME_Result);
                break;
            case 0:
                Toast.makeText(successActivity, "刷新有时候会无法显示,待会再刷新吧!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
