# 🎓 Hệ Thống Thi Trực Tuyến (Online Exam System)

## 📋 Tổng Quan Dự Án

Dự án **Hệ Thống Thi Trực Tuyến** được xây dựng bằng **Spring Boot REST API**, cung cấp một nền tảng hoàn chỉnh để quản lý và thực hiện các bài thi trực tuyến. Hệ thống hỗ trợ quản lý người dùng, bài thi, câu hỏi, đáp án và kết quả thi.

## 🏗️ Kiến Trúc Dự Án

### **Spring Boot REST API Architecture**
```
┌─────────────────────────────────────────────────────────────┐
│                    Client Applications                      │
│  (Web App, Mobile App, Postman, etc.)                       │
└─────────────────────┬───────────────────────────────────────┘
                      │ HTTP/REST
                      ▼
┌─────────────────────────────────────────────────────────────┐
│                 Spring Boot Application                     │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────┐  │
│  │   Controllers   │  │     Services    │  │ Repositories│  │
│  │   (REST APIs)   │  │  (Business      │  │  (Data      │  │
│  │                 │  │   Logic)        │  │  Access)    │  │
│  └─────────────────┘  └─────────────────┘  └─────────────┘  │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────┐  │
│  │      DTOs       │  │    Entities     │  │   Config    │  │
│  │  (Data Transfer │  │  (JPA Models)   │  │ (Security,  │  │
│  │     Objects)    │  │                 │  │   etc.)     │  │
│  └─────────────────┘  └─────────────────┘  └─────────────┘  │
└─────────────────────┬───────────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────────┐
│                    H2 Database Layer                        │
│  ┌─────────────────────────────────────────────────────┐    │
│  │              H2 In-Memory Database                  │    │
│  │              (Development/Testing)                  │    │
│  │                                                     │    │
│  │  • Tự động tạo schema khi khởi động                 │    │
│  │  • Dữ liệu mất khi restart ứng dụng                 │    │
│  │  • H2 Console để xem dữ liệu                        │    │
│  └─────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────┘
```

## 🚀 Công Nghệ Sử Dụng

### **Backend Framework**
- **Spring Boot 3.5.4** - Framework chính
- **Java 21** - Ngôn ngữ lập trình
- **Spring Data JPA** - ORM và quản lý dữ liệu
- **Spring Security** - Bảo mật và xác thực
- **Spring Validation** - Validation dữ liệu

### **Database**
- **H2 Database** - Database in-memory cho development và testing
- **Hibernate** - JPA implementation
- **H2 Console** - Giao diện web để xem và quản lý dữ liệu

### **Development Tools**
- **Maven** - Build tool và dependency management
- **Lombok** - Giảm boilerplate code
- **Spring Boot DevTools** - Hot reload và development

### **Testing**
- **Spring Boot Test** - Testing framework
- **Spring Security Test** - Security testing

## 📁 Cấu Trúc Thư Mục và Nhiệm Vụ

### **Root Directory (`/`)**
```
exam/
├── mvnw                    # Maven wrapper script (Unix/Linux)
├── mvnw.cmd               # Maven wrapper script (Windows)
├── pom.xml                # Maven project configuration
├── README.md              # Project documentation
├── requirement.txt        # Project requirements
└── testfunc.txt          # Test functionality notes
```

### **Source Code (`/src/main/java/com/example/exam/`)**

#### **1. Controllers (`/controller/`) - REST API Endpoints**
```
controller/
├── AnswerController.java   # Quản lý đáp án (CRUD operations)
├── ExamController.java     # Quản lý bài thi (CRUD, generate random)
├── QuestionController.java # Quản lý câu hỏi (CRUD operations)
└── UserController.java     # Quản lý người dùng (CRUD, authentication)
```

**Nhiệm vụ:**
- Định nghĩa các REST API endpoints
- Xử lý HTTP requests (GET, POST, PUT, DELETE)
- Validation input data
- Trả về HTTP responses
- Mapping giữa HTTP requests và business logic

#### **2. Services (`/service/`) - Business Logic Layer**
```
service/
├── AnswerService.java      # Logic xử lý đáp án
├── ExamService.java        # Logic xử lý bài thi
├── QuestionService.java    # Logic xử lý câu hỏi
└── UserService.java        # Logic xử lý người dùng
```

