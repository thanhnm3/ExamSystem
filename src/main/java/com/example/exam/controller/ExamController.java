package com.example.exam.controller;

import com.example.exam.dto.ExamDto;
import com.example.exam.service.ExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
@Tag(name = "Exam Management")
public class ExamController {
    
    private final ExamService examService;
    
    @GetMapping
    @Operation(summary = "Get all exams")
    public ResponseEntity<List<ExamDto>> getAllExams() {
        List<ExamDto> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get exam by ID")
    public ResponseEntity<ExamDto> getExamById(@PathVariable Long id) {
        ExamDto exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }
    
    @PostMapping
    @Operation(summary = "Create new exam")
    public ResponseEntity<ExamDto> createExam(@Valid @RequestBody ExamDto examDto) {
        ExamDto createdExam = examService.createExam(examDto);
        return ResponseEntity.ok(createdExam);
    }
    
    @PostMapping("/generate-random")
    @Operation(summary = "Generate random exam")
    public ResponseEntity<List<ExamDto>> generateRandomExam(@RequestParam Long examId, @RequestParam int questionCount) {
        List<ExamDto> randomExams = examService.generateRandomExam(examId, questionCount);
        return ResponseEntity.ok(randomExams);
    }
} 