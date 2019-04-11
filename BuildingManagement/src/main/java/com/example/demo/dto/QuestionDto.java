package com.example.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.entity.Question;

public class QuestionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer question_id;

	private String content;

	private Integer status;
	
	private Integer checkAnswer ;
	

	private List<AnswerDto> answers = new ArrayList<AnswerDto>();
	
	public QuestionDto() {
		super();
	}

	public QuestionDto(Integer question_id, String content, Integer status, List<AnswerDto> answers) {
		super();
		this.question_id = question_id;
		this.content = content;
		this.status = status;
		this.answers = answers;
	}

	public void dtoToEntity(Question question) {
		question.setId(this.question_id);
		question.setContent(this.content);
		question.setStatus(this.status);
	}

	public Integer getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<AnswerDto> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerDto> answers) {
		this.answers = answers;
	}

	public Integer getCheckAnswer() {
		return checkAnswer;
	}

	public void setCheckAnswer(Integer checkAnswer) {
		this.checkAnswer = checkAnswer;
	}

	

	
}
