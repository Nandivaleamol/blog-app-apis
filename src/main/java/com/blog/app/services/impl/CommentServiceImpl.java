package com.blog.app.services.impl;

import com.blog.app.entities.Comment;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.payloads.CommentDto;
import com.blog.app.repositories.CommentRepo;
import com.blog.app.repositories.PostRepo;
import com.blog.app.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        var post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        var comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        return this.modelMapper.map(this.commentRepo.save(comment), CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        this.commentRepo.delete(this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId)));
    }

    @Override
    public CommentDto getSingleComment(Integer commentId) {
        return this.modelMapper.map(this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId)), CommentDto.class) ;
    }

    @Override
    public List<CommentDto> getAllComments() {
        return this.commentRepo.findAll().stream().map((comment) -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
        var comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
        comment.setContent(commentDto.getContent());
        return this.modelMapper.map(this.commentRepo.save(comment), CommentDto.class) ;
    }
}
