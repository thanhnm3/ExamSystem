package com.example.exam.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nội dung câu hỏi không được để trống")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    
    @NotBlank(message = "Đáp án A không được để trống")
    @Column(nullable = false)
    private String optionA;
    
    @NotBlank(message = "Đáp án B không được để trống")
    @Column(nullable = false)
    private String optionB;
    
    @NotBlank(message = "Đáp án C không được để trống")
    @Column(nullable = false)
    private String optionC;
    
    @NotBlank(message = "Đáp án D không được để trống")
    @Column(nullable = false)
    private String optionD;
    
    @NotNull(message = "Đáp án đúng không được để trống")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CorrectOption correctOption;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;
    
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;
    
    public enum CorrectOption {
        A, B, C, D
    }
} 