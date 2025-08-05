package com.example.exam.controller;

import com.example.exam.dto.ExamDto;
import com.example.exam.service.ExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@Tag(name = "Exam Management", description = "APIs for exam management")
public class ExamController {
    
    @Autowired
    private ExamService examService;
    
    @GetMapping
    @Operation(summary = "Get all exams", description = "Retrieve all active exams")
    public ResponseEntity<List<ExamDto>> getAllExams() {
        List<ExamDto> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get exam by ID", description = "Retrieve a specific exam by ID")
    public ResponseEntity<ExamDto> getExamById(@PathVariable Long id) {
        ExamDto exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }
    
    @PostMapping
    @Operation(summary = "Create new exam", description = "Create a new exam")
    public ResponseEntity<ExamDto> createExam(@Valid @RequestBody ExamDto examDto) {
        ExamDto createdExam = examService.createExam(examDto);
        return ResponseEntity.ok(createdExam);
    }
    
    @GetMapping("/{id}/random")
    @Operation(summary = "Generate random exam", description = "Generate a random exam with specified number of questions")
    public ResponseEntity<List<ExamDto>> generateRandomExam(
            @PathVariable Long id,
            @RequestParam(defaultValue = "10") int questionCount) {
        List<ExamDto> randomExams = examService.generateRandomExam(id, questionCount);
        return ResponseEntity.ok(randomExams);
    }
} 