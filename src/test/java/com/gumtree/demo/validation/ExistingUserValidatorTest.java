package com.gumtree.demo.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gumtree.demo.business.UserService;
import com.gumtree.demo.data.User;

@ExtendWith(MockitoExtension.class)
public class ExistingUserValidatorTest {

	@Mock
	private ExistingUser existingUser;

	@Mock
	private UserService userService;

	@Mock
	private ConstraintValidatorContext constraintValidatorContext;

	@Test
	public void testUserExist_IsValid() {
		ExistingUserValidator validator = new ExistingUserValidator(userService);
		validator.initialize(existingUser);
		when(existingUser.toString()).thenReturn("oreng@gmail.com");
		when(userService.findByUsername(Mockito.anyString()))
				.thenReturn(Optional.of(new User()));

		boolean result = validator.isValid(existingUser.toString(),
				constraintValidatorContext);
		assertTrue(result);
	}

	@Test
	public void testUserNotFound_NotValid() {
		ExistingUserValidator validator = new ExistingUserValidator(userService);
		validator.initialize(existingUser);
		when(existingUser.toString()).thenReturn("oreng1@gmail.com");
		when(userService.findByUsername(Mockito.anyString()))
				.thenReturn(Optional.empty());

		boolean result = validator.isValid(existingUser.toString(),
				constraintValidatorContext);
		assertFalse(result);
	}

}
