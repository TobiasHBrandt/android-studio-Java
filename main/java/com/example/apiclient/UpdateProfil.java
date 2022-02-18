package com.example.apiclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfil extends AppCompatActivity {
    EditText txtUpdateName, txtUpdateJob, txtUpdateAge, txtUpdateHairColor, txtUpdateStudent, txtUpdateGender;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profil);

        txtUpdateName = findViewById(R.id.txtUpdateName);
        txtUpdateJob = findViewById(R.id.txtUpdateJob);
        txtUpdateAge = findViewById(R.id.txtUpdateAge);
        txtUpdateHairColor = findViewById(R.id.txtUpdatehairColor);
        //txtUpdateStudent = findViewById(R.id.txtUpdateStudent);
        txtUpdateGender = findViewById(R.id.txtUpdateGender);

        final RadioButton rb_True = findViewById(R.id.rb_True);
        final RadioButton rb_False = findViewById(R.id.rb_False);

        final Button btnUpdatePerson = findViewById(R.id.btnUpdatePerson);

        intent = getIntent();
        String editName = intent.getStringExtra(PersonProfil.NAME);
        String editJob = intent.getStringExtra(PersonProfil.JOB);
        String editAge = intent.getStringExtra(PersonProfil.AGE);
        String editHaircolor = intent.getStringExtra(PersonProfil.HAIRCOLOR);
        String editStudent = intent.getStringExtra(PersonProfil.STUDENT);
        String editGender = intent.getStringExtra(PersonProfil.GENDER);


        txtUpdateName.setText(editName);
        txtUpdateJob.setText(editJob);
        txtUpdateAge.setText(editAge);
        txtUpdateHairColor.setText(editHaircolor);
        //txtUpdateStudent.setText(editStudent);
        txtUpdateGender.setText(editGender);



        final PersonService personService = ServiceBuilder.buildService(PersonService.class);


        /*btnUpdatePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Person p = new Person();

                final String name = txtUpdateName.getText().toString();
                final String job = txtUpdateJob.getText().toString();
                final int age = Integer.parseInt(txtUpdateAge.getText().toString());
                final String haircolor = txtUpdateHairColor.getText().toString();
                //final boolean student = Boolean.parseBoolean(txtUpdateStudent.getText().toString());

                final String gender = txtUpdateGender.getText().toString();

                p.setName(name);
                p.setJob(job);
                p.setAge(age);
                p.setHaircolor(haircolor);
                if (rb_True.isChecked()){
                    p.setStudent(true);
                }
                else if (rb_False.isChecked()){
                    p.setStudent(false);
                }
                p.setGender(gender);

                Call<Void> request = personService.updatePerson(p);



                request.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {



                       *//* p.setName(txtUpdateName.getText().toString());
                        p.setJob(txtUpdateJob.getText().toString());
                        p.setAge(Integer.parseInt(txtUpdateAge.getText().toString()));
                        p.setHaircolor(txtUpdateHairColor.getText().toString());
                        p.setGender(txtUpdateGender.getText().toString());*//*

                        *//*txtUpdateName.setText(p.getName());
                        txtUpdateJob.setText(p.getJob());
                        txtUpdateAge.setText("" + p.getAge());
                        txtUpdateHairColor.setText(p.getHaircolor());
                        txtUpdateStudent.setText("" + p.isStudent());
                        txtUpdateGender.setText(p.getGender());*//*


                        //btnUpdatePerson.getId();




                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), getString(R.string.update_successful), Toast.LENGTH_LONG).show();
                        }



                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });*/
        //Call<Person>request = personService.getPersonById(1009);
        //final int id = intent.getIntExtra("id", 0);

    }
}