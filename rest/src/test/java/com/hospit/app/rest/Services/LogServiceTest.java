package com.hospit.app.rest.Services;
import com.hospit.app.rest.Models.Log;
import com.hospit.app.rest.Repo.LogRepo;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Esta clase contiene pruebas unitarias para la clase LogService, centrándose en los métodos relacionados con logs.
 */
public class LogServiceTest {

    @InjectMocks
    private LogService logService;

    @Mock
    private LogRepo logRepo;

    /**
     * Caso de prueba para verificar que se obtienen correctamente los logs por un ID de usuario válido.
     */
    @Test
    public void testGetLogsByUserId_ValidUserId() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        int userId = 1;
        List<Log> expectedLogs = Arrays.asList(
                new Log(1, null, null, null, null));
        when(logRepo.findByIdUser_IdUser(userId)).thenReturn(expectedLogs);

        // Act
        List<Log> result = logService.getLogsByUserId(userId);

        // Assert
        assertEquals(expectedLogs, result);
    }

    /**
     * Caso de prueba para verificar que se maneja adecuadamente la obtención de logs cuando se proporciona un ID de usuario no válido.
     */
    @Test
    public void testGetLogsByUserId_InvalidUserId() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        int invalidUserId = -1; // Suponiendo que -1 es un ID de usuario no válido
        when(logRepo.findByIdUser_IdUser(invalidUserId)).thenReturn(Collections.emptyList());

        // Act
        List<Log> result = logService.getLogsByUserId(invalidUserId);

        // Assert
        assertTrue(result.isEmpty());
    }

    /**
     * Caso de prueba para verificar que se guarda correctamente un log.
     */
    @Test
    public void testSaveLog() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        Log logToSave = new Log(1, null, null, null, null);
        when(logRepo.save(any(Log.class))).thenReturn(logToSave);

        // Act
        Log result = logService.saveLog(logToSave);

        // Assert
        assertEquals(logToSave, result);
    }

    /**
     * Caso de prueba para verificar que se maneja adecuadamente el intento de guardar un log no válido.
     */
    @Test
    public void testSaveLog_InvalidLog() {
        // Arrange
        MockitoAnnotations.openMocks(this);
        Log invalidLog = new Log(); // Un log no válido con detalles faltantes
        when(logRepo.save(any(Log.class))).thenReturn(null); // Simula el fallo al guardar

        // Act
        Log result = logService.saveLog(invalidLog);

        // Assert
        assertNull(result);
    }
}