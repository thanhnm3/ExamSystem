package com.example.exam.controller;

import com.example.exam.dto.QuestionDto;
import com.example.exam.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@Tag(name = "Question Management", description = "APIs for question management")
public class QuestionController {
    
    @Autowired
    private QuestionService questionService;
    
    @GetMapping("/exam/{examId}")
    @Operation(summary = "Get questions by exam ID", description = "Retrieve all questions for a specific exam")
    public ResponseEntity<List<QuestionDto>> getQuestionsByExamId(@PathVariable Long examId) {
        List<QuestionDto> questions = questionService.getQuestionsByExamId(examId);
        return ResponseEntity.ok(questions);
    }
    
    @PostMapping
    @Operation(summary = "Create new question", description = "Create a new question for an exam")
    public ResponseEntity<QuestionDto> createQuestion(@Valid @RequestBody QuestionDto questionDto) {
        QuestionDto createdQuestion = questionService.createQuestion(questionDto);
        return ResponseEntity.ok(createdQuestion);
    }
    
    @GetMapping("/exam/{examId}/random")
    @Operation(summary = "Get random questions", description = "Get random questions for an exam")
    public ResponseEntity<List<QuestionDto>> getRandomQuestions(
            @PathVariable Long examId,
            @RequestParam(defaultValue = "10") int count) {
        List<QuestionDto> questions = questionService.getRandomQuestions(examId, count);
        return ResponseEntity.ok(questions);
    }
} 