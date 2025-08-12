package com.example.exam.controller;

import com.example.exam.dto.QuestionDto;
import com.example.exam.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    
    private final QuestionService questionService;
    
    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByExamId(@PathVariable Long examId) {
        List<QuestionDto> questions = questionService.getQuestionsByExamId(examId);
        return ResponseEntity.ok(questions);
    }
    
    @PostMapping
    public ResponseEntity<QuestionDto> createQuestion(@Valid @RequestBody QuestionDto questionDto) {
        QuestionDto createdQuestion = questionService.createQuestion(questionDto);
        return ResponseEntity.ok(createdQuestion);
    }
    
    @GetMapping("/random/{examId}")
    public ResponseEntity<List<QuestionDto>> getRandomQuestions(@PathVariable Long examId, @RequestParam(defaultValue = "10") int count) {
        List<QuestionDto> randomQuestions = questionService.getRandomQuestions(examId, count);
        return ResponseEntity.ok(randomQuestions);
    }
} 