package com.midterm.contactappv1;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDAO {

    @Query("SELECT * FROM Contact")
    List<Contact> getAll();

    @Query("SELECT * FROM Contact WHERE id IN (:contactIds)")
    List<Contact> loadAllByIds(int[] contactIds);

    @Query("SELECT * FROM contact WHERE name LIKE :name")
    Contact findByName(String name);

    @Insert
    void insert(Contact... contacts);

}
