package com.example.exam.service;

import com.example.exam.dto.UserDto;
import com.example.exam.entity.User;
import com.example.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserDto registerUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }
        
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(User.UserRole.USER);
        
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }
    
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user với id: " + id));
        return convertToDto(user);
    }
    
    public List<UserDto> getUsersSortedByScore() {
        // Sắp xếp người dùng theo điểm giảm dần
        return userRepository.findAll().stream()
                .sorted((u1, u2) -> {
                    double score1 = u1.getResults() != null ? 
                        u1.getResults().stream().mapToDouble(r -> r.getScore()).average().orElse(0.0) : 0.0;
                    double score2 = u2.getResults() != null ? 
                        u2.getResults().stream().mapToDouble(r -> r.getScore()).average().orElse(0.0) : 0.0;
                    return Double.compare(score2, score1);
                })
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());
        return dto;
    }
} 