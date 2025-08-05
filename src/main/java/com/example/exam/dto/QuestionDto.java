package com.example.exam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    
    private Long id;
    
    @NotBlank(message = "Nội dung câu hỏi không được để trống")
    private String content;
    
    @NotBlank(message = "Đáp án A không được để trống")
    private String optionA;
    
    @NotBlank(message = "Đáp án B không được để trống")
    private String optionB;
    
    @NotBlank(message = "Đáp án C không được để trống")
    private String optionC;
    
    @NotBlank(message = "Đáp án D không được để trống")
    private String optionD;
    
    @NotNull(message = "Đáp án đúng không được để trống")
    private String correctOption;
    
    private Long examId;
} 