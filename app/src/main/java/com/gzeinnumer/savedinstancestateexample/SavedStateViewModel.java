package com.gzeinnumer.savedinstancestateexample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

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
        MutableLiveData<String> v = new MutableLiveData<>();
        v.postValue(query);
        return v;
    }


    public void setQuery(String query) {
        savedStateHandle.set("query", "dari state"+query);
    }

    public LiveData<String> getFilteredData(){
        return filteredData;
    }
}
