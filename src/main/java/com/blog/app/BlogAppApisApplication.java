package com.blog.app;

import com.blog.app.config.AppConstants;
import com.blog.app.entities.Role;
import com.blog.app.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println(this.passwordEncoder.encode("amol123"));

		try{
			Role role1 = new Role();
			role1.setId(AppConstants.ADMIN_USER);
			role1.setName("ROLE_ADMIN");

				Role role2 = new Role();
				role2.setId(AppConstants.NORMAL_USER);
				role2.setName("ROLE_NORMAL");

			var roles = List.of(role1, role2);
			var result = this.roleRepo.saveAll(roles);
			result.forEach(role -> System.out.println(role.getName()));
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
