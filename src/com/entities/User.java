package com.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.entities.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="user")
public class User {

	public User(String username, String password, String fullname, int status, String role,
			List<Comment> commentUsers) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.status = status;
		this.role = role;
		this.commentUsers = commentUsers;
	}
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_user")
    private Long idUser;
	
    private String username;
    
    private String password;
    
    private String fullname;
    
    private int status;
    
    private String role;
  
    @OneToMany(
    		fetch=FetchType.EAGER,
			cascade=CascadeType.REMOVE,
			mappedBy="user",
			targetEntity=Comment.class
			)
    @JsonIgnore
	private  List<Comment> commentUsers=new ArrayList<Comment>();
	public User() {
		
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Comment> getCommentUsers() {
		return commentUsers;
	}
	public void setCommentUsers(List<Comment> commentUsers) {
		this.commentUsers = commentUsers;
	}

}