package com.example;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.interfaces.UserRepository;
import com.example.models.User;
import com.example.servicios.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceSpyTest {

    @Test
    public void testGetUserNameByIdWithSpy() {
        // 1. Crear un objeto real de UserRepository y convertirlo en un spy
        UserRepository realRepository = new UserRepository() {
            @Override
            public Optional<User> findById(Long id) {
                // Simulamos comportamiento real básico
                return Optional.empty();
            }
        };
        UserRepository spyRepository = spy(realRepository);

        // 2. Configurar un comportamiento simulado en el spy
        Long userId = 1L;
        User mockUser = new User(userId, "Betha");
        when(spyRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // 3. Probar UserService con el spy
        UserService userService = new UserService(spyRepository);
        String userName = userService.getUserNameById(userId);

        // 4. Verificar resultados y comportamiento
        assertEquals("Betha", userName); // Verificamos el resultado
        verify(spyRepository).findById(userId); // Verificamos que se llamó al método del repositorio
        assertEquals(1, userService.getQueryCount()); // Verificamos el contador de consultas
    }

    @Test
    public void testGetUserNameById_UserNotFoundWithSpy() {
        // 1. Crear un spy de UserRepository
        UserRepository realRepository = new UserRepository() {
            @Override
            public Optional<User> findById(Long id) {
                // Simulamos comportamiento real básico
                return Optional.empty();
            }
        };
        UserRepository spyRepository = spy(realRepository);

        // 2. No necesitamos configurar comportamiento adicional, se usará el real

        // 3. Probar UserService con el spy
        UserService userService = new UserService(spyRepository);
        String userName = userService.getUserNameById(2L);

        // 4. Verificar resultados y comportamiento
        assertEquals("Usuario no encontrado", userName); // Verificamos el resultado
        verify(spyRepository).findById(2L); // Verificamos que se llamó al método del repositorio
        assertEquals(1, userService.getQueryCount()); // Verificamos el contador de consultas
    }
}
