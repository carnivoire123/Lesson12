package ru.gb.lesson12;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class PreferencesRepo implements Repo {

    private List<User> users;
    private SharedPreferences preferences;
    private static final String USERS_KEY = "USERS_KEY";
    private Gson gson = new Gson();


    private PreferencesRepo()
    {
        // Context: Activity
        // Context: Service
        // Context: Application
        preferences = PreferenceManager.getDefaultSharedPreferences(
                PreferencesApplication.getInstance() // Application Context
        );
        users = getAll();
    }

    private static PreferencesRepo repo;

    public static PreferencesRepo getInstance()
    {
        if(repo == null)
            repo = new PreferencesRepo();

        return repo;
    }

    @Override
    public void add(User user) {
        users.add(user);
        //String data = gson.toJson(users);
        preferences
                .edit()
                //.putString(USERS_KEY, data)
                .putString(USERS_KEY, gson.toJson(users))
                .apply();
    }

    @Override
    public List<User> getAll() {
        String data = preferences.getString(USERS_KEY, "{}");
        try {

            // gson.fromJson(data, User.class);

            users = gson.fromJson(
                    data,
                    new TypeToken<List<User>>(){}.getType()
            );
        }
        catch (Exception e)
        {
            Log.d("happy", "Exception: " + e.getMessage());
        }

        if(users == null)
            users = new ArrayList<>();

        return users;
    }
}
