package com.manash.question_service.repo;
import com.manash.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Question,Integer>
{

	List<Question> findByCategory(String category);

	@Query(value = "SELECT id FROM Question  WHERE category= ?1 ORDER BY RAND() LIMIT ?2",nativeQuery = true)
	List<Integer> findRandomQuestionsByCategory(String category, int numQ);
}


