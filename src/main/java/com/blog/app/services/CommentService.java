package com.blog.app.services;

import com.blog.app.payloads.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);
    void deleteComment(Integer commentId);

    CommentDto getSingleComment(Integer commentId);

    List<CommentDto> getAllComments();

    CommentDto updateComment(CommentDto commentDto, Integer commentId);
}
