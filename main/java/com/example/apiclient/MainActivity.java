package com.example.apiclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView txtTest;
    ListView lstPerson;
    List<Person> personer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTest = findViewById(R.id.txtTest);
        lstPerson = findViewById(R.id.lstPerson);

        FloatingActionButton fabAddPerson = (FloatingActionButton) findViewById(R.id.btnAddPerson);

        final PersonService personService = ServiceBuilder.buildService(PersonService.class);
        /*Call<Person> request = personService.getPersonById(1009);



        request.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Person person = response.body();
                //txtName.setText(person.getName());

            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                txtTest.setText(t.getMessage());

            }
        });*/

        fabAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPerson.class);
                startActivity(intent);
            }
        });

        // getAllPersons
        Call<List<Person>> request2 = personService.getAllPerson();
        request2.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {

                personer = response.body();

                PersonAdapter adapter = new PersonAdapter(personer, MainActivity.this);
                lstPerson.setAdapter(adapter);

                lstPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {


                        Person person = personer.get(pos);
                        int id = person.getId();
                        Intent intent = new Intent(MainActivity.this, PersonProfil.class);
                        intent.putExtra("id", id);

                        startActivity(intent);
                    }
                });

            }



            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {

            }
        });




       /* Person p = new Person(1000,"Torben", "Arbejdsløs", 56, "Blåt", false, "D");
        Call<Void> request3 = personService.addPerson(p);
        request3.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                //txtName.setText("Person indsat");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                txtName.setText(t.getMessage());
            }
        });*/
    }
}