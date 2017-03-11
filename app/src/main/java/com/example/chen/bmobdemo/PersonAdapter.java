package com.example.chen.bmobdemo;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by chen on 2017/3/8.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private List<Person> mList;
    private onDelLlistener listener;
    public PersonAdapter(List<Person> mList,onDelLlistener listener){
       this.mList = mList;
        this.listener = listener;
    }
    public void changData(List<Person> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
   class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView headView;
        private TextView nameView;
       private TextView ageView;
       private Button btnDel;
       public ViewHolder(View itemview){
           super(itemview);

           nameView = (TextView) itemview.findViewById(R.id.name);
           ageView = (TextView) itemview.findViewById(R.id.age);
           btnDel = (Button) itemview.findViewById(R.id.btn_del);
           btnDel.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

               }
           });
       }
   }
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personitem,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PersonAdapter.ViewHolder holder, int position) {
        final Person p = mList.get(position);

        holder.nameView.setText(p.getName());
        holder.ageView.setText(String.valueOf(p.getAge()));
        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        listener.refresh();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
