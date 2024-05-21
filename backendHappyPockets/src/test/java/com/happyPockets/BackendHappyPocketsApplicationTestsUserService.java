package com.happyPockets;

import com.happyPockets.api.model.User;
import com.happyPockets.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BackendHappyPocketsApplicationTestsUserService {
	UserService userService = null;

	@BeforeEach
	void getUserService() {
		userService = new UserService();
	}

	@Test
	void UserInDataBaseTest() {
		for (User us : userService.getUserList()) {
			assertTrue(userService.getUserID(us.getId()).isPresent());
			User aux = userService.getUserID(us.getId()).get();
			assertEquals(us.getName(),aux.getName());
		}
	}

	@Test
	void UserNotInDataBaseTest() {
		int maxId = -1;
		for (User us : userService.getUserList()) {
			if (maxId < us.getId()) {
				maxId = us.getId();
			}
		}
		assertFalse(userService.getUserID(maxId+1).isPresent());
	}

	@Test
	void UserLogInTrueTest() {
		for (User us : userService.getUserList()) {
			assertTrue(userService.logIn(us.getUsername(), us.getPassword()));
		}
	}

	@Test
	void UserLogInFalseTest() {
		List<User> userList = userService.getUserList();
		if (userList.isEmpty()) {
			assertFalse(userService.logIn("test", "test"));
		} else {
			for (User us : userService.getUserList()) {
				assertFalse(userService.logIn(us.getUsername(), us.getPassword()+"test"));
				assertFalse(userService.logIn(us.getUsername()+"test", us.getPassword()));
			}
		}
	}

	@Test
	void AddUserFalse() {
		for (User us : userService.getUserList()) {
			assertFalse(userService.addUser(us.getUsername(), us.getPassword(), us.getEmail(), us.getName(), us.getSurname1(), us.getSurname2(), us.getPhone()));
		}
	}

	// Para que la prueba sea mas concisa deberia de poder borrarse el usuario del test para que no se quede en la base de datos
	// Si se queda en la base de datos para la siquiente iteracion fallará
	@Test
	void AddUserTrue() {
		int num = new Random().nextInt();
		if (num < 0)
			num = num*(-1);
		assertTrue(userService.addUser("test"+num, "test1234", "test"+num+"@gmail.com", "test", "test", "test", 123456));
		Set<String> users = new HashSet<>();
		for (User us : userService.getUserList()) {
			users.add(us.getUsername());
		}
		assertTrue(users.contains("test"+num));
	}
}
