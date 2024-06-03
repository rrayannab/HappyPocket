package com.happyPockets;

import com.happyPockets.api.model.User;
import com.happyPockets.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase dedicada a las pruebas unitarias de cada uno de los metodos
 * de la calse UserService
 * 		6 pruebas unitarias
 */
@SpringBootTest
public class UserServiceTest {
	UserService userService = null;

	@BeforeEach
	void getUserService() {
		userService = new UserService();
	}

	/**
	 * Preuba para los metodos:
	 * 		getUserID
	 */
	@Test
	void UserInDataBaseTest() {
		for (User us : userService.getUserList()) {
			assertTrue(userService.getUserID(us.getId()).isPresent());
			User aux = userService.getUserID(us.getId()).get();
			assertEquals(us.getName(),aux.getName());
		}
	}

	/**
	 * Preuba para los metodos:
	 * 		getUserID
	 */
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

	/**
	 * Preuba para los metodos:
	 * 		logIn
	 */
	@Test
	void UserLogInTrueTest() {
		for (User us : userService.getUserList()) {
            assertEquals(userService.logIn(us.getUsername(), us.getPassword()), us);
		}
	}

	/**
	 * Preuba para los metodos:
	 * 		logIn
	 */
	@Test
	void UserLogInFalseTest() {
		List<User> userList = userService.getUserList();
		if (userList.isEmpty()) {
			assertNull(userService.logIn("test", "test"));
		} else {
			for (User us : userService.getUserList()) {
				assertNull(userService.logIn(us.getUsername(), us.getPassword()+"test"));
				assertNull(userService.logIn(us.getUsername()+"test", us.getPassword()));
			}
		}
	}

	/**
	 * Preuba para los metodos:
	 * 		addUser
	 */
	@Test
	void AddUserFalse() {
		for (User us : userService.getUserList()) {
			assertFalse(userService.addUser(us.getUsername(), us.getPassword(), us.getEmail(), us.getName(), us.getSurname1(), us.getSurname2(), us.getPhone()));
		}
	}

	/**
	 * Preuba para los metodos:
	 * 		addUser
	 */
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
