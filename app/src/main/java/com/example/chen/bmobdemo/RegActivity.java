package com.example.chen.bmobdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class RegActivity extends AppCompatActivity {
    private EditText muser1;
    private EditText mpass1;
    private EditText mphone;
    private EditText mselect;
    private Button mbtn_select;
    private Button mbtn_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
    }

    private void initView() {
        muser1 = (EditText) findViewById(R.id.user);
        mpass1 = (EditText) findViewById(R.id.password);
        mphone = (EditText) findViewById(R.id.phone);
        mselect = (EditText) findViewById(R.id.in_select);
        mbtn_select = (Button) findViewById(R.id.btn_select);

        mbtn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobSMS.requestSMSCode(mphone+"", "Person", new QueryListener<Integer>() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        final String Code = integer.toString();
                        if(e == null){
                            Log.i("smile","短信id:"+integer);
                        }
                    }
                });
            }
        });
        mbtn_reg = (Button) findViewById(R.id.btn_reg);
        mbtn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1 = muser1.getText().toString().trim();
                String pass1 = mpass1.getText().toString().trim();
                String phonenumber1 = mphone.getText().toString().trim();
                String in_select1 = mselect.getText().toString().trim();
                final BmobUser user = new BmobUser();
                user.setUsername(user1);
                user.setPassword(pass1);
                user.setMobilePhoneNumber(phonenumber1);

                user.signUp(new SaveListener<MyUser>() {

                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if(e == null){
                            Toast.makeText(RegActivity.this,"注册成功"+myUser.toString(),Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });






    }

}
