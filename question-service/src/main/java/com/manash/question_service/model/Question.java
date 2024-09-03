package com.manash.question_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Question
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column
	@JsonProperty("question_title")
	private String questionTitle;

	@Column
	@JsonProperty("right_answer")
	private String rightAnswer;


	@Column
	@JsonProperty("difficulty_level")
	private String difficultyLevel;

	@Column
	private String category;

	@Column
	private String option1;

	@Column
	private String option2;

	@Column
	private String option3;

	@Column
	private String option4;

}
