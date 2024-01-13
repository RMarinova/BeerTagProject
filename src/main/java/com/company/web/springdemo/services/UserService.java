package com.company.web.springdemo.services;

import com.company.web.springdemo.models.Beer;
import com.company.web.springdemo.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User get(int id);

    User get(String username);

    List<Beer> addBeerToWishList(int userId, int beerId);

    List<Beer> removeBeerFromWishList(int userId, int beerId);
}
