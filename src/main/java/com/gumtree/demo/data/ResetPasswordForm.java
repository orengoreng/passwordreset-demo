package com.gumtree.demo.data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.gumtree.demo.validation.ExistingUser;
import com.gumtree.demo.validation.InputDataMatch;

import lombok.Data;

@Data
@InputDataMatch(firstField = "password", secondField = "confirmPassword", message = "The password fields must match")
public class ResetPasswordForm {

	@Email
	@ExistingUser
	@NotBlank(message = "Enter the email")
	private String email;
	@NotBlank(message = "Enter the password")
	private String password;
	@NotBlank(message = "Enter the confirm password")
	private String confirmPassword;

}
