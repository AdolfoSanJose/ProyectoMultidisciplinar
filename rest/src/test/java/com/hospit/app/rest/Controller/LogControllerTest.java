package com.hospit.app.rest.Controller;
import com.hospit.app.rest.Models.Log;
import com.hospit.app.rest.Services.LogService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Esta clase contiene pruebas unitarias para la clase LogController, enfocándose específicamente en los métodos relacionados con logs.
 */
public class LogControllerTest {

    @InjectMocks
    private LogController logController;

    @Mock
    private LogService logService;

    /**
     * Caso de prueba para verificar que se obtienen correctamente los logs por ID de usuario.
     */
    @Test
    void testGetLogsByUserId() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        int userId = 1;
        List<Log> expectedLogs = Arrays.asList(new Log(1, null, null, null, null));
        when(logService.getLogsByUserId(userId)).thenReturn(expectedLogs);

        // Act
        ResponseEntity<List<Log>> response = logController.getLogsByUserId(Collections.singletonMap("idUser", userId));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedLogs, response.getBody());
    }

    /**
     * Caso de prueba para verificar que se recibe una respuesta de error de solicitud (BAD REQUEST) cuando no se proporciona el ID de usuario.
     */
    @Test
    void testGetLogsByUserIdBadRequest() {
        // Arrange
        MockitoAnnotations.openMocks(this);

        // Act
        ResponseEntity<List<Log>> response = logController.getLogsByUserId(Collections.emptyMap());

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Caso de prueba para verificar que se recibe una respuesta de error de no encontrado (NOT FOUND) cuando no hay logs para el ID de usuario proporcionado.
     */
    @Test
    void testGetLogsByUserIdNotFound() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        int userId = 1;
        when(logService.getLogsByUserId(userId)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Log>> response = logController.getLogsByUserId(Collections.singletonMap("idUser", userId));

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Caso de prueba para verificar que se guarda correctamente un log.
     */
    @Test
    void testSaveLog() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        Log logToSave = new Log(1, null, null, null, null);
        when(logService.saveLog(any(Log.class))).thenReturn(logToSave);

        // Act
        ResponseEntity<Log> response = logController.saveLog(logToSave);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(logToSave, response.getBody());
    }

    /**
     * Caso de prueba para verificar que se recibe una respuesta de error de solicitud (BAD REQUEST) cuando se intenta guardar un log nulo.
     */
    @Test
    void testSaveLogBadRequest() {
        // Arrange
        MockitoAnnotations.openMocks(this);

        // Act
        ResponseEntity<Log> response = logController.saveLog(null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
