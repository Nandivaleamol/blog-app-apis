package com.blog.app.payloads;

import javax.validation.constraints.*;

import com.blog.app.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
	
	private int id;
	
	@NotBlank(message = "Blank username is not allowed!!")
	@Size(min = 4, message = "Username must be at least 4 characters!!")
	private String name;
	
	@Email(message = "Email address is not valid!!")
	@Pattern(regexp = "[a-zA-Z0-9][a-zA-Z0-9_.]*@gmail[.]com")
	@NotBlank(message = "Email is required!!")
	private String email;
	@NotBlank(message = "About is must not be empty!!")
	private String about;
	@NotBlank(message = "Password is required!!")
	@Size(min = 4,max = 10, message = "Password min 4 character and max 10 chars are allowed!!")
	private String password;

	private Set<Role> roles = new HashSet<>();

	@JsonIgnore
	public String getPassword() {
		return this.password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}

}
