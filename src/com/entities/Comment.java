package com.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="comment")
public class Comment {

	
	public Comment(Date timeInserted, Date lastModified, String status, String content, User user) {
		super();
		this.timeInserted = timeInserted;
		this.lastModified = lastModified;
		this.status = status;
		this.content = content;
		this.user = user;
	}
	public Comment()
	{
		
	}
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_comment")
    private Long idComment;
	
	@Column(name="time_inserted")
	private Date timeInserted ;
	
	@Column(name="last_modified")
	private Date lastModified ;
	
	private String status;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name="id_user", nullable=false)
	private User user;
	
	
	public Long getIdComment() {
		return idComment;
	}
	public void setIdComment(Long idComment) {
		this.idComment = idComment;
	}
	public String getTimeInserted() {
		return timeInserted.toString();
	}
	public void setTimeInserted(Date timeInserted) {
		this.timeInserted = timeInserted;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}