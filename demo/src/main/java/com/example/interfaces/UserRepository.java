package com.example.interfaces;

import java.util.Optional;

import com.example.models.User;

public interface UserRepository {
    Optional<User> findById(Long id);
}
