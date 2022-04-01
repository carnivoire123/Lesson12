package ru.gb.lesson12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText firstName;
    private EditText lastName;
    private Button createUser;
    private TextView userList;

    private Repo repo = PreferencesRepo.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        createUser = findViewById(R.id.save_user);
        userList = findViewById(R.id.user_list);

        createUser.setOnClickListener(this);

        showUsers();

    }

    @Override
    public void onClick(View view) {
        User user = new User(
                firstName.getText().toString(),
                lastName.getText().toString()
        );

        repo.add(user);

        showUsers();

    }

    private void showUsers() {

        StringBuilder builder = new StringBuilder();

        for (User u : repo.getAll())
        {
            builder.append(u.getFirstName());
            builder.append(" ");
            builder.append(u.getLastName());
            builder.append("\n");
        }

        userList.setText(builder.toString());
    }
}