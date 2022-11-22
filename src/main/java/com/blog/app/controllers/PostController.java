package com.blog.app.controllers;

import java.util.List;

import com.blog.app.config.AppConstants;
import com.blog.app.payloads.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blog.app.payloads.ApiResponse;
import com.blog.app.payloads.PostDto;
import com.blog.app.services.PostService;

@RestController
@RequestMapping(value = "/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	//create post api
	@PostMapping(value = "/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto, 
			@PathVariable Integer userId, 
			@PathVariable Integer categoryId)
	{
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}

	//update post api
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		var updatePostDto = this.postService.updatePost(postDto, postId);
		return ResponseEntity.ok(updatePostDto);
	}
	
	//get posts by userId
	@GetMapping(value = "/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> postsDtos = this.postService.getAllPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(postsDtos, HttpStatus.OK);
	}
	
	//get posts by categoryId
	@GetMapping(value = "/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDto> postDos = this.postService.getAllPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postDos, HttpStatus.OK);
	}
	
	//get all posts
	@GetMapping(value = "/posts")
	 public ResponseEntity<PostResponse> getAllPosts(
			 @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			 @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			 @RequestParam(value = "sortBy",defaultValue =AppConstants.SORT_BY,required = false) String sortBy,
			 @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir
			 )
	{
		var postResponse = this.postService.getAllPost(pageNumber, pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	 }
	
	//get single post by postId
	@GetMapping(value = "/posts/{postId}")
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postId){
		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	//delete post by postId
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostByPostId(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post is successfully deleted!!",true),HttpStatus.OK);
	}
	//search posts by keyword title
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword){
		var postDtos = this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
}
