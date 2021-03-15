package com.gumtree.demo.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gumtree.demo.data.ResetPasswordForm;

@ExtendWith(MockitoExtension.class)
public class InputDataMatchValidatorTest {

	@Mock
	private InputDataMatch inputData;

	@Mock
	private ConstraintValidatorContext constraintValidatorContext;

	private InputDataMatchValidator validator;

	private void setUpTestData() {
		when(inputData.firstField()).thenReturn("password");
		when(inputData.secondField()).thenReturn("confirmPassword");
		validator = new InputDataMatchValidator();
		validator.initialize(inputData);
	}

	@Test
	public void testFieldMatch_isValid() {
		setUpTestData();
		ResetPasswordForm form = new ResetPasswordForm();
		form.setConfirmPassword("123");
		form.setPassword("123");

		boolean result = validator.isValid(form, constraintValidatorContext);
		assertTrue(result);
	}

	@Test
	public void testFieldInputNotMatch_NotValid() {
		setUpTestData();
		ResetPasswordForm form = new ResetPasswordForm();
		form.setConfirmPassword("1234");
		form.setPassword("123");

		boolean result = validator.isValid(form, constraintValidatorContext);
		assertFalse(result);
	}

	@Test
	public void testFieldInputMatchException_ShouldReturnValid() {
		setUpTestData();
		boolean result = validator.isValid(new ResetPasswordForm(),
				constraintValidatorContext);
		assertTrue(result);
	}

}
