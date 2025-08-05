# 🎯 Online Exam System - Hệ thống quản lý kỳ thi trực tuyến

## 📋 Mô tả dự án

Dự án **Online Exam System** là một ứng dụng web Spring Boot được thiết kế để ôn tập và thực hành các kiến thức Java Core, Web (CDI, JPA, Hibernate, Spring) và Giải thuật. Hệ thống cho phép người dùng đăng ký tài khoản, làm bài trắc nghiệm, xem kết quả và admin có thể quản lý bài thi.

## 🏗️ Kiến trúc dự án

```
src/main/java/com/example/exam/
├── ExamApplication.java          # Main application class
├── config/                       # Cấu hình
│   ├── SecurityConfig.java       # Cấu hình bảo mật
│   └── SwaggerConfig.java        # Cấu hình API documentation
├── controller/                   # REST Controllers
│   ├── UserController.java       # Quản lý người dùng
│   ├── ExamController.java       # Quản lý bài thi
│   ├── QuestionController.java   # Quản lý câu hỏi
│   └── AnswerController.java     # Xử lý đáp án
├── dto/                         # Data Transfer Objects
│   ├── UserDto.java
│   ├── ExamDto.java
│   ├── QuestionDto.java
│   ├── AnswerDto.java
│   └── ResultDto.java
├── entity/                      # JPA Entities
│   ├── User.java               # Người dùng
│   ├── Exam.java               # Bài thi
│   ├── Question.java           # Câu hỏi
│   ├── Answer.java             # Đáp án
│   └── Result.java             # Kết quả
├── repository/                  # Data Access Layer
│   ├── UserRepository.java
│   ├── ExamRepository.java
│   ├── QuestionRepository.java
│   ├── AnswerRepository.java
│   └── ResultRepository.java
└── service/                     # Business Logic Layer
    ├── UserService.java
    ├── ExamService.java
    ├── QuestionService.java
    └── AnswerService.java
```

## 🚀 Tính năng chính

### 👤 Quản lý người dùng
- ✅ Đăng ký tài khoản với validation
- ✅ Xem danh sách người dùng
- ✅ Sắp xếp người dùng theo điểm giảm dần
- ✅ Phân quyền Admin/User

### 📝 Quản lý bài thi
- ✅ Tạo bài thi mới
- ✅ Xem danh sách bài thi
- ✅ Sinh ngẫu nhiên đề thi
- ✅ Quản lý trạng thái bài thi

### ❓ Quản lý câu hỏi
- ✅ Thêm câu hỏi trắc nghiệm (4 đáp án)
- ✅ Xem câu hỏi theo bài thi
- ✅ Lấy câu hỏi ngẫu nhiên
- ✅ Validation dữ liệu

### 📊 Chấm điểm và kết quả
- ✅ Tự động chấm điểm bài làm
- ✅ Thuật toán so sánh đáp án (HashMap)
- ✅ Tính điểm theo thang 10
- ✅ Xem kết quả chi tiết

## 🛠️ Công nghệ sử dụng

| Thành phần | Công nghệ | Mục đích |
|------------|-----------|----------|
| **Java Core** | Java 21 | Viết model, logic tính điểm, xử lý chuỗi |
| **Spring Boot** | 3.5.4 | Framework chính, tạo REST API |
| **JPA + Hibernate** | Spring Data JPA | Ánh xạ entity với MySQL |
| **Database** | MySQL 8.0 | Lưu trữ dữ liệu |
| **Validation** | Bean Validation | Ràng buộc dữ liệu |
| **Security** | Spring Security | Bảo mật ứng dụng |
| **API Documentation** | Swagger UI | Tài liệu API trực quan |
| **Build Tool** | Maven | Quản lý dependencies |

## 📦 Cài đặt và chạy

### Yêu cầu hệ thống
- Java 21+
- MySQL 8.0+
- Maven 3.6+

### Bước 1: Clone dự án
```bash
git clone <repository-url>
cd exam
```

### Bước 2: Cấu hình database
1. Tạo database MySQL:
```sql
CREATE DATABASE exam_system;
```

2. Cập nhật thông tin kết nối trong `src/main/resources/application.properties`:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Bước 3: Chạy ứng dụng
```bash
# Sử dụng Maven
mvn spring-boot:run

# Hoặc build và chạy JAR
mvn clean package
java -jar target/exam-0.0.1-SNAPSHOT.jar
```

### Bước 4: Truy cập ứng dụng
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Base URL**: http://localhost:8080/api

