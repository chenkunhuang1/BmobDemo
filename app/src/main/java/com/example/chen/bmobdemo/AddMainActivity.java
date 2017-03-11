package com.example.chen.bmobdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddMainActivity extends AppCompatActivity {
    private Button madd;
    private EditText madd_name;
    private EditText madd_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        madd = (Button) findViewById(R.id.add1);
        madd_name = (EditText) findViewById(R.id.add_name);
        madd_age = (EditText) findViewById(R.id.add_age);
        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name1 = madd_name.getText().toString().trim();
                final int age1 = Integer.parseInt(madd_age.getText().toString().trim());
                Person person=new Person();
                person.setName(name1);
                person.setAge(age1);
                person.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            Intent intent = new Intent();
                            intent.setClass(AddMainActivity.this,MainActivity.class);
                            intent.putExtra("Id",s);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(AddMainActivity.this,"上传失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });
    }
}
