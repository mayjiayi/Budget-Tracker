package com.fdmgroup.javaproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.repository.UserRepository;
import com.fdmgroup.javaproject.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

	@Mock
	private UserRepository userRepo;
	@InjectMocks
	private UserService userService;

	@Test
	public void testRegisterNewUser_SuccessfulRegistration() {
		// Arrange
		User user = new User("testUsername", "testPassword", "testEmail", "testFirstName", "testLastName");
		when(userRepo.findByUsername(user.getUsername())).thenReturn(Optional.empty());

		// Act
		boolean result = userService.registerNewUser(user);

		// Assert
		assertTrue(result);
		verify(userRepo, times(1)).findByUsername(user.getUsername());
		verify(userRepo, times(1)).save(user);
	}

	@Test
	public void testRegisterNewUser_UserAlreadyExists() {
		// Arrange
		User existingUser = new User("testUsername", "testPassword", "testEmail", "testFirstName", "testLastName");
		when(userRepo.findByUsername(existingUser.getUsername())).thenReturn(Optional.of(existingUser));

		// Act
		boolean result = userService.registerNewUser(existingUser);

		// Assert
		assertFalse(result);
		verify(userRepo, times(1)).findByUsername(existingUser.getUsername());
		verify(userRepo, never()).save(any(User.class));
	}

	@Test
	public void testFindUser_UserExists() {
		// Arrange
		int userId = 1;
		User existingUser = new User("testUsername", "testPassword", "testEmail", "testFirstName", "testLastName");
		when(userRepo.findById(userId)).thenReturn(Optional.of(existingUser));

		// Act
		User result = userService.findUser(userId);

		// Assert
		assertEquals(existingUser, result);
		verify(userRepo, times(1)).findById(userId);
	}

	@Test
	public void testFindUser_UserDoesNotExist() {
		// Arrange
		int userId = 1;
		when(userRepo.findById(userId)).thenReturn(Optional.empty());

		// Act
		User result = userService.findUser(userId);

		// Assert
		assertEquals("defaultusername", result.getUsername());
		assertEquals("defaultpassword", result.getPassword());
		assertEquals("defaultEmail", result.getEmail());
		assertEquals("DefaultfirstName", result.getFirstName());
		assertEquals("DefaultlastName", result.getLastName());
		verify(userRepo, times(1)).findById(userId);
	}

	@Test
	public void testFindUserByUsername_UserExists() {
		// Arrange
		String username = "testUsername";
		User existingUser = new User("testUsername", "testPassword", "testEmail", "testFirstName", "testLastName");
		when(userRepo.findByUsername(username)).thenReturn(Optional.of(existingUser));

		// Act
		User result = userService.findUser(username);

		// Assert
		assertEquals(existingUser, result);
		verify(userRepo, times(1)).findByUsername(username);
	}

	@Test
	public void testFindUserByUsername_UserDoesNotExist() {
		// Arrange
		String username = "nonExistingUser";
		when(userRepo.findByUsername(username)).thenReturn(Optional.empty());

		// Act
		User result = userService.findUser(username);

		// Assert
		assertEquals("defaultusername", result.getUsername());
		assertEquals("defaultpassword", result.getPassword());
		assertEquals("defaultEmail", result.getEmail());
		assertEquals("DefaultfirstName", result.getFirstName());
		assertEquals("DefaultlastName", result.getLastName());
		verify(userRepo, times(1)).findByUsername(username);
	}

	@Test
	public void testValidatePassword_CorrectCredentials() {
		// Arrange
		String username = "testUser";
		String password = "password";
		User existingUser = new User(username, password, "testEmail", "testFirstName", "testLastName");
		when(userRepo.findByUsername(username)).thenReturn(Optional.of(existingUser));

		// Act
		Optional<User> result = userService.validatePassword(username, password);

		// Assert
		assertTrue(result.isPresent());
		assertEquals(existingUser, result.get());
		verify(userRepo, times(1)).findByUsername(username);
	}

	@Test
	public void testValidatePassword_IncorrectPassword() {
		// Arrange
		String username = "testUser";
		String password = "incorrectPassword";
		User existingUser = new User(username, "correctPassword", "testEmail", "testFirstName", "testLastName");
		when(userRepo.findByUsername(username)).thenReturn(Optional.of(existingUser));

		// Act
		Optional<User> result = userService.validatePassword(username, password);

		// Assert
		assertFalse(result.isPresent());
		verify(userRepo, times(1)).findByUsername(username);
	}
}
