package com.happyPockets;

import com.happyPockets.api.model.User;
import com.happyPockets.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BackendHappyPocketsApplicationTests {

	@Test
	void UserInDataBaseTest() {
		UserService userService = new UserService();
		for (User us : userService.getUserList()) {
			assert(userService.getUserID(us.getId()).isPresent());
			User aux = userService.getUserID(us.getId()).get();
			assertEquals(us.getName(),aux.getName());
		}
	}

	@Test
	void UserNotInDataBaseTest() {
		UserService userService = new UserService();
		int maxId = -1;
		for (User us : userService.getUserList()) {
			if (maxId < us.getId()) {
				maxId = us.getId();
			}
		}
		assertEquals(userService.getUserID(maxId+1).isPresent(), false);
	}

	@Test
	void UserLogInTrueTest() {
		UserService userService = new UserService();
		for (User us : userService.getUserList()) {
			assertEquals(userService.logIn(us.getUsername(), us.getPassword()), true);
		}
	}

	@Test
	void UserLogInFalseTest() {
		UserService userService = new UserService();
		List<User> userList = userService.getUserList();
		if (userList.isEmpty()) {
			assertEquals(userService.logIn("test", "test"), false);
		} else {
			for (User us : userService.getUserList()) {
				assertEquals(userService.logIn(us.getUsername(), us.getPassword()+"test"), false);
				assertEquals(userService.logIn(us.getUsername()+"test", us.getPassword()), false);
			}
		}
	}

	@Test
	void AddUserFalse() {
		UserService userService = new UserService();
		for (User us : userService.getUserList()) {
			assertEquals(userService.addUser(us.getUsername(), us.getPassword(), us.getEmail(), us.getName(), us.getSurname1(), us.getSurname2(), us.getPhone()), false);
		}
	}

	// Para que la prueba sea mas concisa deberia de poder borrarse el usuario del test para que no se quede en la base de datos
	// Si se queda en la base de datos para la siquiente iteracion fallará
	@Test
	void AddUserTrue() {
		UserService userService = new UserService();
		assertEquals(userService.addUser("test1", "test1234", "test@gmail.com", "test", "test", "test", 123456), true);
		Set<String> users = new HashSet<>();
		for (User us : userService.getUserList()) {
			users.add(us.getUsername());
		}
		assertEquals(users.contains("test1"), true);
	}
}
