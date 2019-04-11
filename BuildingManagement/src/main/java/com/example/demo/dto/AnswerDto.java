package com.example.demo.dto;


public class AnswerDto {

	private Integer answer_id;
	private String content;
	private Integer correct;
	private Integer checkResult;
	private Boolean checked = false;
	
	public AnswerDto() {
		super();
	}

	public AnswerDto(Integer answer_id, String content) {
		super();
		this.answer_id = answer_id;
		this.content = content;
	}

	public AnswerDto(Integer answer_id, String content, Integer correct) {
		super();
		this.answer_id = answer_id;
		this.content = content;
		this.correct = correct;
	}

	public Integer getAnswer_id() {
		return answer_id;
	}

	public void setAnswer_id(Integer answer_id) {
		this.answer_id = answer_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCorrect() {
		return correct;
	}

	public void setCorrect(Integer correct) {
		this.correct = correct;
	}

	public Integer getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(Integer checkResult) {
		this.checkResult = checkResult;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

   

}
