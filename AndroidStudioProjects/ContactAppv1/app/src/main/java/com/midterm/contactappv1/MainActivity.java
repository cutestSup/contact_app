package com.midterm.contactappv1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.midterm.contactappv1.databinding.ActivityMainBinding;

import java.security.PrivateKey;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private ActivityMainBinding binding;

    private ArrayList<Contact> contactList;
    private ContactAdapter contactAdapter;

    private  ArrayList<Contact> filteredList;
    private AppDatabase appDatabase;
    private ContactDAO contactDAO;

    private MenuHost menuHost;

    private static final int NEW_CONTACT_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
        contactList = new ArrayList<>();
        contactAdapter = new ContactAdapter(contactList);
        binding.rvContacts.setAdapter(contactAdapter);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase = AppDatabase.getInstance(getApplicationContext());
                contactDAO = appDatabase.contactDAO();
                contactList.addAll(contactDAO.getAll());
            }
        });
        contactAdapter.notifyDataSetChanged();

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);

                startActivityForResult(intent, NEW_CONTACT_ACTIVITY_REQUEST_CODE);
            }
        });

//        binding.rvContacts.setOnContextClickListener(new View.OnContextClickListener() {
//            @Override
//            public boolean onContextClick(View view) {
//
//                return true;
//            }
//        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.btn_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                
                filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.exit) {
            finish();
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_CONTACT_ACTIVITY_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                String phone = data.getStringExtra("phone");
                String email = data.getStringExtra("email");
//                contactList.add(new Contact( name,phone,email));
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        appDatabase = AppDatabase.getInstance(getApplicationContext());
                        contactDAO = appDatabase.contactDAO();
                        contactDAO.insert(new Contact(name,phone,email));
                        contactList.clear();
                        contactList.addAll(contactDAO.getAll());
                    }
                });
                contactAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Contact saved", Toast.LENGTH_SHORT).show();

            }
            else {
                contactAdapter.notifyDataSetChanged();
            }
        }
    }

    public void filter(String text) {
        filteredList = new ArrayList<>();

        if (text.isEmpty()) {
            contactList.clear();
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    appDatabase = AppDatabase.getInstance(getApplicationContext());
                    contactDAO = appDatabase.contactDAO();
                    contactList.addAll(contactDAO.getAll());
                }
            });
            contactAdapter.notifyDataSetChanged();
        } else {
            filteredList.clear();

            text = text.toLowerCase();
            for (Contact item : contactList) {
                String tmp = item.getName();
                if (tmp.toLowerCase().contains(text)) {
                    filteredList.add(item);
                }
            }
            contactList.clear();
            contactList.addAll(filteredList);
            contactAdapter.notifyDataSetChanged();

        }


    }





}