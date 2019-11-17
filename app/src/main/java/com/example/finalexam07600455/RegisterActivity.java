package com.example.finalexam07600455;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalexam07600455.db.Database;

public class RegisterActivity extends AppCompatActivity {

    private Button mRegister;
    private EditText mfullname;
    private EditText mUsername;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mRegister = (Button)findViewById(R.id.register_button);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRegister();
            }
        });
    }

    private void checkRegister(){
        mfullname = (EditText)findViewById(R.id.full_name_edit_text);
        mUsername = (EditText)findViewById(R.id.username_edit_text);
        mPassword = (EditText)findViewById(R.id.password_edit_text);

        try {
            if ((mUsername.length() == 0 || mPassword.length() == 0) || mfullname.length() == 0){
                Toast.makeText(RegisterActivity.this, "All fields are required",
                        Toast.LENGTH_LONG).show();
            } else if (mUsername.length() > 0 && mPassword.length() > 0 && mfullname.length() > 0 ) {
                Database db = new Database(RegisterActivity.this);
                db.open();
                db.AddUser(mfullname.toString(), mUsername.toString(), mPassword.toString());
                if(db.Login(mUsername.toString(), mPassword.toString()))
                    Toast.makeText(RegisterActivity.this, "Register successfully",
                            Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);

            }
        } catch (Exception e) {
            Toast.makeText(RegisterActivity.this, e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
