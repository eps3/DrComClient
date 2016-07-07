package com.sheep3.qust;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.sheep3.asyn.LoginHandler;
import com.sheep3.asyn.LoginThread;
import com.sheep3.asyn.MessageHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    public static boolean IS_LOGIN = false;
    public static String NAME;
    public static String PWD;
    Button LOGIN_B;
    EditText NAME_E;
    EditText PWD_E;
    CheckBox REM_C;

    LoginHandler handler;
    MessageHandler messageHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        try {
            readUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initView() {
        handler = new LoginHandler(this);

        LOGIN_B = (Button) findViewById(R.id.login);
        NAME_E = (EditText) findViewById(R.id.name);
        PWD_E = (EditText) findViewById(R.id.pwd);
        REM_C = (CheckBox) findViewById(R.id.rem_pwd);
        LOGIN_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IS_LOGIN){
                    Toast.makeText(MainActivity.this, "正在登录中,请等待一会哦~", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (REM_C.isChecked()){
                    try {
                        remUser();
                    } catch (IOException e) {
                        System.out.println("读取有错!");
                    }
                }
                NAME = NAME_E.getText().toString();
                PWD = PWD_E.getText().toString();
                LoginThread loginThread = new LoginThread(handler,NAME,PWD);
                loginThread.start();

            }
        });
    }

    public void remUser() throws IOException {
        NAME = NAME_E.getText().toString();
        PWD = PWD_E.getText().toString();
        //持久化保存数据
        File file = new File(getFilesDir().getPath()+"/user.txt");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write((NAME + "##" + PWD).getBytes());
        fos.close();
    }

    public void readUser() throws IOException {
        File file = new File(getFilesDir().getPath()+"/user.txt");
        if(file.exists()){
            FileInputStream fis = new FileInputStream(file);
            //把字节流转换成字符流
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String text = br.readLine();
            String[] s = text.split("##");
            if (s.length==2){
                REM_C.setChecked(true);
                NAME_E.setText(s[0]);
                PWD_E.setText(s[1]);
            }
        }

    }
}
