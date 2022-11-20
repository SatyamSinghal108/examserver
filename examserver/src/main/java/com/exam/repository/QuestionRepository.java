package com.exam.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.exams.Questions;
import com.exam.entity.exams.Quiz;

public interface QuestionRepository extends JpaRepository<Questions, Long> {

	public Set<Questions> findByQuiz(Quiz quiz);

}
