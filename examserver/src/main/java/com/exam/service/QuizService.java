package com.exam.service;

import java.util.List;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import com.exam.entity.exams.Category;
import com.exam.entity.exams.Quiz;

public interface QuizService {
	
	public Quiz addQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz);
	public Quiz getQuiz(Long quizId);
	public Set<Quiz> getQuiz();
	public void deleteQuiz(Long quizId);
	public List<Quiz> getQuizzesByCategory(Category category);
	public List<Quiz> getActiveQuizzes();
	public List<Quiz> getActiveQuizzesByCategory(Category c);
	
}
