package com.uniovi.entities;

public class Mark {
	//Lista de atributos:
	private long id;
	private String description;
	private Double score;

	
	public Mark(long id, String description, Double score) {
		super();
		this.id = id;
		this.description = description;
		this.score = score;
	}
	public Mark() {
		
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
	
	@Override
	public String toString() {
		return "Mark [id=" + id + ", description=" + description + ", score=" + score + "]";
	}


}