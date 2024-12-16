package com.example.servicios;

import com.example.interfaces.UserRepository;
import com.example.models.User;

public class UserService {
    
    private UserRepository userRepository;
    private int queryCount = 0; // Contador de consultas

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserNameById(Long id) {
        queryCount++;
        return userRepository.findById(id)
                .map(User::getName)
                .orElse("Usuario no encontrado");
    }

    public int getQueryCount() {
        return queryCount;
    }
}
