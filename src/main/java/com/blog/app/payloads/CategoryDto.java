package com.blog.app.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private Integer categoryId;
	@NotBlank
	@Size(min = 4,message = "Min size of category title is atleast 4 characters!!")
	private String categoryTitle;
	@NotBlank
	@Size(min = 10, message = "Min size of category description is atleast 10 characters!!")
	private String categoryDescription;
	
}
