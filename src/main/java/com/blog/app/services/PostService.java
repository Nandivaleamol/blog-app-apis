package com.blog.app.services;

import java.util.List;

import com.blog.app.entities.Post;
import com.blog.app.payloads.PostDto;
import com.blog.app.payloads.PostResponse;

public interface PostService {

	//create post
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update post
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete post
	void deletePost(Integer postId);
	
	//get single post
	PostDto getPostById(Integer postId);
	
	//get all posts
	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

	//get all posts by category
	List<PostDto> getAllPostsByCategory(Integer categoryId);
	
	//get all posts by user
	List<PostDto> getAllPostsByUser(Integer userId);
	
	//search posts by keyword
	List<PostDto> searchPosts(String keyword);
	
}
