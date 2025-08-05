package com.example.exam.config;

import com.example.exam.entity.*;
import com.example.exam.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ExamRepository examRepository;
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Tạo dữ liệu mẫu nếu chưa có
        if (userRepository.count() == 0) {
            createSampleData();
        }
    }
    
    private void createSampleData() {
        // Tạo admin user
        User admin = new User();
        admin.setName("Admin User");
        admin.setEmail("admin@example.com");
        admin.setPassword(passwordEncoder.encode("123456"));
        admin.setRole(User.UserRole.ADMIN);
        userRepository.save(admin);
        
        // Tạo user thường
        User user1 = new User();
        user1.setName("Nguyễn Văn A");
        user1.setEmail("user1@example.com");
        user1.setPassword(passwordEncoder.encode("123456"));
        user1.setRole(User.UserRole.USER);
        userRepository.save(user1);
        
        User user2 = new User();
        user2.setName("Trần Thị B");
        user2.setEmail("user2@example.com");
        user2.setPassword(passwordEncoder.encode("123456"));
        user2.setRole(User.UserRole.USER);
        userRepository.save(user2);
        
        // Tạo bài thi mẫu
        Exam exam1 = new Exam();
        exam1.setTitle("Bài thi Java Core");
        exam1.setDescription("Bài thi kiểm tra kiến thức Java Core cơ bản");
        exam1.setTimeLimit(30);
        exam1.setIsActive(true);
        examRepository.save(exam1);
        
        Exam exam2 = new Exam();
        exam2.setTitle("Bài thi Spring Boot");
        exam2.setDescription("Bài thi kiểm tra kiến thức Spring Boot");
        exam2.setTimeLimit(45);
        exam2.setIsActive(true);
        examRepository.save(exam2);
        
        // Tạo câu hỏi mẫu cho bài thi Java Core
        createQuestion(exam1, "Java là ngôn ngữ lập trình gì?", 
            "Hướng đối tượng", "Hướng thủ tục", "Hướng hàm", "Hướng logic", "A");
        
        createQuestion(exam1, "Từ khóa nào dùng để khai báo hằng số trong Java?", 
            "const", "final", "static", "constant", "B");
        
        createQuestion(exam1, "Phương thức nào dùng để so sánh nội dung của hai String?", 
            "==", "equals()", "compareTo()", "toString()", "B");
        
        createQuestion(exam1, "Interface trong Java có thể chứa gì?", 
            "Chỉ abstract methods", "Chỉ default methods", "Cả abstract và default methods", "Chỉ static methods", "C");
        
        createQuestion(exam1, "Exception nào là checked exception?", 
            "RuntimeException", "NullPointerException", "IOException", "ArrayIndexOutOfBoundsException", "C");
        
        // Tạo câu hỏi mẫu cho bài thi Spring Boot
        createQuestion(exam2, "Annotation nào dùng để đánh dấu một class là Spring Bean?", 
            "@Component", "@Service", "@Repository", "@Controller", "A");
        
        createQuestion(exam2, "Cách nào để inject dependency trong Spring?", 
            "Chỉ @Autowired", "Chỉ @Inject", "Cả @Autowired và @Inject", "Chỉ constructor injection", "C");
        
        createQuestion(exam2, "JPA là viết tắt của gì?", 
            "Java Persistence API", "Java Programming Application", "Java Platform Architecture", "Java Process API", "A");
        
        createQuestion(exam2, "Entity trong JPA được đánh dấu bằng annotation nào?", 
            "@Entity", "@Table", "@Model", "@Data", "A");
        
        createQuestion(exam2, "Spring Boot sử dụng embedded server nào mặc định?", 
            "Tomcat", "Jetty", "Undertow", "WildFly", "A");
    }
    
    private void createQuestion(Exam exam, String content, String optionA, String optionB, String optionC, String optionD, String correctOption) {
        Question question = new Question();
        question.setContent(content);
        question.setOptionA(optionA);
        question.setOptionB(optionB);
        question.setOptionC(optionC);
        question.setOptionD(optionD);
        question.setCorrectOption(Question.CorrectOption.valueOf(correctOption));
        question.setExam(exam);
        questionRepository.save(question);
    }
} 