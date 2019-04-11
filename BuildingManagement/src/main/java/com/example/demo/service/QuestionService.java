package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.AnswerDto;
import com.example.demo.dto.QuestionDto;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;

@Service
@Transactional
public class QuestionService {
	

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	public List<QuestionDto> getAllQuestion() {
		List<QuestionDto> questionDtos = new ArrayList<>();
		List<Question> questions = questionRepository.findAll();
		for (Question item : questions) {
			QuestionDto dto = new QuestionDto();
			item.entityToDto1(dto);
			questionDtos.add(dto);
		}
		return questionDtos;
	}

	public Integer getValueCorectQuestion(QuestionDto questionDto) {
		Question question = questionRepository.findById(questionDto.getQuestion_id()).get();
		for (Answer item : question.getAnswers()) {
			if (item.getCorrect() == 1) {
				return 1;
			}
		}
		return 0;
	}

	public QuestionDto getQuestion(Integer Id) {
		Question question = questionRepository.findOneById(Id);
		QuestionDto dto = new QuestionDto();
		question.entityToDto1(dto);
		return dto;
	}

	public QuestionDto checkAnswerQuestion(Integer id) {
		Answer answer = answerRepository.findById(id).get();
		QuestionDto questionDto = new QuestionDto();
		answer.getQuestion().entityToDto2(questionDto);
		if (answer.getCorrect() == 0) {
			questionDto.setCheckAnswer(id);
			return questionDto;
		} else {
			return questionDto;
		}

	}

	public void randomAnswer(List<QuestionDto> dtos) {
		for (QuestionDto questionDto : dtos) {
			Collections.shuffle(questionDto.getAnswers());
		}
	}

	
	public List<QuestionDto> checkAnswerInListQuestion(List<QuestionDto> questionDtos) {

		for (QuestionDto questionDto : questionDtos) {

			questionDto.getAnswers().clear();
			Question question = questionRepository.findOneById(questionDto.getQuestion_id());
			for (Answer item : question.getAnswers()) {
				AnswerDto answerDto = new AnswerDto();
				item.entityToDto(answerDto);
				questionDto.getAnswers().add(answerDto);
				if(questionDto.getCheckAnswer() == item.getId()) {
					answerDto.setChecked(true);
				}
			}
			Answer answer = answerRepository.findById(questionDto.getCheckAnswer()).get();
			if (answer.getCorrect() == 1) {
				questionDto.setCheckAnswer(null);
			}
			
			

		}

		return questionDtos;

	}

}
