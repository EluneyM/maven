package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.interfaces.UserRepository;
import com.example.models.User;
import com.example.servicios.UserService;

class ApplicationTest {

    @Test
    public void testGetUserNameById() {
        // 1. Crear un mock de UserRepository
        UserRepository mockRepository = mock(UserRepository.class);

        // 2. Configurar el comportamiento del mock
        Long userId = 1L;
        User mockUser = new User(userId, "Betha");
        when(mockRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // 3. Probar UserService
        UserService userService = new UserService(mockRepository);
        String userName = userService.getUserNameById(userId);

        // 4. Verificar resultados
        assertEquals("Betha", userName);
    }

    @Test
    public void testGetUserNameById_UserNotFound() {
        // 1. Crear un mock de UserRepository
        UserRepository mockRepository = mock(UserRepository.class);

        // 2. Configurar el mock para que devuelva un Optional vacío
        Long userId = 2L;
        when(mockRepository.findById(userId)).thenReturn(Optional.empty());

        // 3. Probar UserService
        UserService userService = new UserService(mockRepository);
        String userName = userService.getUserNameById(userId);

        // 4. Verificar resultados
        assertEquals("Usuario no encontrado", userName);
    }
}