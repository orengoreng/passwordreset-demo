package com.gumtree.demo.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.gumtree.demo.business.UserService;
import com.gumtree.demo.data.ResetPasswordForm;
import com.gumtree.demo.data.User;
import com.gumtree.demo.web.UserController;

@WebMvcTest(controllers = UserController.class)
public class ITUserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService service;

	@Test
	void whenGetResetPassword_thenReturn200() throws Exception {
		mockMvc.perform(get("/resetPassword")).andExpect(status().isOk())
				.andExpect(view().name("/resetPassword"));
	}

	@Test
	void whenPostResetPasswordWithValidForm_thenReturn200() throws Exception {
		// Mock input parameter
		when(service.findByUsername(Mockito.anyString()))
				.thenReturn(Optional.of(new User()));
		User user = new User();
		user.setUsername("oreng1@gmail.com");
		user.setPassword("3");
		when(service.save(Mockito.any())).thenReturn(user);

		mockMvc.perform(
				post("/resetPassword").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("email", "oreng@gmail.com").param("password", "3")
						.param("confirmPassword", "3")
						.sessionAttr("resetPasswordForm", new ResetPasswordForm()))
				.andExpect(status().isOk()).andExpect(view().name("/login"))
				.andExpect(model().attribute("user", hasProperty("password", is("3"))));
	}

	@Test
	void whenPostRestPasswordWithInvalidForm_thenReturn400() throws Exception {
		mockMvc.perform(
				post("/resetPassword").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("email", "oreng").param("password", "3")
						.param("confirmPassword", "2")
						.sessionAttr("resetPasswordForm", new ResetPasswordForm()))
				.andExpect(status().isBadRequest())
				.andExpect(view().name("/resetPassword"))
				.andExpect(model().attributeHasFieldErrorCode("resetPasswordForm",
						"confirmPassword", is("InputDataMatch")));
	}

	@Test
	void whenPostRestPasswordWithNonExistingUser_thenReturn4() throws Exception {
		mockMvc.perform(
				post("/resetPassword").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("email", "oreng123@gmail.com").param("password", "2")
						.param("confirmPassword", "2")
						.sessionAttr("resetPasswordForm", new ResetPasswordForm()))
				.andExpect(status().isBadRequest())
				.andExpect(view().name("/resetPassword"))
				.andExpect(model().attributeHasFieldErrorCode("resetPasswordForm",
						"email", is("ExistingUser")));
	}

}
