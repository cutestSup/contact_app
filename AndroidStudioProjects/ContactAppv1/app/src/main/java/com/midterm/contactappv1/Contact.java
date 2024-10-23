package com.midterm.contactappv1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String mobileNum;

    @ColumnInfo
    private String email;

    public Contact(String name, String mobileNum, String email) {
        this.name = name;
        this.mobileNum = mobileNum;
        this.email = email;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
