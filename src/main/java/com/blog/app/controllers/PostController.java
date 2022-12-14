package com.blog.app.controllers;

import java.io.IOException;
import java.util.List;

import com.blog.app.config.AppConstants;
import com.blog.app.payloads.PostResponse;
import com.blog.app.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import com.blog.app.payloads.ApiResponse;
import com.blog.app.payloads.PostDto;
import com.blog.app.services.PostService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	//create post api
	@PostMapping(value = "/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPostByUserIdAndCategoryId(
			@Valid
			@RequestBody PostDto postDto, 
			@PathVariable Integer userId, 
			@PathVariable Integer categoryId)
	{
		return new ResponseEntity<PostDto>(this.postService.createPost(postDto, userId, categoryId),HttpStatus.CREATED);
	}

	//update post api
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePostByPostId(
			@Valid
			@RequestBody PostDto postDto,
			@PathVariable Integer postId){
		return ResponseEntity.ok(this.postService.updatePost(postDto, postId));
	}
	
	//get posts by userId
	@GetMapping(value = "/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		return new ResponseEntity<List<PostDto>>(this.postService.getAllPostsByUser(userId), HttpStatus.OK);
	}
	
	//get posts by categoryId
	@GetMapping(value = "/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		return new ResponseEntity<List<PostDto>>(this.postService.getAllPostsByCategory(categoryId), HttpStatus.OK);
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
		return new ResponseEntity<PostResponse>(this.postService.getAllPost(pageNumber, pageSize,sortBy,sortDir),HttpStatus.OK);
	 }
	
	//get single post by postId
	@GetMapping(value = "/posts/{postId}")
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postId){
		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	//delete post by postId
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostByPostId(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post is successfully deleted!!",true),HttpStatus.OK);
	}
	//search posts by keyword title
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword){
		return new ResponseEntity<List<PostDto>>(this.postService.searchPosts(keyword),HttpStatus.OK);
	}
	//uploading image by postId and image parameter
	@PostMapping("/posts/image/{postId}")
	public ResponseEntity<PostDto> uploadImageByPostId(
			@PathVariable Integer postId,
			@RequestParam("image")MultipartFile image
			) throws IOException {
		var postDto = this.postService.getPostById(postId);
		var fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		var updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<>(updatePost,HttpStatus.OK);
	}

	// serve files
	@GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImageByImageName(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response
	) throws IOException {
		var resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
	}
}
