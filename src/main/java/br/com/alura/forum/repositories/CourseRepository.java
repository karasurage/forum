package br.com.alura.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	Course findByName(String name);

}
