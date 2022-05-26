package com.example.studentsdatebase;

import static com.example.studentsdatebase.ChooseGroupActivity.groupsList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddGroupActivity extends AppCompatActivity {
    private EditText groupId;
    private EditText groupName;
    private Button addGroupBtn;

    public static int countGroups = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        addGroupBtn = findViewById(R.id.addGroupButton);
        groupId = findViewById(R.id.groupId);
        groupName = findViewById(R.id.groupName);

        addGroupBtn.setOnClickListener(view -> {
            countGroups++;
            groupsList.add(new GroupModel(groupId.getText().toString(), groupName.getText().toString()));
            Toast.makeText(getApplicationContext(), "Группа " + groupId.getText().toString() + " успешно добавлена!", Toast.LENGTH_SHORT).show();
        });
    }
}