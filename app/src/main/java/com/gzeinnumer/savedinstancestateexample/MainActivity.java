package com.gzeinnumer.savedinstancestateexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                String str = ed.getText().toString();
                if (str.length() > 0) {
                    int pos = vm.items.getValue().size();
                    vm.addItemToShoppingList(str);
                    dummyAdapter.notifyItemInserted(pos > 0 ? pos : 0);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadShoppingLists();
    }

    private void loadShoppingLists() {
        vm.loadShoppingList();

        vm.items.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                dummyAdapter.setList(strings);
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