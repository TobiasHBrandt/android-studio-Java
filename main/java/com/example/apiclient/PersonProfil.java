package com.example.apiclient;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonProfil extends AppCompatActivity {

    EditText txtProfilName, txtProfilJob, txtProfilAge, txtProfilHairColor, txtProfilStudent, txtProfilGender;
    Intent intent;

    Person p;

    Context context = PersonProfil.this;

    static String NAME = "name";
    static String JOB = "job";
    static String AGE = "age";
    static String HAIRCOLOR = "haircolor";
    static String STUDENT = "student";
    static String GENDER = "gender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_profil);

        intent = getIntent();


        txtProfilName = findViewById(R.id.txtProfilName);
        txtProfilJob = findViewById(R.id.txtProfilJob);
        txtProfilAge = findViewById(R.id.txtProfilAge);
        txtProfilHairColor = findViewById(R.id.txtProfilhairColor);
        //txtProfilStudent = findViewById(R.id.txtProfilStudent);
        txtProfilGender = findViewById(R.id.txtProfilGender);

        final Button btnDelete = findViewById(R.id.btnDelete);
        final Button btnAdd = findViewById(R.id.btnAdd);
        //final Button btnUpdate = findViewById(R.id.btnUpdate);
        final Button btnUpdatePerson = findViewById(R.id.btnUpdatePerson);

        final RadioButton rb_True = findViewById(R.id.rb_True);
        final RadioButton rb_False = findViewById(R.id.rb_False);

        final PersonService personService = ServiceBuilder.buildService(PersonService.class);
        //Call<Person>request = personService.getPersonById(1009);
        final int id = intent.getIntExtra("id", 0);
        Call<Person> request = personService.getPersonById(id);


        //get person

        request.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                p = response.body();
                txtProfilName.setText(p.getName());
                txtProfilJob.setText(p.getJob());
                txtProfilAge.setText(String.valueOf(p.getAge()));
                txtProfilHairColor.setText(p.getHaircolor());
                if (p.isStudent()){
                    rb_True.setChecked(true);
                }
                else if (!p.isStudent()){
                    rb_False.setChecked(true);
                }
                txtProfilGender.setText(p.getGender());

                /*btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(PersonProfil.this, UpdateProfil.class);
                        String name = txtProfilName.getText().toString();
                        String job = txtProfilJob.getText().toString();
                        String age = txtProfilAge.getText().toString();
                        String haircolor = txtProfilHairColor.getText().toString();
                        String student = txtProfilStudent.getText().toString();
                        String gender = txtProfilGender.getText().toString();

                        intent.putExtra(NAME, name);
                        intent.putExtra(JOB, job);
                        intent.putExtra(AGE, age);
                        intent.putExtra(HAIRCOLOR, haircolor);
                        intent.putExtra(STUDENT, student);
                        intent.putExtra(GENDER, gender);

                        startActivityForResult(intent, 0);
                    }
                });*/
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                txtProfilName.setText(t.getMessage());

            }
        });


        // delete person

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Delete Person!");
                alert.setMessage("Do you want to delete: " + p.getName() + "?");
               alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Call<Void> request2 = personService.deletePersonById(id);
                       request2.enqueue(new Callback<Void>() {
                           @Override
                           public void onResponse(Call<Void> call, Response<Void> response) {
                               Intent intent = new Intent(PersonProfil.this, MainActivity.class);
                               startActivity(intent);
                               Toast.makeText(context, "Person: " + p.getName() + " is deleted",
                                       Toast.LENGTH_LONG).show();
                               finish();
                           }

                           @Override
                           public void onFailure(Call<Void> call, Throwable t) {

                           }
                       });

                   }
               });
               alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });

               alert.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonProfil.this, AddPerson.class);
                startActivity(intent);
            }
        });


        //update person

        btnUpdatePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = txtProfilName.getText().toString();
                final String job = txtProfilJob.getText().toString();
                final int age = Integer.parseInt(txtProfilAge.getText().toString());
                final String haircolor = txtProfilHairColor.getText().toString();
                //final boolean student = Boolean.parseBoolean(txtUpdateStudent.getText().toString());

                final String gender = txtProfilGender.getText().toString();

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

                final PersonService personService = ServiceBuilder.buildService(PersonService.class);

                Call<Void> request3 = personService.updatePerson(p);



                request3.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        Intent intent = new Intent(PersonProfil.this, MainActivity.class);
                        startActivity(intent);
                        finish();


                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), getString(R.string.update_successful), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });
    }
}