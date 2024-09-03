package com.manash.quiz_service.controller;

import com.manash.quiz_service.model.QuestionWrapper;
import com.manash.quiz_service.model.QuizDto;
import com.manash.quiz_service.model.Response;
import com.manash.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Quiz")
public class QuizController
{

	@Autowired
	QuizService quizService;

	@PostMapping("create")
	public ResponseEntity<List<Integer>> createQuiz(@RequestBody QuizDto quizdto){
		return quizService.createQuiz(quizdto.getCategory(), quizdto.getNumQ(), quizdto.getTitle());
	}

	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
	{
		return quizService.getQuizQuestions(id);
	}
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody
	List<Response> response){
		return quizService.calculateResult(id, response);

	}

}
