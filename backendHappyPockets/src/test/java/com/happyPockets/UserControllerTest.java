package com.happyPockets;

import com.happyPockets.api.controller.UserController;
import com.happyPockets.api.model.User;
import com.happyPockets.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetUser() throws Exception {
        // Configurar el mock para devolver un usuario cuando se busca por ID
        User user = new User(1, "testUser", "password", "mail@test.com", "John", "Doe", "Smith", 123456789);
        when(userService.getUserID(1)).thenReturn(Optional.of(user));

        // Probar el endpoint /user
        mockMvc.perform(get("/user")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1}"));

        // Verificar que el método del servicio se llamó una vez con el parámetro correcto
        verify(userService, times(1)).getUserID(1);
    }

    @Test
    public void testGetUsers() throws Exception {
        // Configurar el mock para devolver una lista de usuarios
        User user1 = new User(1, "testUser", "password", "mail@test.com", "John", "Doe", "Smith", 123456789);
        User user2 = new User(2, "testUser2", "password2", "mail2@test.com", "Jane", "Doe", "Smith", 987654321);
        when(userService.getUserList()).thenReturn(Arrays.asList(user1, user2));

        // Probar el endpoint /users
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1},{\"id\":2}]"));

        // Verificar que el método del servicio se llamó una vez
        verify(userService, times(1)).getUserList();
    }

    @Test
    public void testAddUser() throws Exception {
        // Configurar el mock para devolver true cuando se añade un usuario
        when(userService.addUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyInt())).thenReturn(true);

        // Probar el endpoint /addUser
        mockMvc.perform(post("/addUser")
                        .param("username", "testUser")
                        .param("password", "password")
                        .param("email", "email@example.com")
                        .param("name", "John")
                        .param("surname1", "Doe")
                        .param("surname2", "Smith")
                        .param("phone", "123456789"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        // Verificar que el método del servicio se llamó una vez con los parámetros correctos
        verify(userService, times(1)).addUser("testUser", "password", "email@example.com", "John", "Doe", "Smith", 123456789);
    }

    @Test
    public void testLogIn() throws Exception {
        // Configurar el mock para devolver true cuando se hace login con credenciales correctas
        when(userService.logIn("testUser", "password")).thenReturn(true);

        // Probar el endpoint /logIn
        mockMvc.perform(get("/logIn")
                        .param("username", "testUser")
                        .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        // Verificar que el método del servicio se llamó una vez con los parámetros correctos
        verify(userService, times(1)).logIn("testUser", "password");
    }
}
