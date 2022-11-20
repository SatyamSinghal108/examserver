package com.exam.service;

import java.util.Set;

import com.exam.entity.exams.Questions;
import com.exam.entity.exams.Quiz;

public interface QuestionService {
	
	public Questions addQuestion(Questions questions);
	public Questions updateQuestion(Questions questions);
	public void deleteQuestion(Long qid);
	public Set<Questions> getQuestions();
	public Questions getQuestion(Long qid);
	public Set<Questions> getQuestionsOfQuiz(Quiz quiz);
	public Questions get(Long quesId);

}
