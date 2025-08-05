package com.example.exam.repository;

import com.example.exam.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    
    List<Exam> findByIsActiveTrue();
    
    List<Exam> findByIsActive(Boolean isActive);
} 