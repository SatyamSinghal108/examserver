package com.exam.examAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exam.entity.exams.Quiz;
import com.exam.entity.exams.Category;
import com.exam.service.QuizService;


@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizAPI {
	
	@Autowired
	private QuizService quizService;
	private Category category;
	
	//add Quiz
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
		Quiz addQuiz = this.quizService.addQuiz(quiz);
		return ResponseEntity.ok(addQuiz);
	}
	
	//get Quiz
	@GetMapping("/{quizId}")
	public Quiz getQuiz(@PathVariable("quizId") Long QuizId) {
		
		return this.quizService.getQuiz(QuizId);
	}
	
	//get all Quiz
	@GetMapping("/")
	public ResponseEntity<?> getQuizess(){
		return ResponseEntity.ok(this.quizService.getQuiz());
	}
	
	@PutMapping("/")
	public Quiz updateQuiz(@RequestBody Quiz Quiz) {
		return this.quizService.updateQuiz(Quiz);
	}
	
	@DeleteMapping("/{QuizId}")
	public void deleteQuiz(@PathVariable("QuizId") Long QuizId) {
		 this.quizService.deleteQuiz(QuizId);
	}
	
	//get quiz by category
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizByCategory(@PathVariable("cid") Long cid){
		
		Category category  = new Category();
		category.setCid(cid);
		return this.quizService.getQuizzesByCategory(category);	
	}

	
	@GetMapping("/active")
	public List<Quiz> getActiveQuizzes(){
		return this.quizService.getActiveQuizzes();
	}
	
	@GetMapping("/category/active/{cid}")
	public List<Quiz> getActiveQuizByCategory(@PathVariable("cid") Long cid){
		Category category  = new Category();
		category.setCid(cid);
		return this.quizService.getActiveQuizzesByCategory(category);
	}
}
