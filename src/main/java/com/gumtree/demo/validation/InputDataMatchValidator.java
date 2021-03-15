package com.gumtree.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Annotation validation to match 2 fields value
 */
public class InputDataMatchValidator
		implements ConstraintValidator<InputDataMatch, Object> {

	private String firstField;
	private String secondField;
	private String message;

	@Override
	public void initialize(final InputDataMatch constraintAnnotation) {
		firstField = constraintAnnotation.firstField();
		secondField = constraintAnnotation.secondField();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean valid = true;
		try {
			// Get value of an object member by reflection
			final Object firstObj = BeanUtils.getProperty(value, firstField);
			final Object secondObj = BeanUtils.getProperty(value, secondField);
			// Compare values
			valid = firstObj == null && secondObj == null
					|| firstObj != null && firstObj.equals(secondObj);
			if (!valid) {
				context.buildConstraintViolationWithTemplate(message)
						.addPropertyNode(secondField).addConstraintViolation()
						.disableDefaultConstraintViolation();
			}
		} catch (Exception ignore) {

		}
		return valid;
	}

}
