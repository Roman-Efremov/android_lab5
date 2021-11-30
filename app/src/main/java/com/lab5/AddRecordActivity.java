package com.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddRecordActivity extends AppCompatActivity {

    private EditText inputName;
    private EditText inputSurname;
    private Button save;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        inputName = findViewById(R.id.input_name);
        inputSurname = findViewById(R.id.input_surname);
        save = findViewById(R.id.button_save);
        cancel = findViewById(R.id.button_cancel);

        save.setOnClickListener(view -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("NAME", inputName.getText().toString());
            returnIntent.putExtra("SURNAME", inputSurname.getText().toString());
            this.setResult(Activity.RESULT_OK, returnIntent);
            this.finish();
        });

        cancel.setOnClickListener(view -> {
            this.setResult(Activity.RESULT_CANCELED);
            this.finish();
        });
    }
}