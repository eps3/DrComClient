package com.sheep3.qust;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sheep3.asyn.MessageHandler;
import com.sheep3.asyn.MessageThread;
import com.sheep3.asyn.QuitHandler;
import com.sheep3.asyn.QuitThread;

public class SuccessActivity extends AppCompatActivity {
    public TextView NAME_T;
    public TextView FLUX_T;
    public TextView TIME_T;

    Button QUIT_B;
    Button F5_B;

    QuitHandler quitHandler;
    MessageHandler messageHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        initView();

        quitHandler = new QuitHandler(this);
        messageHandler = new MessageHandler(this);

        MessageThread messageThread = new MessageThread(messageHandler);
        messageThread.start();

        NAME_T.setText(MainActivity.NAME);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 创建退出对话框
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            // 设置对话框标题
            isExit.setTitle("QAQ");
            // 设置对话框消息
            isExit.setMessage("你真的要退出么orz?");
            // 添加选择按钮并注册监听
            isExit.setButton("确定", listener);
            isExit.setButton2("取消", listener);
            // 显示对话框
            isExit.show();

        }
        return false;
    }

    /**
     * 监听对话框里面的button点击事件
     */
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    finish();
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };

    public void initView() {
        NAME_T = (TextView) findViewById(R.id.zhanghao);
        FLUX_T = (TextView) findViewById(R.id.liuliang);
        TIME_T = (TextView) findViewById(R.id.shijian);

        QUIT_B = (Button) findViewById(R.id.tuichu);
        F5_B = (Button) findViewById(R.id.shuaxin);

        QUIT_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuitThread quitThread = new QuitThread(quitHandler);
                quitThread.start();
            }
        });
        F5_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageThread messageThread = new MessageThread(messageHandler);
                messageThread.start();
            }
        });

    }
}
