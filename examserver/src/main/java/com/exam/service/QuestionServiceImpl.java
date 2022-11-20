package com.exam.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.exams.Questions;
import com.exam.entity.exams.Quiz;
import com.exam.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	public QuestionRepository questionRepository;

	@Override
	public Questions addQuestion(Questions questions) {
		// TODO Auto-generated method stub
		return this.questionRepository.save(questions);
	}

	@Override
	public Questions updateQuestion(Questions questions) {
		// TODO Auto-generated method stub
		return this.questionRepository.save(questions);
	}

	@Override
	public void deleteQuestion(Long qid) {
		// TODO Auto-generated method stub
		Questions questions=new Questions();
		questions.setQuesid(qid);
		this.questionRepository.deleteById(qid);
		
	}

	@Override
	public Set<Questions> getQuestions() {
		// TODO Auto-generated method stub
		return new HashSet<>( this.questionRepository.findAll());
	}

	@Override
	public Questions getQuestion(Long qid) {
		// TODO Auto-generated method stub
		return this.questionRepository.findById(qid).get();
	}

	@Override
	public Set<Questions> getQuestionsOfQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.questionRepository.findByQuiz(quiz);
	}

	@Override
	public Questions get(Long quesId) {
		// TODO Auto-generated method stub
		return this.questionRepository.getOne(quesId);
	}

}
