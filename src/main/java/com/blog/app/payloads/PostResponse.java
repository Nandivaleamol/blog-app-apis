package com.blog.app.payloads;

import com.blog.app.entities.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class PostResponse {

    private List<PostDto> content;
    private  int pageNumber;
    private  int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLastPage;
}
