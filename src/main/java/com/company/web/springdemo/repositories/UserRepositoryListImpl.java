//package com.company.web.springdemo.repositories;
//
//import com.company.web.springdemo.exceptions.EntityNotFoundException;
//import com.company.web.springdemo.models.User;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class UserRepositoryImpl implements UserRepository {
//    private final List<User> users;
//
//    public UserRepositoryImpl() {
//        users = new ArrayList<>();
//        User pesho = new User(1, "pesho", true);
//        pesho.setPassword("123456");
//        users.add(pesho);
////        users.add(new User(1, "pesho", true));
//        users.add(new User(2, "vladi", false));
//        users.add(new User(3, "nadya", false));
//    }
//
//    @Override
//    public List<User> getAll() {
//        return new ArrayList<>(users);
//    }
//
//    @Override
//    public User getById(int id) {
//        return users.stream()
//                .filter(user -> user.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new EntityNotFoundException("User", id));
//    }
//
//    @Override
//    public User getByUsername(String username) {
//        return users.stream()
//                .filter(user -> user.getUsername().equals(username))
//                .findFirst()
//                .orElseThrow(() -> new EntityNotFoundException("User", "username", username));
//    }
//}
