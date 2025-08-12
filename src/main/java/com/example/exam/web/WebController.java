package com.example.exam.web;

import com.example.exam.dto.ExamDto;
import com.example.exam.service.ExamService;
import com.example.exam.service.QuestionService;
import com.example.exam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * FRONTEND CONTROLLER - Xử lý các trang web
 * Khác với API Controller chỉ trả về JSON
 */
@Controller
@RequestMapping("/web")
@RequiredArgsConstructor
public class WebController {
    
    private final ExamService examService;
    private final QuestionService questionService;
    private final UserService userService;
    
    /**
     * Trang chủ - Dashboard
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalExams", examService.getAllExams().size());
        // Sử dụng method có sẵn, lấy questions từ exam đầu tiên nếu có
        try {
            List<ExamDto> exams = examService.getAllExams();
            if (!exams.isEmpty()) {
                model.addAttribute("totalQuestions", questionService.getQuestionsByExamId(exams.get(0).getId()).size());
            } else {
                model.addAttribute("totalQuestions", 0);
            }
        } catch (Exception e) {
            model.addAttribute("totalQuestions", 0);
        }
        model.addAttribute("totalUsers", userService.getAllUsers().size());
        return "frontend/home";
    }
    
    /**
     * Trang chủ đơn giản để test
     */
    @GetMapping("/simple")
    public String homeSimple(Model model) {
        model.addAttribute("totalExams", examService.getAllExams().size());
        try {
            List<ExamDto> exams = examService.getAllExams();
            if (!exams.isEmpty()) {
                model.addAttribute("totalQuestions", questionService.getQuestionsByExamId(exams.get(0).getId()).size());
            } else {
                model.addAttribute("totalQuestions", 0);
            }
        } catch (Exception e) {
            model.addAttribute("totalQuestions", 0);
        }
        model.addAttribute("totalUsers", userService.getAllUsers().size());
        return "frontend/home-simple";
    }
    
    /**
     * Trang quản lý Exam
     */
    @GetMapping("/exams")
    public String examManagement(Model model) {
        model.addAttribute("exams", examService.getAllExams());
        return "frontend/exam-management";
    }
    
    /**
     * Trang tạo Exam mới
     */
    @GetMapping("/exams/create")
    public String createExamForm(Model model) {
        return "frontend/exam-create";
    }
    
    /**
     * Trang quản lý Question
     */
    @GetMapping("/questions")
    public String questionManagement(Model model) {
        // Lấy questions từ exam đầu tiên để hiển thị
        try {
            List<ExamDto> exams = examService.getAllExams();
            if (!exams.isEmpty()) {
                model.addAttribute("questions", questionService.getQuestionsByExamId(exams.get(0).getId()));
            } else {
                model.addAttribute("questions", List.of());
            }
        } catch (Exception e) {
            model.addAttribute("questions", List.of());
        }
        return "frontend/question-management";
    }
    
    /**
     * Trang quản lý User
     */
    @GetMapping("/users")
    public String userManagement(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "frontend/user-management";
    }
    
    /**
     * Xử lý lỗi template parsing
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", "Đã xảy ra lỗi: " + ex.getMessage());
        modelAndView.addObject("timestamp", new java.util.Date());
        return modelAndView;
    }
}
