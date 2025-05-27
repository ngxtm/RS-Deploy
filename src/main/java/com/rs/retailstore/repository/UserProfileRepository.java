package com.rs.retailstore.repository;

import com.rs.retailstore.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    List<UserProfile> findByUsername(String username);

}
