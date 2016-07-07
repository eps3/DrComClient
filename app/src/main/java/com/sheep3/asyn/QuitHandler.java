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
public class QuitHandler extends Handler{
    SuccessActivity successActivity;

    public QuitHandler(SuccessActivity successActivity) {
        this.successActivity = successActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                Toast.makeText(successActivity, "退出成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(successActivity, MainActivity.class);
                successActivity.startActivity(intent);
                successActivity.finish();
                break;
            case 0:
                Toast.makeText(successActivity, "退出失败", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
