package com.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long>{
	@Query("select e from User e where e.status!=-1")
	 public List <User> findActifUsers();
	
	public User findByUsername(String username);
	
}
