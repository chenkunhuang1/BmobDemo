package com.example.chen.bmobdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends AppCompatActivity implements onDelLlistener {
    private TextView mtxt;
    private Button mbtn;
    private RecyclerView mrecyclerview;
    private PersonAdapter myAdapter;
    private List<Person> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "46b2ee74eafe9cefd1904503f7e303a3");
        initView();


    }

    private void initView() {
        mrecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        myAdapter = new PersonAdapter(mList, MainActivity.this);
        LinearLayoutManager lm = new LinearLayoutManager(MainActivity.this);
        mrecyclerview.setLayoutManager(lm);
        mrecyclerview.setAdapter(myAdapter);
        mbtn = (Button) findViewById(R.id.btn);
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddMainActivity.class));
            }
        });


    }


    @Override
    public void del(String name) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        queryAll();

    }


    @Override
    public void refresh() {
        queryAll();

    }

    private void queryAll() {
        BmobQuery<Person> query = new BmobQuery<>();
        query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> list, BmobException e) {
                if (e == null) {
                    mList = list;
                    myAdapter.changData(mList);
                    mtxt = (TextView) findViewById(R.id.txt);
                    final Intent intent1 = getIntent();
                    Bundle bundle = intent1.getExtras();
                    final short str = bundle.getShort("ID");

                    BmobQuery<Person> query = new BmobQuery<Person>();


                } else {
                    Log.i("query", e.toString());
                }
            }
        });
    }


}
