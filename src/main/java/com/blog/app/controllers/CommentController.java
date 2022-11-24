package com.blog.app.controllers;

import com.blog.app.payloads.ApiResponse;
import com.blog.app.payloads.CommentDto;
import com.blog.app.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //create comment
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.commentService.createComment(commentDto,postId));
    }

    //delete comment
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Comment is successfully deleted!!",true));
    }

    //get single comment by comment id
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> getSingleComment(@PathVariable Integer commentId){
        return ResponseEntity.status(HttpStatus.OK).body(this.commentService.getSingleComment(commentId));
    }

    //get all comments
    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> getAllComments(){
        return ResponseEntity.status(HttpStatus.OK).body(this.commentService.getAllComments());
    }

    //update comment by comment id
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable Integer commentId){
        return ResponseEntity.status(HttpStatus.OK).body(this.commentService.updateComment(commentDto,commentId));
    }

}