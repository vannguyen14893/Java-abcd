package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.dto.AnswerDto;
import com.example.demo.dto.QuestionDto;

@Entity
@Table(name = "question")
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column(name = "content")
	private String content;
	@Column(name = "active")
	private Integer status;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Answer> answers = new ArrayList<Answer>();

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void entityToDto1(QuestionDto dto) {
		dto.setQuestion_id(this.id);
		dto.setContent(this.content);
		dto.setStatus(this.status);
		for (Answer item : this.answers) {
			AnswerDto answerDto = new AnswerDto(item.getId(), item.getContent());
			dto.getAnswers().add(answerDto);
		}

	}

	public void entityToDto2(QuestionDto dto) {
		dto.setQuestion_id(this.id);
		dto.setContent(this.content);
		dto.setStatus(this.status);
		for (Answer item : this.answers) {
			AnswerDto answerDto = new AnswerDto(item.getId(), item.getContent(), item.getCorrect());
			dto.getAnswers().add(answerDto);
		}

	}

}
