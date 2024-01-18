package com.hospit.app.rest.Services;
import com.hospit.app.rest.Models.Roles;
import com.hospit.app.rest.Models.User;
import com.hospit.app.rest.Repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Esta clase contiene pruebas unitarias para la clase UserService, centrándose en los métodos relacionados con usuarios.
 */
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepo userRepo;

    /**
     * Caso de prueba para verificar que se obtienen correctamente todos los usuarios.
     */
    @Test
    public void testGetUsers() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        ArrayList<User> expectedUsers = new ArrayList<>(Arrays.asList(
                new User(Long.valueOf(1), "name", "test@example.com", "password", "123456789", new Roles(1, "Medico"), null)
        ));
        when(userRepo.findAll()).thenReturn(expectedUsers);

        // Act
        ArrayList<User> result = userService.getUsers();

        // Assert
        assertEquals(expectedUsers, result);
    }

    /**
     * Caso de prueba para verificar que se obtiene correctamente la información de un usuario por su correo electrónico.
     */
    @Test
    public void testGetUserDataByEmail_ValidEmail() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        String userEmail = "user@example.com";
        User expectedUser = new User(Long.valueOf(1), "name", "test@example.com", "password", "123456789", new Roles(1, "Medico"), null);
        when(userRepo.findByEmail(userEmail)).thenReturn(expectedUser);

        // Act
        User result = userService.getUserDataByEmail(userEmail);

        // Assert
        assertEquals(expectedUser, result);
    }

    /**
     * Caso de prueba para verificar que se recibe un resultado nulo cuando se busca la información de un usuario con un correo electrónico no válido.
     */
    @Test
    public void testGetUserDataByEmail_InvalidEmail() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        String invalidEmail = "invalid@example.com";
        when(userRepo.findByEmail(invalidEmail)).thenReturn(null);

        // Act
        User result = userService.getUserDataByEmail(invalidEmail);

        // Assert
        assertNull(result);
    }

    /**
     * Caso de prueba para verificar que se obtiene correctamente la información de los usuarios por el ID de su rol.
     */
    @Test
    public void testGetUserDataByRoleId() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        int roleId = 1;
        List<User> expectedUsers = Arrays.asList(
                new User(Long.valueOf(1), "name", "test@example.com", "password", "123456789", new Roles(1, "Medico"), null)
        );
        when(userRepo.findByIdRol_IdRol(roleId)).thenReturn(expectedUsers);

        // Act
        List<User> result = userService.getUserDataByRoleId(roleId);

        // Assert
        assertEquals(expectedUsers, result);
    }

    /**
     * Caso de prueba para verificar que se registra correctamente un usuario.
     */
    @Test
    public void testRegisterUser() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        User userToRegister = new User(Long.valueOf(1), "name", "test@example.com", "password", "123456789", new Roles(1, "Medico"), null);
        when(userRepo.save(any(User.class))).thenReturn(userToRegister);

        // Act
        User result = userService.registerUser(userToRegister);

        // Assert
        assertEquals(userToRegister, result);
    }

    /**
     * Caso de prueba para verificar que se valida correctamente un correo electrónico con credenciales válidas.
     */
    @Test
    public void testIsValidEmail_ValidCredentials() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        String userEmail = "user@example.com";
        String validPassword = "password";
        User userWithValidCredentials = new User(Long.valueOf(1), "name", userEmail, validPassword, "123456789", new Roles(1, "Medico"), null);
        when(userRepo.findByEmail(userEmail)).thenReturn(userWithValidCredentials);

        // Act
        boolean result = userService.isValidEmail(userEmail, validPassword);

        // Assert
        assertTrue(result);
    }

    /**
     * Caso de prueba para verificar que se invalida correctamente un correo electrónico con credenciales no válidas.
     */
    @Test
    public void testIsValidEmail_InvalidCredentials() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        String userEmail = "user@example.com";
        String invalidPassword = "wrongpassword";
        when(userRepo.findByEmail(userEmail)).thenReturn(null);

        // Act
        boolean result = userService.isValidEmail(userEmail, invalidPassword);

        // Assert
        assertFalse(result);
    }
}