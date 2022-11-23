package com.blog.app.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer postId;
	 @Column(length = 250)
	 private String title;
	 @Column(length = 10000)
	 private String content;
	 private String imageName;
	 private Date addedDate;
	 
	 @ManyToOne
	 @JoinColumn(name = "category_id")
	 private Category category;
	 @ManyToOne
	 private User user;

	 @OneToMany(mappedBy = "post",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private Set<Comment> comments = new HashSet<>();
}
