package com.proj.book_reservation.repositories;

import com.proj.book_reservation.dtos.user.UserBasicProjection;
import com.proj.book_reservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
//    Optional<User> findByUsername(String username);
    @Query(value = """
        SELECT u.user_id as userId, u.first_name as firstName, u.last_name as lastName,
               u.user_name as username, u.phone as phone, u.role as role, u.created_at as createdAt
        FROM user u
        WHERE u.user_id = :userId
    """, nativeQuery = true)
    UserBasicProjection getUserBasic(@Param("userId") Long userId);

    Optional<User> findByEmail(String email);
}
