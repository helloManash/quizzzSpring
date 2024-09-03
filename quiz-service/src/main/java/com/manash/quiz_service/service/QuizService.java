package com.manash.quiz_service.service;


import com.manash.quiz_service.feign.QuizInterface;
import com.manash.quiz_service.model.Question;
import com.manash.quiz_service.model.QuestionWrapper;
import com.manash.quiz_service.model.Quiz;
import com.manash.quiz_service.model.Response;
import com.manash.quiz_service.repo.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService
{
	@Autowired
	QuizDao quizDao;

	@Autowired
	QuizInterface quizInterface;



	public ResponseEntity<List<Integer>> createQuiz(String category, int numQ, String title)
	{

		List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);

		quizDao.save(quiz);

		return new ResponseEntity<>(questions, HttpStatus.CREATED);
	}
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id){
		Optional<Quiz> quiz = quizDao.findById(id);
		List<Integer> questionIds = quiz.get().getQuestionIds();
		List<QuestionWrapper> questionsForUser = quizInterface.getQuestionsFromId(questionIds).getBody();
//
		return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses)
	{
//		Quiz quiz = quizDao.findById(id).get();
//		List<Question> questions = quiz.getQuestions();
//		int right = 0, i = 0;
//		for(Response response: responses){
//			if(response.getResponse().equals(questions.get(i).getRightAnswer()))
//			{
//				right = right + 1;
//			}
//			i++;
//		}
		return quizInterface.getScore(responses);
	}
}
