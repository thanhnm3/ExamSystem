# 🚀 Hướng Dẫn Sử Dụng Swagger UI

## 📋 Tổng Quan

Swagger UI là giao diện web để test và tài liệu hóa REST API. Dự án này sử dụng **SpringDoc OpenAPI** để tạo Swagger UI.

## 🔧 Cài Đặt

### **1. Dependency đã được thêm vào pom.xml:**
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```

### **2. Configuration Class:**
- `SwaggerConfig.java` - Cấu hình thông tin API
- `application.properties` - Cấu hình Swagger paths

## 🌐 Truy Cập Swagger UI

### **URLs:**
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs (JSON)**: http://localhost:8080/v3/api-docs
- **API Docs (YAML)**: http://localhost:8080/v3/api-docs.yaml

### **H2 Console:**
- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:exam_system`
- **Username**: `admin`
- **Password**: `123456`

## 🧪 Cách Test API với Swagger UI

### **Bước 1: Mở Swagger UI**
1. Khởi động ứng dụng: `mvn spring-boot:run`
2. Mở browser: http://localhost:8080/swagger-ui.html

### **Bước 2: Xem API Info**
- Thông tin tổng quan về API ở đầu trang
- Version, mô tả, contact info
- Server environments (Development/Production)

### **Bước 3: Test API Endpoints**

#### **Test GET /api/exams (Lấy danh sách bài thi)**
1. Tìm endpoint `GET /api/exams`
2. Click vào endpoint để mở rộng
3. Click "Try it out"
4. Click "Execute"
5. Xem response:
   ```json
   [
     {
       "id": 1,
       "title": "Bài thi Java Core",
       "description": "Kiểm tra kiến thức Java cơ bản",
       "timeLimit": 60,
       "createdAt": "2024-01-01T10:00:00",
       "isActive": true
     }
   ]
   ```

#### **Test POST /api/exams (Tạo bài thi mới)**
1. Tìm endpoint `POST /api/exams`
2. Click "Try it out"
3. Nhập request body:
   ```json
   {
     "title": "Bài thi Spring Boot",
     "description": "Kiểm tra kiến thức Spring Boot",
     "timeLimit": 90
   }
   ```
4. Click "Execute"
5. Xem response với bài thi mới được tạo

#### **Test GET /api/exams/{id} (Lấy bài thi theo ID)**
1. Tìm endpoint `GET /api/exams/{id}`
2. Click "Try it out"
3. Nhập `id` = 1
4. Click "Execute"
5. Xem thông tin bài thi có ID = 1

#### **Test POST /api/exams/generate-random (Tạo bài thi ngẫu nhiên)**
1. Tìm endpoint `POST /api/exams/generate-random`
2. Click "Try it out"
3. Nhập parameters:
   - `examId`: 1
   - `questionCount`: 5
4. Click "Execute"
5. Xem danh sách bài thi ngẫu nhiên

## 📊 Các API Endpoints Có Sẵn

### **Exam Management**
- `GET /api/exams` - Lấy danh sách bài thi
- `GET /api/exams/{id}` - Lấy bài thi theo ID
- `POST /api/exams` - Tạo bài thi mới
- `POST /api/exams/generate-random` - Tạo bài thi ngẫu nhiên

### **Question Management**
- `GET /api/questions` - Lấy danh sách câu hỏi
- `GET /api/questions/{id}` - Lấy câu hỏi theo ID
- `POST /api/questions` - Tạo câu hỏi mới
- `PUT /api/questions/{id}` - Cập nhật câu hỏi
- `DELETE /api/questions/{id}` - Xóa câu hỏi

### **Answer Management**
- `GET /api/answers` - Lấy danh sách đáp án
- `GET /api/answers/{id}` - Lấy đáp án theo ID
- `POST /api/answers` - Tạo đáp án mới
- `PUT /api/answers/{id}` - Cập nhật đáp án
- `DELETE /api/answers/{id}` - Xóa đáp án

### **User Management**
- `GET /api/users` - Lấy danh sách người dùng
- `GET /api/users/{id}` - Lấy người dùng theo ID
- `POST /api/users` - Tạo người dùng mới
- `PUT /api/users/{id}` - Cập nhật người dùng
- `DELETE /api/users/{id}` - Xóa người dùng

## 🔍 Swagger UI Features

### **Interactive Documentation**
- ✅ Test API trực tiếp từ browser
- ✅ Không cần Postman hoặc cURL
- ✅ Request/Response examples
- ✅ Schema validation

### **API Information**
- ✅ Endpoint descriptions
- ✅ Parameter details
- ✅ Response schemas
- ✅ Error codes

### **Testing Capabilities**
- ✅ Try it out functionality
- ✅ Request body editor
- ✅ Parameter input forms
- ✅ Response viewer

## 🐛 Troubleshooting

### **Swagger UI không hiển thị**
1. Kiểm tra dependency trong `pom.xml`
2. Đảm bảo `SwaggerConfig.java` được tạo
3. Restart ứng dụng
4. Kiểm tra console logs

### **Không có API endpoints**
1. Kiểm tra controllers có `@RestController`
2. Đảm bảo package được scan
3. Kiểm tra `@RequestMapping` annotations
4. Xem logs để tìm lỗi

### **Lỗi khi test API**
1. Kiểm tra ứng dụng đang chạy
2. Kiểm tra database connection
3. Xem console logs
4. Kiểm tra request format

## 📚 Tài Liệu Tham Khảo

- [SpringDoc OpenAPI](https://springdoc.org/)
- [OpenAPI Specification](https://swagger.io/specification/)
- [Swagger UI](https://swagger.io/tools/swagger-ui/)

## 🎯 Tips Sử Dụng

1. **Sử dụng Tags**: Các API được nhóm theo chức năng
2. **Xem Schema**: Click vào model để xem cấu trúc dữ liệu
3. **Test từng bước**: Test các API cơ bản trước
4. **Kiểm tra Response**: Luôn xem status code và response body
5. **Sử dụng Examples**: Copy-paste examples có sẵn

---

**Lưu ý**: Swagger UI chỉ hoạt động khi ứng dụng đang chạy. Đảm bảo chạy `mvn spring-boot:run` trước khi truy cập.
