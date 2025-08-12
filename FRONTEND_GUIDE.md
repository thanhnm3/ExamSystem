# Hướng dẫn sử dụng Frontend với Thymeleaf

## **Tổng quan**

Dự án này sử dụng **Spring Boot + Thymeleaf + Bootstrap** để tạo giao diện web đơn giản và đẹp mắt. Frontend được tích hợp trực tiếp vào Spring Boot application, không cần deploy riêng biệt.

## **Cấu trúc thư mục**

```
src/main/resources/
├── templates/                    # FRONTEND - HTML Templates
│   └── frontend/
│       ├── layout.html          # Layout chung cho tất cả trang
│       ├── home.html            # Trang Dashboard
│       ├── exam-management.html # Quản lý Exam
│       ├── exam-create.html     # Tạo Exam mới
│       ├── question-management.html # Quản lý Question
│       └── user-management.html # Quản lý User
└── static/                      # FRONTEND - CSS, JS, Images
    ├── css/
    ├── js/
    └── images/

src/main/java/com/example/exam/
├── web/                         # FRONTEND - Web Controllers
│   └── WebController.java       # Xử lý các trang web
├── api/                         # BACKEND - API Controllers  
│   ├── ExamController.java      # REST API cho Exam
│   ├── QuestionController.java  # REST API cho Question
│   └── UserController.java      # REST API cho User
└── service/                     # BACKEND - Business Logic
```

## **Phân biệt Backend và Frontend**

### **🔴 BACKEND (Java)**
- **Package**: `com.example.exam.api.*` - REST API Controllers
- **Package**: `com.example.exam.service.*` - Business Logic
- **Package**: `com.example.exam.repository.*` - Data Access
- **Package**: `com.example.exam.entity.*` - Database Entities
- **Package**: `com.example.exam.dto.*` - Data Transfer Objects
- **Responsibility**: Xử lý logic nghiệp vụ, database, API endpoints

### **🟢 FRONTEND (Thymeleaf + Bootstrap)**
- **Package**: `com.example.exam.web.*` - Web Controllers (trả về HTML)
- **Directory**: `src/main/resources/templates/frontend/` - HTML Templates
- **Directory**: `src/main/resources/static/` - CSS, JS, Images
- **Responsibility**: Hiển thị giao diện người dùng, form, navigation

## **Cách hoạt động**

1. **WebController** (`/web/*`) → Trả về HTML templates
2. **APIController** (`/api/*`) → Trả về JSON data
3. **Frontend** gọi **Backend API** qua JavaScript fetch()
4. **Thymeleaf** render HTML với data từ Controller

## **URL Mapping**

### **Frontend URLs (Web Pages)**
- `/web/` - Dashboard
- `/web/exams` - Quản lý Exam
- `/web/exams/create` - Tạo Exam mới
- `/web/questions` - Quản lý Question
- `/web/users` - Quản lý User

### **Backend URLs (REST API)**
- `/api/exams` - CRUD operations cho Exam
- `/api/questions` - CRUD operations cho Question
- `/api/users` - CRUD operations cho User
- `/swagger-ui/index.html` - API Documentation

## **Tính năng Frontend**

### **1. Dashboard**
- Thống kê tổng quan (số exam, question, user)
- Quick actions buttons
- Thông tin hệ thống

### **2. Quản lý Exam**
- Danh sách exam với search/filter
- CRUD operations (Create, Read, Update, Delete)
- Pagination
- Responsive table

### **3. Tạo Exam mới**
- Form validation
- Real-time preview
- Quick settings
- Responsive design

### **4. Quản lý Question**
- Modal form để thêm question
- Dynamic loading của exam list
- Form validation
- CRUD operations

### **5. Quản lý User**
- Modal form để thêm user
- Avatar circles
- Role management
- Status management

## **Công nghệ sử dụng**

### **Backend**
- **Spring Boot 3.5.4** - Framework chính
- **Spring Security** - Authentication & Authorization
- **Spring Data JPA** - Database access
- **H2 Database** - In-memory database
- **Lombok** - Reduce boilerplate code

### **Frontend**
- **Thymeleaf** - Template engine
- **Bootstrap 5.3.0** - CSS framework
- **Bootstrap Icons** - Icon library
- **Vanilla JavaScript** - Client-side logic
- **Fetch API** - HTTP requests

## **Cách chạy**

1. **Clone project**
```bash
git clone <repository-url>
cd exam
```

2. **Chạy Spring Boot application**
```bash
./mvnw spring-boot:run
```

3. **Truy cập frontend**
- Dashboard: http://localhost:8080/web/
- Exam Management: http://localhost:8080/web/exams
- Question Management: http://localhost:8080/web/questions
- User Management: http://localhost:8080/web/users

4. **Truy cập API Documentation**
- Swagger UI: http://localhost:8080/swagger-ui/index.html

## **Ưu điểm của approach này**

### **✅ Pros**
- **Phát triển nhanh**: Không cần học framework mới
- **Deploy đơn giản**: Chỉ cần 1 JAR file
- **Tích hợp tốt**: Frontend và Backend cùng 1 project
- **UI đẹp**: Bootstrap cung cấp components đẹp
- **Responsive**: Hoạt động tốt trên mobile
- **SEO friendly**: Server-side rendering

### **❌ Cons**
- **Không phải SPA**: Page refresh khi navigate
- **Limited interactivity**: Ít dynamic features hơn React/Vue
- **Tight coupling**: Frontend và Backend cùng codebase

## **Khi nào nên sử dụng**

### **✅ Phù hợp cho**
- **MVP/Prototype** - Phát triển nhanh
- **Admin panels** - Giao diện quản trị
- **Internal tools** - Công cụ nội bộ
- **Simple CRUD apps** - Ứng dụng CRUD đơn giản
- **Learning projects** - Dự án học tập

### **❌ Không phù hợp cho**
- **Complex SPAs** - Ứng dụng phức tạp
- **Real-time apps** - Ứng dụng real-time
- **Mobile apps** - Ứng dụng mobile
- **High-traffic sites** - Website có traffic cao

## **Mở rộng trong tương lai**

Nếu muốn upgrade lên SPA, bạn có thể:

1. **Giữ nguyên Backend** (Spring Boot + REST API)
2. **Thay thế Frontend** bằng React/Vue/Angular
3. **Deploy riêng biệt** Frontend và Backend
4. **Sử dụng CORS** để kết nối

## **Kết luận**

Với **Spring Boot + Thymeleaf + Bootstrap**, bạn có thể:
- Phát triển frontend **nhanh chóng** và **đơn giản**
- Có giao diện **đẹp** và **responsive**
- **Deploy dễ dàng** với 1 JAR file
- **Phân biệt rõ ràng** Backend và Frontend code
- **Học tập** và **hiểu rõ** cả 2 phía

Đây là giải pháp **tối ưu** cho dự án exam system của bạn!
