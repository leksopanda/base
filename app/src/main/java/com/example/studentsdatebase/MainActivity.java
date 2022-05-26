package com.example.studentsdatebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//fsdfs//
public class MainActivity extends AppCompatActivity {
    private Button addGroup, addStudent, readGroup, readStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addGroup = findViewById(R.id.addGroup);
        addStudent = findViewById(R.id.addStudent);
        readGroup = findViewById(R.id.readGroup);
        readStudent = findViewById(R.id.readStudent);

        addGroup.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddGroupActivity.class);
            startActivity(intent);
        });

        addStudent.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivity(intent);
        });

        readGroup.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ChooseGroupActivity.class);
            startActivity(intent);
        });

        readStudent.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ChooseStudentActivity.class);
            startActivity(intent);
        });

    }
}