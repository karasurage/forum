package br.com.alura.forum.controllers.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.models.StatusTopic;
import br.com.alura.forum.models.Topic;

public class DetailTopicDto {

	private Long id;
	private String title;
	private String message;
	private LocalDateTime creationDate;
	private String nameAuthor;
	private StatusTopic status;
	private List<AnswerDto> answers;

	public DetailTopicDto(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.creationDate = topic.getCreationDate();
		this.nameAuthor = topic.getAuthor().getName();
		this.status = topic.getStatus();
		this.answers = new ArrayList<>();
		this.answers.addAll(topic.getAnswers().stream().map(AnswerDto::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
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

	public StatusTopic getStatus() {
		return status;
	}

	public List<AnswerDto> getAnswers() {
		return answers;
	}

}
