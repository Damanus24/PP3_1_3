package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;

public interface MyUserDetailsService {
    /*Так и не понял зачем нужен этот интерфейс, если я использую в UserDetailsServiceImpl
    методы из коробки JpaRepository, который имплементирует интерфейс UserRepository*/

    public List<User> findAll();

    public User getUser(int id);

    public void saveUser(User user);

    public void updateUser(User updatedUser);

    public void deleteUser(int id);

}
