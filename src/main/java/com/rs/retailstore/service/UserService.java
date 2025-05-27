package com.rs.retailstore.service;

import com.rs.retailstore.dto.RegisterRequest;
import com.rs.retailstore.entity.UserProfile;
import com.rs.retailstore.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(RegisterRequest req) {
        try {
            // Kiểm tra xem username đã tồn tại chưa
            if (!profileRepository.findByUsername(req.getUsername()).isEmpty()) {
                throw new RuntimeException("Username already exists");
            }
            
            // Tạo thông tin user trong một bảng duy nhất
            UserProfile profile = new UserProfile();
            profile.setUsername(req.getUsername());
            profile.setPassword(passwordEncoder.encode(req.getPassword())); // Mã hóa password
            
            // Use the role from request if provided, otherwise set default role
            if (req.getRole() != null && !req.getRole().isEmpty()) {
                profile.setRole(req.getRole().toUpperCase()); // Convert to uppercase for consistency
            } else {
                profile.setRole("USER");
            }
            
            profile.setFullname(req.getFullname());
            profile.setEmail(req.getEmail());
            profile.setPhone(req.getPhone());

            profileRepository.save(profile);
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            throw e; // Rethrow to let Spring handle it
        }
    }
}
