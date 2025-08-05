package com.example.exam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    
    private Long id;
    
    @NotNull(message = "Đáp án được chọn không được để trống")
    private String selectedOption;
    
    private Long userId;
    
    private Long questionId;
    
    private Long examId;
} 