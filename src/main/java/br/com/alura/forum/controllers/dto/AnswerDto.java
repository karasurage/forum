package br.com.alura.forum.controllers.dto;

import java.time.LocalDateTime;

import br.com.alura.forum.models.Answer;

public class AnswerDto {

	private Long id;
	private String message;
	private LocalDateTime creationDate;
	private String nameAuthor;

	public AnswerDto(Answer answer) {
		this.id = answer.getId();
		this.message = answer.getMessage();
		this.creationDate = answer.getCreationDate();
		this.nameAuthor = answer.getAuthor().getName();
	}

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public String getNameAuthor() {
		return nameAuthor;
	}

}
