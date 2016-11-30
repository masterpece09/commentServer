package com.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.CommentRepository;
import com.dao.UserRepository;
import com.entities.Comment;
import com.entities.User;

/** 
 * controlleur des commentaires
 * @author LENOVO G50
 *
 */
@RestController
@RequestMapping("/comment")
public class CommentController{
	@Autowired CommentRepository commentRepository;
	@Autowired UserRepository userRepository;
	@RequestMapping(value="/save", method=RequestMethod.POST)
	/**
	 * modification d'un commentaire
	 * @param comment
	 * @param httpSession
	 * @return
	 */
	public Comment saveComment(@RequestBody  Comment comment,HttpSession httpSession)
	 {
		SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String userName = securityContext.getAuthentication().getName();
		//test si l'utilisateur a le droit de commenter la publication
		if(comment.getUser().getUsername().equals(userName))
		{
		//test if the comment id is null then it is and merge issue
		if(comment.getIdComment()==null) comment.setTimeInserted(new Date());
		else comment.setLastModified(new Date());
		commentRepository.save(comment);
		}			
		return comment;
	 }
	
	/**
	 * Ajout d'un commentaire
	 * @param comment
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Comment addComment(@RequestParam String comment,HttpSession httpSession)
	{
		User CurrentUser=userRepository.findByUsername(((SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getName());
		Comment PostedComment=new Comment();
		//controlle coté client reussi et comment n'est pas vide
		PostedComment.setContent(comment);
		PostedComment.setStatus("ACTIF");
		PostedComment.setTimeInserted(new java.util.Date());
		PostedComment.setUser(CurrentUser);
		commentRepository.save(PostedComment);
		return PostedComment;
	}
	
	/**
	 * Cette fonction suprimment un commentaire
	 * @param idComment
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public Comment addComment(@RequestParam Long idComment,HttpSession httpSession)
	{
		Comment Comment= commentRepository.findOne(idComment);
		
		Comment.setStatus("DELETED");
		Comment.setLastModified(new java.util.Date());
		commentRepository.save(Comment);
		return Comment;
	}
	
	/**
	 * 
	 * list des commentaires
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<Comment> getActifComments()
	{
		return commentRepository.findByStatus("ACTIF");
	}
}
