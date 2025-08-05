package com.example.exam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {
    
    private Long id;
    
    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;
    
    private String description;
    
    @NotNull(message = "Thời gian làm bài không được để trống")
    private Integer timeLimit;
    
    private LocalDateTime createdAt;
    
    private Boolean isActive;
    
    private List<QuestionDto> questions;
} 