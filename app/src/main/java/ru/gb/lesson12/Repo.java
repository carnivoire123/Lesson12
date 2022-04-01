package ru.gb.lesson12;

import java.util.List;

public interface Repo {
    // CRUD
    // create read update delete
    void add(User user);
    List<User> getAll();
}
