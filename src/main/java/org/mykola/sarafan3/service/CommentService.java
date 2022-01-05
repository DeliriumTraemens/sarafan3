package org.mykola.sarafan3.service;

import org.mykola.sarafan3.domain.Comment;
import org.mykola.sarafan3.domain.User;
import org.mykola.sarafan3.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	private final CommentRepository commentRepo;
	
	@Autowired
	public CommentService(CommentRepository commentRepo) {
		this.commentRepo = commentRepo;
	}
	
	public Comment create(Comment comment, User user){
		comment.setAuthor(user);
		commentRepo.save(comment);
		return comment;
	}
	
	
}//EndOfClass
