package com.example.apiclient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends BaseAdapter {

    List<Person> personer;
    MainActivity context;

    public PersonAdapter(List<Person> personer, MainActivity context) {
        this.personer = personer;
        this.context = context;
    }




    @Override
    public int getCount() {
        return personer.size();
    }

    @Override
    public Object getItem(int i) {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    @Override
    public View getView(final int pos, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.person_layout, null);

        Person person = personer.get(pos);

        TextView txtName = v.findViewById(R.id.txtName);
        txtName.setText(person.getName());

        /*TextView txtAge = v.findViewById(R.id.txtAge);
        txtAge.setText(person.getAge());*/

        TextView txtJob = v.findViewById(R.id.txtJob);
        txtJob.setText(person.getJob());
        return v;
    }
}
