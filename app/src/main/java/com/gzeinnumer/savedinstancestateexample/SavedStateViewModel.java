package com.gzeinnumer.savedinstancestateexample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SavedStateViewModel extends ViewModel {
    public final String KEY = "Saved_Shopping_List";
    private final SavedStateHandle savedStateHandle;
    public LiveData<List<String>> items;

    public SavedStateViewModel(SavedStateHandle savedStateHandle) {
        this.savedStateHandle = savedStateHandle;
        items = savedStateHandle.getLiveData(KEY, new ArrayList<>());
    }

    public void addItemToShoppingList(String d) {
        items.getValue().add(d);
        savedStateHandle.set(KEY, items.getValue());
    }

    private List<String> generateItems() {
        List<String> list = new ArrayList<String>();

        list.add("Milk");
        list.add("Eggs");
        list.add("Oranges");

        return list;
    }

    public void loadShoppingList() {
        if (items.getValue().isEmpty()) {
            items.getValue().addAll(generateItems());
            savedStateHandle.set(KEY, items.getValue());
        }
    }
}
