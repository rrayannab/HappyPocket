package com.happyPockets;

import com.happyPockets.api.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Clase dedicada a las pruebas unitarias de cada uno de los metodos
 * de la calse User
 * 		2 pruebas unitarias
 */
public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        // Configuración inicial para cada prueba
        user = new User(1, "test", "test", "test@test.com", "test", "test", "test", 123456789);
    }

    /**
     * Preuba para los metodos getters
     */
    @Test
    public void testGetters() {
        assertEquals(1, user.getId());
        assertEquals("test", user.getUsername());
        assertEquals("test", user.getPassword());
        assertEquals("test@test.com", user.getEmail());
        assertEquals("test", user.getName());
        assertEquals("test", user.getSurname1());
        assertEquals("test", user.getSurname2());
        assertEquals(123456789, user.getPhone());
    }

    /**
     * Preuba para los metodos setter
     */
    @Test
    public void testSetters() {
        user.setId(2);
        user.setUsername("new_test");
        user.setPassword("new_test");
        user.setEmail("new_test@test.com");
        user.setName("new_test");
        user.setSurname1("new_test");
        user.setSurname2("new_test");
        user.setPhone(987654321);

        assertEquals(2, user.getId());
        assertEquals("new_test", user.getUsername());
        assertEquals("new_test", user.getPassword());
        assertEquals("new_test@test.com", user.getEmail());
        assertEquals("new_test", user.getName());
        assertEquals("new_test", user.getSurname1());
        assertEquals("new_test", user.getSurname2());
        assertEquals(987654321, user.getPhone());
    }
}
