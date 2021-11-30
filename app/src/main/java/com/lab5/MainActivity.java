package com.lab5;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.lab5.data.AppDatabase;
import com.lab5.data.User;

public class MainActivity extends AppCompatActivity {

    private TextView recordsTotalValue;
    private TextView recordsFoundValue;
    private SearchView searchRecords;
    private Button addRecordButton;
    private AppDatabase db;

    private ActivityResultLauncher<Intent> formActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent resultData = result.getData();
                    if (resultData != null)
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                db.userDao().insert(new User(resultData.getStringExtra("NAME"),
                                        resultData.getStringExtra("SURNAME")));
                            }
                        });
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(getApplicationContext());

        recordsTotalValue = findViewById(R.id.text_records_total_value);
        recordsFoundValue = findViewById(R.id.text_records_found_value);
        searchRecords = findViewById(R.id.search_records);
        addRecordButton = findViewById(R.id.button_add_record);

        db.userDao().getAll().observe(this, users -> {
            recordsTotalValue.setText(String.valueOf(users.size()));
        });

        searchRecords.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String name) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String name) {
                if(!name.equals(""))
                    findByNameOrSurname(db, name);
                else
                    recordsFoundValue.setText("0");
                return true;
            }
        });

        addRecordButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
            formActivityResultLauncher.launch(intent);
        });
    }

    public void findByNameOrSurname(AppDatabase db, String name) {
        db.userDao().findByNameOrSurname(name).observe(this, users -> {
            recordsFoundValue.setText(String.valueOf(users.size()));
        });
    }
}