package com.example.exam.controller;

import com.example.exam.dto.AnswerDto;
import com.example.exam.dto.ResultDto;
import com.example.exam.service.AnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {
    
    private final AnswerService answerService;
    
    @PostMapping("/submit")
    public ResponseEntity<ResultDto> submitAnswers(@Valid @RequestBody List<AnswerDto> answers) {
        ResultDto result = answerService.submitAnswers(answers);
        return ResponseEntity.ok(result);
    }
} 