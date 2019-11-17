package com.example.finalexam07600455;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalexam07600455.db.Database;

//import com.example.testinfinal.db.Database;

public class MainActivity extends AppCompatActivity {
    private Button mLogin;
    private EditText mUsername;
    private EditText mPassword;
    private Button mRegister;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mLogin = (Button) findViewById(R.id.login_button);
        mRegister = (Button) findViewById(R.id.register_button);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkLogin() {
        mUsername = (EditText) findViewById(R.id.username_edit_text);
        mPassword = (EditText) findViewById(R.id.password_edit_text);
        try {
            if (mUsername.length() == 0 && mPassword.length() == 0) {
                Toast.makeText(MainActivity.this, "insert Username or Password please",
                        Toast.LENGTH_LONG).show();
            } else if ((mUsername.length() == 0 || mPassword.length() == 0) ) {
                Toast.makeText(MainActivity.this, "All fields are required",
                        Toast.LENGTH_LONG).show();
            } else if (mUsername.length() >= 0 && mPassword.length() >= 0) {
                Database db = new Database(MainActivity.this);
                if(db.Login(mUsername.toString(), mPassword.toString()))
                    Toast.makeText(MainActivity.this, "Welocome",
                            Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}



