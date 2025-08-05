package com.example.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {
    
    private Long id;
    
    private Double score;
    
    private Integer totalQuestions;
    
    private Integer correctAnswers;
    
    private LocalDateTime completedAt;
    
    private Long userId;
    
    private Long examId;
    
    private String examTitle;
    
    private String userName;
} 