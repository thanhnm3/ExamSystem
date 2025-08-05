package com.example.exam.service;

import com.example.exam.dto.QuestionDto;
import com.example.exam.entity.Exam;
import com.example.exam.entity.Question;
import com.example.exam.repository.ExamRepository;
import com.example.exam.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private ExamRepository examRepository;
    
    public List<QuestionDto> getQuestionsByExamId(Long examId) {
        return questionRepository.findByExamIdOrderById(examId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public QuestionDto createQuestion(QuestionDto questionDto) {
        Exam exam = examRepository.findById(questionDto.getExamId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài thi với id: " + questionDto.getExamId()));
        
        Question question = new Question();
        question.setContent(questionDto.getContent());
        question.setOptionA(questionDto.getOptionA());
        question.setOptionB(questionDto.getOptionB());
        question.setOptionC(questionDto.getOptionC());
        question.setOptionD(questionDto.getOptionD());
        question.setCorrectOption(Question.CorrectOption.valueOf(questionDto.getCorrectOption()));
        question.setExam(exam);
        
        Question savedQuestion = questionRepository.save(question);
        return convertToDto(savedQuestion);
    }
    
    public List<QuestionDto> getRandomQuestions(Long examId, int count) {
        List<Question> questions = questionRepository.findByExamId(examId);
        Collections.shuffle(questions);
        
        return questions.stream()
                .limit(count)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    private QuestionDto convertToDto(Question question) {
        QuestionDto dto = new QuestionDto();
        dto.setId(question.getId());
        dto.setContent(question.getContent());
        dto.setOptionA(question.getOptionA());
        dto.setOptionB(question.getOptionB());
        dto.setOptionC(question.getOptionC());
        dto.setOptionD(question.getOptionD());
        dto.setCorrectOption(question.getCorrectOption().name());
        dto.setExamId(question.getExam().getId());
        return dto;
    }
} 