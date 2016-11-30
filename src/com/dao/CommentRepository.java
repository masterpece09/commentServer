package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.Comment;

import java.util.List;

public interface  CommentRepository extends JpaRepository<Comment, Long>{
/**
 * list des commentaires actifss du systeme
 * @param status
 * @return
 */
	List<Comment> findByStatus(String status);

}