**Nhiệm vụ:**
- Chứa business logic của ứng dụng
- Xử lý dữ liệu trước khi lưu vào database
- Validation business rules
- Gọi repositories để truy xuất/lưu dữ liệu
- Chuyển đổi giữa Entity và DTO

#### **3. Repositories (`/repository/`) - Data Access Layer**
```
repository/
├── AnswerRepository.java   # Truy xuất dữ liệu đáp án
├── ExamRepository.java     # Truy xuất dữ liệu bài thi
├── QuestionRepository.java # Truy xuất dữ liệu câu hỏi
├── ResultRepository.java   # Truy xuất dữ liệu kết quả
└── UserRepository.java     # Truy xuất dữ liệu người dùng
```

**Nhiệm vụ:**
- Interface với Spring Data JPA
- Định nghĩa các custom queries
- Truy xuất, lưu, cập nhật, xóa dữ liệu
- Tương tác trực tiếp với database

#### **4. Entities (`/entity/`) - JPA Models**
```
entity/
├── Answer.java             # Model đáp án
├── Exam.java              # Model bài thi
├── Question.java           # Model câu hỏi
├── Result.java             # Model kết quả thi
└── User.java               # Model người dùng
```

**Nhiệm vụ:**
- Định nghĩa cấu trúc database tables
- JPA annotations (@Entity, @Table, @Column)
- Relationships giữa các entities
- Validation constraints
- Lombok annotations để giảm boilerplate

#### **5. DTOs (`/dto/`) - Data Transfer Objects**
```
dto/
├── AnswerDto.java          # DTO cho đáp án
├── ExamDto.java            # DTO cho bài thi
├── QuestionDto.java        # DTO cho câu hỏi
├── ResultDto.java          # DTO cho kết quả
└── UserDto.java            # DTO cho người dùng
```

**Nhiệm vụ:**
- Chuyển đổi dữ liệu giữa layers
- Ẩn thông tin nhạy cảm (như password)
- Validation input data
- Tối ưu hóa network transfer
- Mapping giữa Entity và external data

#### **6. Configuration (`/config/`) - Application Configuration**
```
config/
├── DataSeeder.java         # Khởi tạo dữ liệu mẫu
├── SecurityConfig.java     # Cấu hình bảo mật
└── (các config khác)
```

**Nhiệm vụ:**
- Cấu hình Spring Security
- Cấu hình database connection
- Cấu hình logging
- Khởi tạo dữ liệu mẫu
- Bean configurations

#### **7. Exception Handling (`/exception/`)**
```
exception/
└── GlobalExceptionHandler.java  # Xử lý exception toàn cục
```

**Nhiệm vụ:**
- Bắt và xử lý tất cả exceptions
- Trả về error responses có cấu trúc
- Logging errors
- User-friendly error messages

### **Resources (`/src/main/resources/`)**

#### **1. Configuration Files**
```
resources/
├── application.properties  # Application configuration
├── static/                # Static resources (CSS, JS, images)
└── templates/             # Template files (nếu cần)
```

**Nhiệm vụ:**
- Database connection settings
- Server configuration
- Logging configuration
- Security settings
- External service configurations

#### **2. Static Resources**
```
static/
├── css/                   # Stylesheets
├── js/                    # JavaScript files
└── images/                # Image files
```

**Nhiệm vụ:**
- Frontend assets
- UI components
- Client-side functionality

### **Test Directory (`/src/test/`)**
```
test/
└── java/com/example/exam/
    └── ExamApplicationTests.java  # Unit tests
```

**Nhiệm vụ:**
- Unit testing
- Integration testing
- Test data setup
- Mock objects

## 🔐 REST API Endpoints

### **Exam Management**
```
GET    /api/exams              # Lấy danh sách tất cả bài thi
GET    /api/exams/{id}         # Lấy bài thi theo ID
POST   /api/exams              # Tạo bài thi mới
POST   /api/exams/generate-random  # Tạo bài thi ngẫu nhiên
```

### **Question Management**
```
GET    /api/questions          # Lấy danh sách câu hỏi
GET    /api/questions/{id}     # Lấy câu hỏi theo ID
POST   /api/questions          # Tạo câu hỏi mới
PUT    /api/questions/{id}     # Cập nhật câu hỏi
DELETE /api/questions/{id}     # Xóa câu hỏi
```