## 📚 API Endpoints

### 👤 User Management
| Method | Endpoint | Mô tả |
|--------|----------|-------|
| POST | `/api/users` | Đăng ký người dùng mới |
| GET | `/api/users` | Lấy danh sách người dùng |
| GET | `/api/users/{id}` | Lấy thông tin người dùng |
| GET | `/api/users/sorted-by-score` | Sắp xếp người dùng theo điểm |

### 📝 Exam Management
| Method | Endpoint | Mô tả |
|--------|----------|-------|
| GET | `/api/exams` | Lấy danh sách bài thi |
| GET | `/api/exams/{id}` | Lấy thông tin bài thi |
| POST | `/api/exams` | Tạo bài thi mới |
| GET | `/api/exams/{id}/random` | Sinh đề thi ngẫu nhiên |

### ❓ Question Management
| Method | Endpoint | Mô tả |
|--------|----------|-------|
| GET | `/api/questions/exam/{examId}` | Lấy câu hỏi theo bài thi |
| POST | `/api/questions` | Tạo câu hỏi mới |
| GET | `/api/questions/exam/{examId}/random` | Lấy câu hỏi ngẫu nhiên |

### 📊 Answer & Results
| Method | Endpoint | Mô tả |
|--------|----------|-------|
| POST | `/api/answers` | Nộp bài và chấm điểm |

## 🧮 Thuật toán thực hiện

### 1. Thuật toán chấm điểm
```java
// Sử dụng HashMap để so sánh đáp án
Map<Long, Question.CorrectOption> correctAnswers = new HashMap<>();
for (Answer answer : answers) {
    correctAnswers.put(answer.getQuestion().getId(), answer.getQuestion().getCorrectOption());
}

// Đếm câu trả lời đúng
int correctCount = 0;
for (Answer answer : answers) {
    if (answer.getSelectedOption().equals(correctAnswers.get(answer.getQuestion().getId()))) {
        correctCount++;
    }
}

// Tính điểm (thang điểm 10)
double score = (double) correctCount / answers.size() * 10;
```

### 2. Thuật toán sắp xếp người dùng theo điểm
```java
// Sử dụng Comparator để sắp xếp
users.stream()
    .sorted((u1, u2) -> {
        double score1 = u1.getResults().stream().mapToDouble(r -> r.getScore()).average().orElse(0.0);
        double score2 = u2.getResults().stream().mapToDouble(r -> r.getScore()).average().orElse(0.0);
        return Double.compare(score2, score1); // Giảm dần
    })
```

### 3. Thuật toán sinh đề thi ngẫu nhiên
```java
// Sử dụng Collections.shuffle
List<Exam> exams = examRepository.findByIsActiveTrue();
Collections.shuffle(exams);
return exams.stream().limit(questionCount).collect(Collectors.toList());
```

## 🔧 Validation Rules

### User Validation
- ✅ Tên: `@NotBlank`
- ✅ Email: `@Email` + `@NotBlank`
- ✅ Password: `@Size(min=6)` + `@NotBlank`

### Exam Validation
- ✅ Tiêu đề: `@NotBlank`
- ✅ Thời gian: `@NotNull`

### Question Validation
- ✅ Nội dung: `@NotBlank`
- ✅ Các đáp án: `@NotBlank`
- ✅ Đáp án đúng: `@NotNull`

## 🧪 Testing

### Test với Swagger UI
1. Truy cập: http://localhost:8080/swagger-ui.html
2. Chọn API endpoint cần test
3. Click "Try it out"
4. Nhập dữ liệu và thực thi

### Test với Postman
Import collection từ file `postman_collection.json` (nếu có)

## 📈 Tính năng mở rộng (Future)

- 🔐 JWT Authentication
- 📊 Dashboard thống kê
- 🎨 Frontend React/Vue.js
- 📧 Email notification
- 📱 Mobile app
- 🔍 Tìm kiếm nâng cao
- 📋 Export kết quả PDF

## 🤝 Đóng góp

1. Fork dự án
2. Tạo feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Tạo Pull Request

## 📄 License

Dự án này được phân phối dưới MIT License. Xem file `LICENSE` để biết thêm chi tiết.

## 👨‍💻 Tác giả

**Exam System Team**
- Email: contact@examsystem.com
- GitHub: [@examsystem](https://github.com/examsystem)

---

⭐ Nếu dự án này hữu ích, hãy cho chúng tôi một star! 