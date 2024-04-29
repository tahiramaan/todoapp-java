package in.tahiramaan.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText editTextNewUsername, editTextNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        editTextNewUsername = findViewById(R.id.editTextNewUsername);
        editTextNewPassword = findViewById(R.id.editTextNewPassword);

        findViewById(R.id.buttonRegister).setOnClickListener(v -> {
            String username = editTextNewUsername.getText().toString();
            String password = editTextNewPassword.getText().toString();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(username, password);
            editor.apply();

            Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();

            finish(); // Finish registration activity and go back to login
        });
    }
}