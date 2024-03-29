package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Mark {	
	@Id
	@GeneratedValue
	private long id;
	private String description;
	private Double score;
	private Boolean resend = false;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Mark(long id, String description, Double score) {
		super();
		this.id = id;
		this.description = description;
		this.score = score;
	}
	public Mark(String description, Double score,User user) {
		super();
		this.description = description;
		this.score = score;
		this.user = user;
	}
	
	public Mark() {
		
	}
	
	public Boolean getResend() {
		return resend;
	}
	public void setResend(Boolean resend) {
		this.resend = resend;
	}
	
	//Métodos get y setters, autogenerado.
	public Long getId() {
		return Long.valueOf(id);//Hacemos este cambio debido a un problema en MarksService
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Mark [id=" + id + ", description=" + description + ", score=" + score + "]";
	}


}