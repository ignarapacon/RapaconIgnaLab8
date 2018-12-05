package com.rapacon.igna.rapaconignalab8;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference root;

    private EditText ifullname;
    private EditText iage;
    private EditText igender;

    private TextView ofullname;
    private TextView oage;
    private TextView ogender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance();
        root = db.getReference("Users");

        ifullname = findViewById(R.id.etFullName);
        iage = findViewById(R.id.etAge);
        igender = findViewById(R.id.etGender);

        ofullname = findViewById(R.id.tvFullName);
        oage = findViewById(R.id.tvAge);
        ogender = findViewById(R.id.tvGender);

    }

    public void saveInfo(EditText ifullname, EditText iage, EditText igender) {

        String getFullName = ifullname.getText().toString().trim();
        String getAge = iage.getText().toString().trim();
        String getGender = igender.getText().toString().trim();

        User user = new User(getFullName, getAge, getGender);

        Toast.makeText(MainActivity.this, "Saving Successful!", Toast.LENGTH_LONG).show();

        root.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "saving is Successful!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void getInfo() {
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String nameData = dataSnapshot.child("fullname").getValue(String.class);
                    String ageData = dataSnapshot.child("age").getValue(String.class);
                    String genderData = dataSnapshot.child("gender").getValue(String.class);

                    ofullname.setText(nameData);
                    oage.setText(ageData);
                    ogender.setText(genderData);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void Select (View v) {
        if (v.getId() == R.id.btnSave) {
            saveInfo(ifullname, iage, igender);
        }
        if (v.getId() == R.id.btnSearch) {
            getInfo();
        }
    }
}


