package com.example.studentsdatebase;

import static com.example.studentsdatebase.ChooseStudentActivity.studentsList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {
    private EditText name,surname,middleName,birthDate, group;
    private Button addStudentBtn;
    public static int countStudents = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        name = findViewById(R.id.studentName);
        surname = findViewById(R.id.studentSurname);
        middleName = findViewById(R.id.studentMiddleName);
        birthDate = findViewById(R.id.studentBirthDate);
        addStudentBtn = findViewById(R.id.addStudentButton);
        group = findViewById(R.id.studentGroup);

        addStudentBtn.setOnClickListener(view -> {
            countStudents++;
            studentsList.add(new StudentModel(name.getText().toString(), surname.getText().toString(), middleName.getText().toString(), birthDate.getText().toString(), group.getText().toString()));
            Toast.makeText(getApplicationContext(), "Студент " + surname.getText().toString() + " " + name.getText().toString() +  " успешно добавлен(а)!", Toast.LENGTH_SHORT).show();
        });
    }
}