package com.manash.question_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionWrapper
{
	private Integer id;
	private String questionTitle;
	private String option1;
	private String option2;
	private String option3;
	private String option4;

	public QuestionWrapper(Integer id, String questionTitle, String option4, String option3,
		String option2, String option1)
	{
		this.id = id;
		this.questionTitle = questionTitle;
		this.option4 = option4;
		this.option3 = option3;
		this.option2 = option2;
		this.option1 = option1;
	}
}