### **Answer Management**
```
GET    /api/answers            # Lấy danh sách đáp án
GET    /api/answers/{id}       # Lấy đáp án theo ID
POST   /api/answers            # Tạo đáp án mới
PUT    /api/answers/{id}       # Cập nhật đáp án
DELETE /api/answers/{id}       # Xóa đáp án
```

### **User Management**
```
GET    /api/users              # Lấy danh sách người dùng
GET    /api/users/{id}         # Lấy người dùng theo ID
POST   /api/users              # Tạo người dùng mới
PUT    /api/users/{id}         # Cập nhật người dùng
DELETE /api/users/{id}         # Xóa người dùng
```

## 🗄️ H2 Database Schema

### **Entity Relationships**
```
User (1) ←→ (N) Result
  ↓
Exam (1) ←→ (N) Question
  ↓
Question (1) ←→ (N) Answer
  ↓
Result (N) ←→ (1) Exam
```

### **H2 Database Features**
- **In-Memory Database**: Dữ liệu được lưu trong RAM
- **Auto Schema Creation**: Tự động tạo tables khi khởi động
- **Data Persistence**: Dữ liệu mất khi restart ứng dụng
- **H2 Console**: Giao diện web để xem và quản lý dữ liệu

### **Table Structures**
- **users**: Thông tin người dùng
- **exams**: Thông tin bài thi
- **questions**: Câu hỏi trong bài thi
- **answers**: Đáp án của câu hỏi
- **results**: Kết quả thi của người dùng

## 🚀 Hướng Dẫn Chạy Dự Án

### **Prerequisites**
- Java 21+
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### **Setup và Chạy**

#### **Bước 1: Clone và Build Project**
```bash
# Clone repository
git clone <repository-url>
cd exam

# Build project
mvn clean install
```

#### **Bước 2: Chạy Ứng Dụng**
```bash
# Cách 1: Sử dụng Maven
mvn spring-boot:run

# Cách 2: Chạy từ IDE
# Mở ExamApplication.java và chạy main method

# Cách 3: Chạy JAR file
mvn clean package
java -jar target/exam-0.0.1-SNAPSHOT.jar
```

#### **Bước 3: Truy Cập Ứng Dụng**
- **Application**: http://localhost:8080
- **H2 Console**: http://localhost:8080/h2-console
- **API Documentation**: http://localhost:8080/swagger-ui.html

### **H2 Database Configuration**

#### **H2 Console Access**
1. Mở trình duyệt và truy cập: http://localhost:8080/h2-console
2. Nhập thông tin kết nối:
   - **JDBC URL**: `jdbc:h2:mem:exam_system`
   - **Username**: `admin`
   - **Password**: `123456`
3. Click "Connect" để vào H2 Console

#### **H2 Database Properties**
```properties
# Database Configuration
spring.datasource.url=jdbc:h2:mem:exam_system
spring.datasource.username=admin
spring.datasource.password=123456
spring.datasource.driver-class-name=org.h2.Driver

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA Configuration
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
```

### **Swagger API Documentation**

#### **Truy Cập Swagger UI**
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs (JSON)**: http://localhost:8080/v3/api-docs
- **API Docs (YAML)**: http://localhost:8080/v3/api-docs.yaml

#### **Sử Dụng Swagger UI**
1. **Mở Swagger UI**: Truy cập http://localhost:8080/swagger-ui.html
2. **Xem API Info**: Thông tin tổng quan về API ở đầu trang
3. **Chọn Endpoint**: Click vào endpoint cần test (ví dụ: `/api/exams`)
4. **Click "Try it out"**: Để kích hoạt chế độ test
5. **Nhập Parameters**: Điền các tham số cần thiết
6. **Nhập Request Body**: Nếu là POST/PUT request
7. **Click "Execute"**: Để thực thi API call
8. **Xem Response**: Kết quả trả về với status code và response body

#### **Swagger Features**
- ✅ **Interactive Documentation**: Test API trực tiếp từ browser
- ✅ **Request/Response Examples**: Ví dụ sử dụng API
- ✅ **Schema Validation**: Kiểm tra format dữ liệu
- ✅ **Authentication Support**: Hỗ trợ JWT, Basic Auth
- ✅ **Multiple Servers**: Development và Production environments
- ✅ **API Versioning**: Quản lý phiên bản API

