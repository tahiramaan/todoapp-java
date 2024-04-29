package in.tahiramaan.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText editTextUsername, editTextPassword;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginBtn = findViewById(R.id.buttonLogin);

        loginBtn.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            String savedPassword = sharedPreferences.getString(username, "err");

            if (password.equals(savedPassword)) {
                // Login successful, start TodoActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.textViewRegister).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });
    }
}