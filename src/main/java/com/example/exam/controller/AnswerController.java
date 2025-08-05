package com.example.exam.controller;

import com.example.exam.dto.AnswerDto;
import com.example.exam.dto.ResultDto;
import com.example.exam.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
@Tag(name = "Answer Management", description = "APIs for answer management")
public class AnswerController {
    
    @Autowired
    private AnswerService answerService;
    
    @PostMapping
    @Operation(summary = "Submit answers", description = "Submit answers for an exam and get results")
    public ResponseEntity<ResultDto> submitAnswers(@Valid @RequestBody List<AnswerDto> answerDtos) {
        ResultDto result = answerService.submitAnswers(answerDtos);
        return ResponseEntity.ok(result);
    }
} 