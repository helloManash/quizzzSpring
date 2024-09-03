package com.manash.question_service.service;

import com.manash.question_service.model.Question;
import com.manash.question_service.model.QuestionWrapper;
import com.manash.question_service.model.Response;
import com.manash.question_service.repo.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService
{
	@Autowired
	QuizRepository repo;

	public ResponseEntity<List<Question>> getAllQuestions()
	{
		try
		{
			return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
		}
		catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<List<Question>> getAllQuestionsByCatergory(String category)
	{
		try
		{
			return new ResponseEntity<>(repo.findByCategory(category), HttpStatus.OK);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion( Question question)
	{
		repo.save(question);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	public ResponseEntity<List<Integer>>getQuestionsForQuiz(String categoryName, Integer numberOfQuestions){
		List<Integer> questions = repo.findRandomQuestionsByCategory(categoryName, numberOfQuestions);
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds){
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		for(Integer questionId : questionIds){
			Question question = repo.findById(questionId).get();
			questions.add(question);
		}
		for(Question question : questions){
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			wrappers.add(wrapper);
		}
		return new ResponseEntity<>(wrappers, HttpStatus.OK);

	}

	public ResponseEntity<Integer> getScore(List<Response> responses)
	{

		int right = 0;
		for(Response response: responses){
			Question question = repo.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer()))
			{
				right = right + 1;
			}
		}
		return new ResponseEntity<>(right, HttpStatus.OK);

	}
}
