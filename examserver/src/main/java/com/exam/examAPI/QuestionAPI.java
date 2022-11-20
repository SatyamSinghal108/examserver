package com.exam.examAPI;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.exam.entity.exams.Questions;
import com.exam.entity.exams.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/questions")
@CrossOrigin("*")
public class QuestionAPI {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	//add Question
	@PostMapping("/")
	public ResponseEntity<Questions> addQuestion(@RequestBody Questions Question){
		Questions addQuestion = this.questionService.addQuestion(Question);
		return ResponseEntity.ok(addQuestion);
	}
	
	//get Question
	@GetMapping("/{QuestionId}")
	public Questions getQuestion(@PathVariable("QuestionId") Long QuestionId) {
		
		return this.questionService.getQuestion(QuestionId);
	}
	
	//get all Question
	@GetMapping("/")
	public ResponseEntity<?> getQuestions(){
		return ResponseEntity.ok(this.questionService.getQuestions());
	}
	
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid){
//		Quiz quiz = new Quiz();
//		quiz.setQid();
//		Set<Questions> questionsOfQuiz= this.questionService.getQuestionsOfQuiz(quiz);
//		return ResponseEntity.ok(questionsOfQuiz);
		Quiz quiz = this.quizService.getQuiz(qid);
		Set<Questions> questions = quiz.getQuestions();
		List<Questions> list = new ArrayList(questions);
		if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())){
		    list = list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions() + 1));
		    	}
		list.forEach(q->{
			q.setAnswer("");
		});
		
		Collections.shuffle(list);
		return ResponseEntity.ok (list);
	}
	
	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid){
		Quiz quiz = new Quiz();
		quiz.setQid(qid);
		Set<Questions> questionsOfQuiz= this.questionService.getQuestionsOfQuiz(quiz);
		return ResponseEntity.ok(questionsOfQuiz);
		
	}
	
	@PutMapping("/")
	public Questions updateQuestion(@RequestBody Questions Question) {
		return this.questionService.updateQuestion(Question);
	}
	
	@DeleteMapping("/{QuestionId}")
	public void deleteQuestion(@PathVariable("QuestionId") Long QuestionId) {
		 this.questionService.deleteQuestion(QuestionId);
	}
	
	//evaluate quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Questions> question){
		System.out.println(question);
		  double marksGot=0;
		  Integer correctAnswer=0;
		  Integer attemptedQues=0;
		    for(Questions q:question){
//			System.out.println(q.getGivenAnswer());
			Questions questions =this.questionService.get(q.getQuesid());
			if(q.getGivenAnswer().equals(questions.getAnswer())) {
				correctAnswer++;
				double markSingle = Double.parseDouble(question.get(0).getQuiz().getMaxMarks())/question.size();
				marksGot+=markSingle;
			}
			if(q.getGivenAnswer().trim()!=null) {
				attemptedQues++;
			}
		    }
		
		Map<String, Object> map =Map.of("marksGot",marksGot,"correctAnswer",correctAnswer,"attemptedQues",attemptedQues);
		return ResponseEntity.ok(map);
		
	}
}
