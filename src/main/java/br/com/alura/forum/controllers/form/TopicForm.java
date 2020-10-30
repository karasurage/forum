package br.com.alura.forum.controllers.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.models.Course;
import br.com.alura.forum.models.Topic;
import br.com.alura.forum.repositories.CourseRepository;

public class TopicForm {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String title;
	
	@NotNull @NotEmpty @Length(min = 10)
	private String message;
	
	@NotNull
	@NotEmpty
	private String nameCourse;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNameCourse() {
		return nameCourse;
	}

	public void setNameCourse(String nameCourse) {
		this.nameCourse = nameCourse;
	}

	public Topic converter(CourseRepository courseRepository) {
		Course course = courseRepository.findByName(nameCourse);
		return new Topic(title, message, course);
	}

}
