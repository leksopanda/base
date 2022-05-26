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

public class ChooseGroupActivity extends AppCompatActivity {
    private RecyclerView chooseGroupRecyclerView;
    public static List<GroupModel> groupsList = new ArrayList<>();
    private ChooseGroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_group);

        chooseGroupRecyclerView = findViewById(R.id.chooseGroupRecyclerView);

        viewGroups();
    }

    private void viewGroups(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(ChooseGroupActivity.this,RecyclerView.VERTICAL, false);
        chooseGroupRecyclerView.setLayoutManager(layoutManager);

        adapter = new ChooseGroupAdapter(groupsList);
        chooseGroupRecyclerView.setAdapter(adapter);
    }
    private void setChooseGroupRecyclerView(List<GroupModel> groupsList){
        LinearLayoutManager layoutManager = new LinearLayoutManager(ChooseGroupActivity.this,RecyclerView.VERTICAL, false);
        chooseGroupRecyclerView.setLayoutManager(layoutManager);

        adapter = new ChooseGroupAdapter(groupsList);
        chooseGroupRecyclerView.setAdapter(adapter);
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