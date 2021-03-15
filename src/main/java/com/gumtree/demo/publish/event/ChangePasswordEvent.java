package com.gumtree.demo.publish.event;

import org.springframework.context.ApplicationEvent;

public class ChangePasswordEvent extends ApplicationEvent {

	public ChangePasswordEvent(Object source) {
		super(source);
	}

	private static final long serialVersionUID = 5464285683073291989L;

}
