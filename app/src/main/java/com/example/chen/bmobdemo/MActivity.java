package com.example.chen.bmobdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MActivity extends AppCompatActivity {
    private Button mlog1;
    private Button mreg;
    private EditText muser2;
    private EditText mpass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);
        initView();

    }

    private void initView() {
        mreg = (Button) findViewById(R.id.reg);
        mreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MActivity.this,RegActivity.class));
            }
        });
        mlog1 = (Button) findViewById(R.id.log);
        muser2 = (EditText) findViewById(R.id.user1);

        mpass2 = (EditText) findViewById(R.id.pass1);

        mlog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(muser2.getText().toString().trim().equals("abc") && mpass2.getText().toString().trim().equals("123")){
                    startActivity(new Intent(MActivity.this,MainActivity.class));
                }else {
                    Toast.makeText(MActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
