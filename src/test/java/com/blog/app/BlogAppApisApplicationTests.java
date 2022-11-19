package com.blog.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.app.repositories.UserRepo;
import com.blog.app.services.UserService;

@SpringBootTest
class BlogAppApisApplicationTests {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void repoTest() {
		System.out.println("userRepo object details: ");
		String className = this.userRepo.getClass().getName();
		Package packName = this.userRepo.getClass().getPackage();
		System.out.println(className);
		System.out.println(packName);
	}
	
	@Test
	public void serviceTest() {
		System.out.println("userService object details : ");
		String className = this.userService.getClass().getName();
		Package packName = this.userService.getClass().getPackage();
		System.out.println(className);
		System.out.println(packName);
	}

}