#### **Swagger Configuration**
```properties
# Swagger Configuration
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method

# Additional Swagger Options
springdoc.swagger-ui.tags-sorter=alpha
springdoc.swagger-ui.doc-expansion=none
springdoc.swagger-ui.disable-swagger-default-url=true
```

#### **Swagger Configuration Class**
```java
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hệ Thống Thi Trực Tuyến API")
                        .description("REST API cho hệ thống quản lý bài thi")
                        .version("1.0.0"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Development"),
                        new Server().url("https://api.examsystem.com").description("Production")
                ));
    }
}
```

## 🧪 Testing

### **Run Tests**
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ExamServiceTest

# Run with coverage
mvn test jacoco:report
```

### **Test Structure**
- **Unit Tests**: Test individual components
- **Integration Tests**: Test component interactions
- **Security Tests**: Test authentication/authorization

### **Test với Swagger UI**
1. **Truy cập Swagger**: http://localhost:8080/swagger-ui.html
2. **Chọn API endpoint**: Ví dụ `/api/exams` để test quản lý bài thi
3. **Click "Try it out"**: Kích hoạt chế độ test
4. **Nhập dữ liệu**: Parameters và request body (nếu cần)
5. **Click "Execute"**: Thực thi API call
6. **Xem kết quả**: Response với status code, headers và body

#### **Ví dụ Test API với Swagger**

**Test GET /api/exams:**
- Click vào endpoint `GET /api/exams`
- Click "Try it out"
- Click "Execute"
- Xem danh sách bài thi trả về

**Test POST /api/exams:**
- Click vào endpoint `POST /api/exams`
- Click "Try it out"
- Nhập request body:
```json
{
  "title": "Bài thi Java Core",
  "description": "Kiểm tra kiến thức Java cơ bản",
  "timeLimit": 60
}
```
- Click "Execute"
- Xem bài thi mới được tạo

**Test GET /api/exams/{id}:**
- Click vào endpoint `GET /api/exams/{id}`
- Click "Try it out"
- Nhập `id` = 1
- Click "Execute"
- Xem thông tin bài thi có ID = 1

### **Test với Postman**
1. **Import collection**: Từ file `postman_collection.json` (nếu có)
2. **Tạo request mới**: Với base URL: `http://localhost:8080/api`
3. **Test các endpoints**:
   - `GET /api/exams` - Lấy danh sách bài thi
   - `POST /api/exams` - Tạo bài thi mới
   - `GET /api/users` - Lấy danh sách người dùng
   - `POST /api/questions` - Tạo câu hỏi mới

### **Test với cURL**
```bash
# Lấy danh sách bài thi
curl -X GET "http://localhost:8080/api/exams" -H "accept: application/json"

# Tạo bài thi mới
curl -X POST "http://localhost:8080/api/exams" \
  -H "Content-Type: application/json" \
  -d '{"title":"Bài thi mới","description":"Mô tả bài thi","timeLimit":45}'

# Lấy bài thi theo ID
curl -X GET "http://localhost:8080/api/exams/1" -H "accept: application/json"
```

## 🔧 Configuration

### **Development Environment**
- H2 in-memory database
- Debug logging enabled
- H2 console accessible
- CSRF disabled for testing
- Auto schema creation

### **H2 Database Features**
- **In-Memory Storage**: Dữ liệu lưu trong RAM
- **Auto Schema**: Tự động tạo tables
- **Web Console**: Giao diện quản lý dữ liệu
- **Fast Performance**: Tốc độ truy xuất nhanh

## 📊 Monitoring và Logging

### **Logging Levels**
- **DEBUG**: Development debugging
- **INFO**: General information
- **WARN**: Warning messages
- **ERROR**: Error messages

### **H2 Console Monitoring**
- Xem cấu trúc database
- Xem dữ liệu trong tables
- Thực thi SQL queries
- Export/Import dữ liệu

## 🔒 Security Features

### **Authentication & Authorization**
- Spring Security integration
- Password encryption (BCrypt)
- Role-based access control
- JWT token support (có thể mở rộng)

### **Data Protection**
- Input validation
- SQL injection prevention
- XSS protection
- CSRF protection (disabled for development)

