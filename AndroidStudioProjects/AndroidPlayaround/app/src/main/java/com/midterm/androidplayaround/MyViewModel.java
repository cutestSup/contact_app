package com.midterm.androidplayaround;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> number;
    private MutableLiveData<List<Integer>> items;

    public LiveData<Integer> getNumber(){
        if (number == null){
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }

    public LiveData<List<Integer>> getItems() {
        if (items == null){
            items = new MutableLiveData<List<Integer>>();
            items.setValue(new ArrayList<>());
        }
        return items;
    }

    public void increaseNumber(){
        number.setValue(number.getValue()+1);
    }

    public void decreaseNumber(){
        number.setValue(number.getValue()-1);
    }

    public void addItem(Integer newItem) {
        List<Integer> currentList = items.getValue();
        if (currentList != null) {
            currentList.add(newItem);
            items.setValue(currentList);
        }
    }

    public void removeItem(Integer item) {
        List<Integer> currentList = items.getValue();
        if (currentList != null) {
            currentList.remove(item);
            items.setValue(currentList);
        }
    }
}
