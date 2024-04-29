package in.tahiramaan.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText editTextTodo;
    ListView listViewTodo;
    ArrayList<String> todoList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        editTextTodo = findViewById(R.id.editTextTodo);
        listViewTodo = findViewById(R.id.listViewTodo);

        // Load todo list from SharedPreferences
        loadTodoList();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoList);
        listViewTodo.setAdapter(adapter);

        findViewById(R.id.buttonAddTodo).setOnClickListener(v -> {
            String newTodo = editTextTodo.getText().toString().trim();
            if(newTodo.length() > 0){
                todoList.add(newTodo);
                saveTodoList();
                adapter.notifyDataSetChanged();
                editTextTodo.getText().clear();
                Toast.makeText(getApplicationContext(), "Todo added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Empty item, please try again", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void loadTodoList() {
        todoList = new ArrayList<>();
        Set<String> todoSet = sharedPreferences.getStringSet("todoList", new HashSet<String>());
        todoList.addAll(todoSet);
    }

    private void saveTodoList() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("todoList", new HashSet<>(todoList));
        editor.apply();
    }
}