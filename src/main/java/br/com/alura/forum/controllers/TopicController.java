package br.com.alura.forum.controllers;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controllers.dto.DetailTopicDto;
import br.com.alura.forum.controllers.dto.TopicDto;
import br.com.alura.forum.controllers.form.TopicForm;
import br.com.alura.forum.controllers.form.UpdateTopicForm;
import br.com.alura.forum.models.Topic;
import br.com.alura.forum.repositories.CourseRepository;
import br.com.alura.forum.repositories.TopicRepository;

@RestController
@RequestMapping("/topicos")
public class TopicController {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private CourseRepository courseRepository;

	@GetMapping
	@Cacheable(value = "listTopics")
	public Page<TopicDto> list(@RequestParam(required = false) String nameCourse,
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10)  Pageable pageable) {

		if (nameCourse == null) {
			Page<Topic> topics = topicRepository.findAll(pageable);
			return TopicDto.converter(topics);
		} else {
			Page<Topic> topics = topicRepository.findByCourseName(nameCourse, pageable);
			return TopicDto.converter(topics);
		}

	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listTopics", allEntries = true)
	public ResponseEntity<TopicDto> register(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = form.converter(courseRepository);
		topicRepository.save(topic);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();

		return ResponseEntity.created(uri).body(new TopicDto(topic));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailTopicDto> detail(@PathVariable Long id) {
		Optional<Topic> topic = topicRepository.findById(id);
		if (topic.isPresent()) {
			return ResponseEntity.ok(new DetailTopicDto(topic.get()));
		}

		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listTopics", allEntries = true)
	public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form) {
		Optional<Topic> optional = topicRepository.findById(id);
		if (optional.isPresent()) {
			Topic topic = form.update(id, topicRepository);
			return ResponseEntity.ok(new TopicDto(topic));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listTopics", allEntries = true)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Topic> optional = topicRepository.findById(id);
		if (optional.isPresent()) {
			topicRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}