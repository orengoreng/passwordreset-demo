package com.gumtree.demo.web;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gumtree.demo.business.UserService;
import com.gumtree.demo.data.ResetPasswordForm;
import com.gumtree.demo.data.User;

import lombok.extern.slf4j.Slf4j;

/**
 * Rest API for User function
 */
@Slf4j
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/resetPassword")
	public String getResetPassword(ResetPasswordForm resetPasswordForm, Model model) {
		return "/resetPassword";
	}

	@PostMapping("/resetPassword")
	public String updateResetPassword(@Valid ResetPasswordForm resetPasswordForm,
			BindingResult binding, Model model, HttpServletResponse servletResponse)
			throws Exception {
		log.debug("requested /resetPassword");
		// return to the page display error
		if (binding.hasFieldErrors()) {
			log.warn("Data binding error in ResetPassword- User:{}",
					resetPasswordForm.getEmail());
			servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "/resetPassword";
		}

		// update User entity
		userService.findByUsername(resetPasswordForm.getEmail()).filter(u -> u != null)
				.map(u -> {
					u.setPassword(resetPasswordForm.getPassword());
					User saved = userService.save(u);
					model.addAttribute("user", saved);
					return saved;
				}).orElseThrow(Exception::new);
		log.info("Success password changed - User:{}", resetPasswordForm.getEmail());
		return "/login";
	}

}
