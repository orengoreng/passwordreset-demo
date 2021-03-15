package com.gumtree.demo.publish.event;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.gumtree.demo.business.ObjectToJsonFileWriterUtil;
import com.gumtree.demo.data.Audit;
import com.gumtree.demo.data.User;

import lombok.extern.slf4j.Slf4j;

/**
 * Listener class to write password change event into a file
 */
@Slf4j
@Component
public class FileTrackerPasswordEventListener
		implements ApplicationListener<ChangePasswordEvent> {

	private final static String PASSWORD_EVENT = "PasswordChange";

	@Value("${app.event.folder}")
	private String folder;

	@SuppressWarnings("unchecked")
	@Override
	public void onApplicationEvent(ChangePasswordEvent event) {
		// Call Object to Json Util class
		Audit<User> audit = (Audit<User>) event.getSource();
		log.info("received password change event - User:{}",
				audit.getRecent().getUsername());
		String filename = String.format("%s_%s", PASSWORD_EVENT,
				audit.getTime().format(DateTimeFormatter.ISO_INSTANT));
		ObjectToJsonFileWriterUtil writer = new ObjectToJsonFileWriterUtil(folder,
				filename);
		writer.writeJsonFile(event.getSource());
		log.info("write password change event - file:{}", filename);
	}

}
