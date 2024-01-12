package com.hospit.app.rest.Controller;
import com.hospit.app.rest.Models.Roles;
import com.hospit.app.rest.Models.User;
import com.hospit.app.rest.Services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Esta clase contiene pruebas unitarias para la clase UserController, centrándose en los métodos relacionados con usuarios.
 */
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    /**
     * Caso de prueba para verificar que se obtienen correctamente todos los usuarios.
     */
    @Test
    void testGetUsers() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        List<User> expectedUsers = Arrays.asList(
                new User(Long.valueOf(1), "name", "test@example.com", "password", "123456789", new Roles(1, "Medico"), null));
        when(userService.getUsers()).thenReturn(new ArrayList<>(expectedUsers));

        // Act
        ArrayList<User> result = userController.getUsers();

        // Assert
        assertEquals(expectedUsers, result);
    }

    /**
     * Caso de prueba para verificar que se obtiene correctamente la información de un usuario por su correo electrónico.
     */
    @Test
    void testGetUserDataByEmail() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        String email = "test@example.com";
        User expectedUserData =  new User(Long.valueOf(1), "name", "test@example.com", "password", "123456789", new Roles(1, "Medico"), null);
        when(userService.getUserDataByEmail(email)).thenReturn(expectedUserData);

        // Act
        ResponseEntity<User> response = userController.getUserDataByEmail(
                Collections.singletonMap("email", email)
        );

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUserData, response.getBody());
    }

    /**
     * Caso de prueba para verificar que se recibe una respuesta de error de solicitud (BAD REQUEST) cuando no se proporciona el correo electrónico del usuario.
     */
    @Test
    void testGetUserDataByEmailBadRequest() {
        // Arrange
        MockitoAnnotations.openMocks(this);

        // Act
        ResponseEntity<User> response = userController.getUserDataByEmail(Collections.emptyMap());

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Caso de prueba para verificar que se recibe una respuesta de error de no encontrado (NOT FOUND) cuando no hay información para el correo electrónico del usuario proporcionado.
     */
    @Test
    void testGetUserDataByEmailNotFound() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        String email = "nonexistent@example.com";
        when(userService.getUserDataByEmail(email)).thenReturn(null);

        // Act
        ResponseEntity<User> response = userController.getUserDataByEmail(
                Collections.singletonMap("email", email)
        );

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Caso de prueba para verificar que se obtiene correctamente la información de los usuarios por su rol.
     */
    @Test
    void testGetUserDataByRole() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        Roles role = new Roles(1,"Medico");
        List<User> expectedUserData = Arrays.asList( new User(Long.valueOf(1), "name", "test@example.com", "password", "123456789", new Roles(1, "Medico"), null));
        when(userService.getUserDataByRoleId(role.getIdRol())).thenReturn(expectedUserData);

        // Act
        ResponseEntity<List<User>> response = userController.getUserDataByRole(role);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUserData, response.getBody());
    }

    /**
     * Caso de prueba para verificar que se recibe una respuesta de error de no encontrado (NOT FOUND) cuando no hay usuarios para el rol proporcionado.
     */
    @Test
    void testGetUserDataByRoleNotFound() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        Roles role = new Roles(/* detalles del rol aquí */);
        when(userService.getUserDataByRoleId(role.getIdRol())).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<User>> response = userController.getUserDataByRole(role);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Caso de prueba para verificar que se registra correctamente un usuario.
     */
    @Test
    void testRegisterUser() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        User userToRegister =  new User(Long.valueOf(1), "name", "test@example.com", "password", "123456789", new Roles(1, "Medico"), null);
        when(userService.registerUser(any(User.class))).thenReturn(userToRegister);

        // Act
        User result = userController.registerUser(userToRegister);

        // Assert
        assertEquals(userToRegister, result);
    }
}