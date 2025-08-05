package com.example.exam.service;

import com.example.exam.dto.ExamDto;
import com.example.exam.entity.Exam;
import com.example.exam.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamService {
    
    @Autowired
    private ExamRepository examRepository;
    
    public List<ExamDto> getAllExams() {
        return examRepository.findByIsActiveTrue().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public ExamDto getExamById(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài thi với id: " + id));
        return convertToDto(exam);
    }
    
    public ExamDto createExam(ExamDto examDto) {
        Exam exam = new Exam();
        exam.setTitle(examDto.getTitle());
        exam.setDescription(examDto.getDescription());
        exam.setTimeLimit(examDto.getTimeLimit());
        exam.setIsActive(true);
        
        Exam savedExam = examRepository.save(exam);
        return convertToDto(savedExam);
    }
    
    public List<ExamDto> generateRandomExam(Long examId, int questionCount) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài thi với id: " + examId));
        
        List<Exam> exams = examRepository.findByIsActiveTrue();
        Collections.shuffle(exams);
        
        return exams.stream()
                .limit(questionCount)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    private ExamDto convertToDto(Exam exam) {
        ExamDto dto = new ExamDto();
        dto.setId(exam.getId());
        dto.setTitle(exam.getTitle());
        dto.setDescription(exam.getDescription());
        dto.setTimeLimit(exam.getTimeLimit());
        dto.setCreatedAt(exam.getCreatedAt());
        dto.setIsActive(exam.getIsActive());
        return dto;
    }
} 