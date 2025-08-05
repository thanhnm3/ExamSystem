package com.example.exam.repository;

import com.example.exam.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    
    List<Result> findByUserId(Long userId);
    
    List<Result> findByExamId(Long examId);
    
    @Query("SELECT r FROM Result r ORDER BY r.score DESC")
    List<Result> findAllOrderByScoreDesc();
    
    @Query("SELECT r FROM Result r ORDER BY r.score DESC LIMIT 5")
    List<Result> findTop5ByScoreDesc();
} 