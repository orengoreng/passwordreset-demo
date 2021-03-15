package com.gumtree.demo.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gumtree.demo.publish.event.UserEntityListener;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(value = UserEntityListener.class)
@Table(name = "User")
@Data
@NoArgsConstructor
public class User {

	@Id
	@Column
	private String username;
	@Column
	private String role;
	@Column
	private String password;
	@Column
	private Boolean enabled;

}
