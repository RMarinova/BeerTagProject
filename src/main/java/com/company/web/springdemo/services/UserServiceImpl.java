package com.company.web.springdemo.services;

import com.company.web.springdemo.models.Beer;
import com.company.web.springdemo.models.User;
import com.company.web.springdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BeerService beerService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BeerService beerService) {
        this.userRepository = userRepository;
        this.beerService = beerService;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User get(int id) {
        return userRepository.get(id);
    }

    @Override
    public User get(String username) {
        return userRepository.get(username);
    }

    @Override
    public List<Beer> addBeerToWishList(int userId, int beerId) {

        User user = userRepository.get(userId);
        Beer beer = beerService.get(beerId);
        if (user.getWishList().add(beer)) {
            userRepository.update(user);
        }

        return new ArrayList<>(user.getWishList());
    }

    @Override
    public List<Beer> removeBeerFromWishList(int userId, int beerId) {
        User user = userRepository.get(userId);
        Beer beer = beerService.get(beerId);
        if (user.getWishList().remove(beer)) {
            userRepository.update(user);
        }
        return new ArrayList<>(user.getWishList());
    }
}
