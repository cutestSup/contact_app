package com.midterm.twonumbers_linearlayout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<String >> items;

    public LiveData<List<String>> getItems() {
        if (items == null){
            items = new MutableLiveData<List<String >>();
            items.setValue(new ArrayList<>());
        }
        return items;
    }

    public void addItem(String newItem) {
        List<String > currentList = items.getValue();
        if (currentList != null) {
            currentList.add(newItem);
            items.setValue(currentList);
        }
    }

}
