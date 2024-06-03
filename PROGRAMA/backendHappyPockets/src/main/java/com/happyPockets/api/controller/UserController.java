package com.happyPockets.api.controller;

import com.happyPockets.api.model.Product;
import com.happyPockets.api.model.User;
import com.happyPockets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    /**
     * Endpoint para obtener un usuario por su id.
     * @param id
     * @return el usuario si existe, null en cualquier otro caso.
     */
    @GetMapping("/user")
    public User getUser(@RequestParam Integer id){
        Optional<User> user = userService.getUserID(id);
        if (user.isPresent())
            return user.get();
        System.out.println("consulta getUser " + id);
        return null;
    }

    /**
     * Endpoint para obtener todos los usuarios de la base de datos.
     * @return una lista con todos los usuarios.
     */
    @GetMapping("/users")
    public List<User> getUsers(){
        System.out.println("consulta getUsers");
        return userService.getUserList();
    }

    /**
     * Endpoint para añadir un usuario a la base de datos.
     * @param username
     * @param password
     * @param email
     * @param name
     * @param surname1
     * @param surname2
     * @param phone
     * @return true si se ha podido añadir corectamente, false en cualquier otro caso
     */
    @PostMapping("/addUser")
    public boolean addUser(@RequestParam String username, String password, String email, String name, String surname1, String surname2, int phone){
        System.out.println("consulta addUser");
        return userService.addUser(username, password, email, name, surname1, surname2, phone);
    }

    /**
     * Endpoint para iniciar sesión.
     * @param username
     * @param password
     * @return el user en caso de existir el usuario en la base de datos y la contraseña se correcta.
     *          En cualquier otro caso devolvera peticion fallida.
     */
    @GetMapping("/logIn")
    public ResponseEntity<User> logIn(@RequestParam String username, String password) {
        System.out.println("consulta login: " + username + " username");
        User us = userService.logIn(username, password);
        return us == null ? ResponseEntity.status(HttpStatus.FORBIDDEN).body(null) : ResponseEntity.ok().body(us);
    }
}
