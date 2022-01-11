package org.mykola.sarafan3.service;

import org.mykola.sarafan3.domain.Comment;
import org.mykola.sarafan3.domain.Message;
import org.mykola.sarafan3.domain.User;
import org.mykola.sarafan3.domain.Views;
import org.mykola.sarafan3.dto.EventType;
import org.mykola.sarafan3.dto.ObjectType;
import org.mykola.sarafan3.repository.CommentRepository;
import org.mykola.sarafan3.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService {
	private final CommentRepository commentRepo;
	private final BiConsumer<EventType, Comment> wsSender;
	
	
	@Autowired
	public CommentService(CommentRepository commentRepo, WsSender wsSender) {
		this.commentRepo = commentRepo;
		this.wsSender= wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
	}
	
	public Comment create(Comment comment, User user){
		comment.setAuthor(user);
		Comment commentFromDb = commentRepo.save(comment);
		wsSender.accept(EventType.CREATE, commentFromDb);
		return commentFromDb;
	}
	
	
}//EndOfClass
