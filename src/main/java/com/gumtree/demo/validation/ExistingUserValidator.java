package com.gumtree.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.gumtree.demo.business.UserService;

import lombok.RequiredArgsConstructor;

/**
 * Validate annotation to check for existing user
 */
@RequiredArgsConstructor
public class ExistingUserValidator implements ConstraintValidator<ExistingUser, String> {

	private final UserService userService;

	@Override
	public void initialize(ExistingUser user) {
		// Intentionally empty: nothing to initialize
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext ctx) {

		if (value == null) {
			return true;
		}
		return userService.findByUsername(value).isPresent();
	}

}
