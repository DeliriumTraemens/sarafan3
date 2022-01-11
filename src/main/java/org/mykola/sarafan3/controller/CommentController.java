package org.mykola.sarafan3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.mykola.sarafan3.domain.Comment;
import org.mykola.sarafan3.domain.User;
import org.mykola.sarafan3.domain.Views;
import org.mykola.sarafan3.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")
public class CommentController {
	private final CommentService commentService;
	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping
	@JsonView(Views.FullComment.class)
	public Comment create(
			@RequestBody Comment comment,
			@AuthenticationPrincipal User user){
		
		return commentService.create(comment, user);
	}
	
	
	
}
