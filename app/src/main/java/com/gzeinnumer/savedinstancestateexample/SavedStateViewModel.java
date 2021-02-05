package com.gzeinnumer.savedinstancestateexample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SavedStateViewModel extends ViewModel {

    private SavedStateHandle savedStateHandle;
    public LiveData<String> filteredData;
    public SavedStateViewModel(SavedStateHandle savedStateHandle) {
        this.savedStateHandle = savedStateHandle;
        LiveData<String> queryLiveData = savedStateHandle.getLiveData("query");
        filteredData = Transformations.switchMap(queryLiveData, query -> {
            return getFilteredData(query);
        });
    }

    private LiveData<String> getFilteredData(String query) {
        MutableLiveData<String> d = new MutableLiveData<>();

        d.setValue(query + " data 3");
        return d;
    }

    public void setQuery(String query) {
        savedStateHandle.set("query", query);
    }

    public LiveData<String> getFilteredData() {
        return filteredData;
    }
}
