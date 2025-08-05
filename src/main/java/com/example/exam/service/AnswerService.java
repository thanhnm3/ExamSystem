package com.example.exam.service;

import com.example.exam.dto.AnswerDto;
import com.example.exam.dto.ResultDto;
import com.example.exam.entity.*;
import com.example.exam.repository.AnswerRepository;
import com.example.exam.repository.ExamRepository;
import com.example.exam.repository.QuestionRepository;
import com.example.exam.repository.ResultRepository;
import com.example.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnswerService {
    
    @Autowired
    private AnswerRepository answerRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private ExamRepository examRepository;
    
    @Autowired
    private ResultRepository resultRepository;
    
    public ResultDto submitAnswers(List<AnswerDto> answerDtos) {
        if (answerDtos.isEmpty()) {
            throw new RuntimeException("Danh sách đáp án không được để trống");
        }
        
        // Lấy thông tin user và exam từ answer đầu tiên
        Long userId = answerDtos.get(0).getUserId();
        Long examId = answerDtos.get(0).getExamId();
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user với id: " + userId));
        
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài thi với id: " + examId));
        
        // Lưu tất cả đáp án
        List<Answer> answers = answerDtos.stream()
                .map(dto -> {
                    Question question = questionRepository.findById(dto.getQuestionId())
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy câu hỏi với id: " + dto.getQuestionId()));
                    
                    Answer answer = new Answer();
                    answer.setSelectedOption(Question.CorrectOption.valueOf(dto.getSelectedOption()));
                    answer.setUser(user);
                    answer.setQuestion(question);
                    answer.setExam(exam);
                    
                    return answerRepository.save(answer);
                })
                .collect(Collectors.toList());
        
        // Chấm điểm
        double score = calculateScore(answers);
        
        // Lưu kết quả
        Result result = new Result();
        result.setScore(score);
        result.setTotalQuestions(answers.size());
        result.setCorrectAnswers((int) answers.stream()
                .filter(answer -> answer.getSelectedOption().equals(answer.getQuestion().getCorrectOption()))
                .count());
        result.setUser(user);
        result.setExam(exam);
        
        Result savedResult = resultRepository.save(result);
        return convertToDto(savedResult);
    }
    
    private double calculateScore(List<Answer> answers) {
        // Thuật toán chấm điểm: sử dụng HashMap để so sánh đáp án
        Map<Long, Question.CorrectOption> correctAnswers = new HashMap<>();
        
        // Tạo map đáp án đúng
        for (Answer answer : answers) {
            correctAnswers.put(answer.getQuestion().getId(), answer.getQuestion().getCorrectOption());
        }
        
        // Đếm số câu trả lời đúng
        int correctCount = 0;
        for (Answer answer : answers) {
            if (answer.getSelectedOption().equals(correctAnswers.get(answer.getQuestion().getId()))) {
                correctCount++;
            }
        }
        
        // Tính điểm (thang điểm 10)
        return (double) correctCount / answers.size() * 10;
    }
    
    private ResultDto convertToDto(Result result) {
        ResultDto dto = new ResultDto();
        dto.setId(result.getId());
        dto.setScore(result.getScore());
        dto.setTotalQuestions(result.getTotalQuestions());
        dto.setCorrectAnswers(result.getCorrectAnswers());
        dto.setCompletedAt(result.getCompletedAt());
        dto.setUserId(result.getUser().getId());
        dto.setExamId(result.getExam().getId());
        dto.setExamTitle(result.getExam().getTitle());
        dto.setUserName(result.getUser().getName());
        return dto;
    }
} 