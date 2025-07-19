package com.example.barter.repository;

import com.example.barter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.status = 'ACTIVE' AND u.id != :userId")
    List<User> findActiveUsersExcept(@Param("userId") Long userId);
    
    @Query("SELECT u FROM User u WHERE u.location LIKE %:location% AND u.status = 'ACTIVE' AND u.id != :userId")
    List<User> findActiveUsersByLocation(@Param("userId") Long userId, @Param("location") String location);
    
    @Query("SELECT u FROM User u WHERE u.status = 'ACTIVE' ORDER BY u.rating DESC, u.totalTrades DESC")
    List<User> findTopRatedUsers();
}