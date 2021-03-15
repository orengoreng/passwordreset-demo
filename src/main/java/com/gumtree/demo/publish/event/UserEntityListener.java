package com.gumtree.demo.publish.event;

import java.time.LocalDateTime;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.gumtree.demo.data.Audit;
import com.gumtree.demo.data.User;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Entity listener for User table to send event on password change.
 *
 */
@Component
@NoArgsConstructor
@Slf4j
public class UserEntityListener {

	private Audit<User> audit = new Audit<User>();

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@PostPersist
	@PostUpdate
	public void postUpdate(User user) {
		// Send event notification
		notifyEvent(user);
	}

	private void notifyEvent(User user) {
		audit.setRecent(user);
		audit.setTime(LocalDateTime.now());
		eventPublisher.publishEvent(new ChangePasswordEvent(audit));
		log.info("Nofify password event by User:-{}", user.getUsername());
	}

}
