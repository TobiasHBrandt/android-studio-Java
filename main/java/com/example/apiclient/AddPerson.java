package com.example.apiclient;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class AddPerson extends AppCompatActivity {

    public Person p = new Person();
    PersonService personService = ServiceBuilder.buildService(PersonService.class);
    Context context = AddPerson.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        final EditText txtAddName = findViewById(R.id.txtAddName);
        final EditText txtAddJob = findViewById(R.id.txtAddJob);
        final EditText txtAddAge = findViewById(R.id.txtAddAge);
        final EditText txtAddHairColor = findViewById(R.id.txtAddHairColor);
        //final EditText txtAddStudent = findViewById(R.id.txtAddStudent);
        final EditText txtAddGender = findViewById(R.id.txtAddGender);

        final RadioButton rb_True = findViewById(R.id.rb_True);
        final RadioButton rb_False = findViewById(R.id.rb_False);

        final Button btnAddPerson = findViewById(R.id.btnAddPerson);

        personService = ServiceBuilder.buildService(PersonService.class);


        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(txtAddName.getText()) || TextUtils.isEmpty(txtAddJob.getText()) ||
                        TextUtils.isEmpty(txtAddAge.getText()) || TextUtils.isEmpty(txtAddHairColor.getText()) ||
                        TextUtils.isEmpty(txtAddGender.getText())){
                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Missing fields!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else {
                    final String name = txtAddName.getText().toString();
                    final String job = txtAddJob.getText().toString();
                    final int age = Integer.parseInt(txtAddAge.getText().toString());
                    final String haircolor = txtAddHairColor.getText().toString();
                    final String gender = txtAddGender.getText().toString();

                    p.setName(name);
                    p.setJob(job);
                    p.setAge(age);
                    p.setHaircolor(haircolor);

                    p.setGender(gender);
                    if (!rb_True.isChecked() && !rb_False.isChecked()){
                        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("Missing fields!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                    }
                    else if (rb_True.isChecked()){
                        p.setStudent(true);
                    }
                    else if (rb_False.isChecked()){
                        p.setStudent(false);
                    }
                    Call<Void> request = personService.addPerson(p);
                    request.enqueue(new Callback<Void>() {
                        @SuppressLint("ResourceType")
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Intent intent = new Intent(AddPerson.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            //btnAddPerson.setText(p.name + p.age + p.job + p.haircolor + p.student + p.gender);

                        }
                    });
                }




            }
        });




    }



}