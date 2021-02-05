package com.gzeinnumer.savedinstancestateexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Main_Activity";

    private SavedStateViewModel vm;
    RecyclerView recyclerView;
    Button btn;
    EditText ed;
    private DummyAdapter dummyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm = new ViewModelProvider(this).get(SavedStateViewModel.class);

        recyclerView = findViewById(R.id.rv);
        btn = findViewById(R.id.btn);
        ed = findViewById(R.id.ed);

        initAdapter();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.setQuery("data 1");
            }
        });
        loadShoppingLists();
    }

    private void loadShoppingLists() {
        //akan tetap terpanggil jika
        vm.getFilteredData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initAdapter() {
        dummyAdapter = new DummyAdapter(new DummyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(dummyAdapter);
    }
}