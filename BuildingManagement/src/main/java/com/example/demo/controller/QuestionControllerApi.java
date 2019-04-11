package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.QuestionDto;
import com.example.demo.service.QuestionService;
import com.example.demo.untils.Constant;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class QuestionControllerApi {
	@Autowired
	private QuestionService questionService;

	@GetMapping("/get-all/question")
	public List<QuestionDto> getAllQuestion() {
		List<QuestionDto> dtos = questionService.getAllQuestion();
		Collections.shuffle(dtos);
		dtos = dtos.subList(Constant.bein, Constant.end);
//		questionService.randomAnswer(dtos);
		return dtos;
	}

	@GetMapping("/get-question/{questionID}")
	public QuestionDto getOneQuestion(@PathVariable(value = "questionID", required = false) Integer questionID) {
		QuestionDto questionDto = questionService.getQuestion(questionID);
		return questionDto;

	}

	
	@GetMapping("/check-answer/{answerId}")
	public QuestionDto checkAnswer(@PathVariable(value = "answerId", required = false) Integer answerId) {
		QuestionDto questionDto = questionService.checkAnswerQuestion(answerId);
		return questionDto;
	}
	
	@PostMapping("/check-answers")
	public List<QuestionDto> checkAnswers(@RequestBody List<QuestionDto> questionDtos){
		List<QuestionDto> dtos = questionService.checkAnswerInListQuestion(questionDtos);
		return dtos;
	}
	

}
