package com.gzeinnumer.savedinstancestateexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private SavedStateViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm = new ViewModelProvider(this).get(SavedStateViewModel.class);

        vm.setQuery("Query nih");

        vm.getFilteredData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG, "onChanged: "+s);
            }
        });
    }
}