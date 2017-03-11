package com.example.chen.bmobdemo;

import cn.bmob.v3.BmobUser;

/**
 * Created by chen on 2017/3/11.
 */

public class MyUser extends BmobUser {
    private String user;
    private String password;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
