package br.com.alura.forum.controllers.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.alura.forum.models.Topic;

public class TopicDto {

	private Long id;
	private String title;
	private String message;
	private LocalDateTime creationDate;

	public TopicDto(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.creationDate = topic.getCreationDate();
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

	public static Page<TopicDto> converter(Page<Topic> topics) {
		return topics.map(TopicDto::new);
	}

}
