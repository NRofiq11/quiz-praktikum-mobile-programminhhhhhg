package com.example.loginform.view;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginform.Contract;
import com.example.loginform.LoginPresenter;
import com.example.loginform.R;
import com.example.loginform.data.LoginCredentials;

public class LoginActivity extends AppCompatActivity implements Contract.LoginView {

    private ProgressDialog progressDialog;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        presenter = new LoginPresenter(this);

        final EditText emailView = findViewById(R.id.email);
        final EditText passwordView = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = emailView.getText().toString();
                String password = passwordView.getText().toString();

                LoginCredentials credentials = new LoginCredentials(email, password);
                presenter.start(credentials);
            }

        });
    }

    @Override
    public void showProgressbar() {
        progressDialog.show();
    }

    @Override
    public void hideProgressbar() {
        progressDialog.dismiss();
    }

    @Override
    public void onSuccess() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onFailed(String messsage) {
        Toast.makeText(this, messsage, Toast.LENGTH_SHORT).show();
    }
}