## 🚀 Deployment

### **Build JAR File**
```bash
mvn clean package
```

### **Run JAR File**
```bash
java -jar target/exam-0.0.1-SNAPSHOT.jar
```

### **Production Considerations**
- **Database**: Chuyển sang MySQL/PostgreSQL
- **Security**: Enable CSRF protection
- **Logging**: Production logging levels
- **Monitoring**: Health checks và metrics

## 📈 Performance Optimization

### **H2 Database Optimization**
- In-memory storage cho development
- Connection pooling
- Query optimization
- Indexing strategies

### **Application Optimization**
- Lazy loading
- Pagination
- Response compression
- Async processing

## 🐛 Troubleshooting

### **Common Issues**

#### **1. Port 8080 already in use**
```bash
# Thay đổi port trong application.properties
server.port=8081
```

#### **2. H2 Console không truy cập được**
- Kiểm tra `spring.h2.console.enabled=true`
- Kiểm tra `spring.h2.console.path=/h2-console`
- Đảm bảo Spring Security cho phép truy cập

#### **3. Database connection failed**
- Kiểm tra H2 dependency trong pom.xml
- Kiểm tra JDBC URL trong application.properties
- Restart ứng dụng

#### **4. Build failures**
- Đảm bảo Java 21+
- Đảm bảo Maven 3.6+
- Clean và rebuild project

#### **5. Swagger UI không hiển thị**
- Kiểm tra dependency `springdoc-openapi-starter-webmvc-ui` trong pom.xml
- Đảm bảo `SwaggerConfig.java` được tạo đúng
- Restart ứng dụng sau khi thêm dependency
- Kiểm tra console logs để xem lỗi

#### **6. Swagger UI hiển thị nhưng không có endpoints**
- Kiểm tra controllers có annotation `@RestController`
- Đảm bảo package controllers được scan
- Kiểm tra `@RequestMapping` annotations
- Xem logs để tìm lỗi controller loading

### **Debug Mode**
```bash
# Enable debug logging
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dlogging.level.com.example.exam=DEBUG"

# Hoặc thay đổi trong application.properties
logging.level.com.example.exam=DEBUG
```

### **H2 Database Debug**
```properties
# Hiển thị SQL queries
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Hiển thị database operations
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

## 📚 Tài Liệu Tham Khảo

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Spring Security Reference](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)
- [H2 Database Documentation](http://www.h2database.com/html/main.html)
- [SpringDoc OpenAPI](https://springdoc.org/)

## 📖 Hướng Dẫn Chi Tiết

- **README.md** - Tài liệu tổng quan dự án
- **SWAGGER_GUIDE.md** - Hướng dẫn sử dụng Swagger UI chi tiết
- **SWAGGER_TROUBLESHOOTING.md** - Hướng dẫn khắc phục lỗi Swagger

## 🤝 Đóng Góp

1. Fork repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

## 📄 License

Dự án này được phân phối dưới giấy phép MIT. Xem file `LICENSE` để biết thêm chi tiết.

## 📞 Liên Hệ

- **Developer**: [Your Name]
- **Email**: [your.email@example.com]
- **Project Link**: [https://github.com/username/exam](https://github.com/username/exam)

---

## 🎯 **Lưu Ý Quan Trọng**

**Dự án này được xây dựng theo Spring Boot REST API, KHÔNG phải Spring MVC truyền thống.**

### **Đặc điểm của REST API:**
- ✅ **Controllers sử dụng `@RestController`** - Không trả về view
- ✅ **Responses là JSON** - Không có HTML templates
- ✅ **Stateless** - Không lưu session state
- ✅ **Frontend riêng biệt** - Có thể là React, Vue, Angular hoặc mobile app

### **H2 Database:**
- ✅ **In-Memory Database** - Dữ liệu lưu trong RAM
- ✅ **Auto Schema Creation** - Tự động tạo tables
- ✅ **H2 Console** - Giao diện web để quản lý dữ liệu
- ✅ **Development Friendly** - Không cần cài đặt database riêng

### **Swagger Documentation:**
- ✅ **API Documentation** - Tài liệu API tự động
- ✅ **Interactive Testing** - Test API trực tiếp từ browser
- ✅ **Request/Response Examples** - Ví dụ sử dụng API 