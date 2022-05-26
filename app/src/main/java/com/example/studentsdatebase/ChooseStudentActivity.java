package com.example.studentsdatebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ChooseStudentActivity extends AppCompatActivity {
    private RecyclerView chooseStudentRecyclerView;
    public static List<StudentModel> studentsList = new ArrayList<>();
    private ChooseStudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_student);

        chooseStudentRecyclerView = findViewById(R.id.chooseStudentRecyclerView);

        viewStudents();
    }
    private void viewStudents(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(ChooseStudentActivity.this,RecyclerView.VERTICAL, false);
        chooseStudentRecyclerView.setLayoutManager(layoutManager);

        adapter = new ChooseStudentAdapter(studentsList);
        chooseStudentRecyclerView.setAdapter(adapter);
    }
    private void setChooseStudentRecyclerView(List<StudentModel> studentsList){
        LinearLayoutManager layoutManager = new LinearLayoutManager(ChooseStudentActivity.this,RecyclerView.VERTICAL, false);
        chooseStudentRecyclerView.setLayoutManager(layoutManager);

        adapter = new ChooseStudentAdapter(studentsList);
        chooseStudentRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.active_bar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}