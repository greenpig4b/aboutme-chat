package com.example.aboutme.review;

import com.example.aboutme.user.UserResponseDTO.ExpertMainDTO.RecentReviewRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
//    전문가한테 속한 리뷰 리스트
    @Query("SELECT r FROM Review r WHERE r.counsel.expert.id = :expertId")
    List<Review> findByExpertId(@Param("expertId") Integer expertId);

    @Query("""
            SELECT new com.example.aboutme.user.UserResponseDTO.ExpertMainDTO.RecentReviewRecord(r.id, u.name, r.score, r.content) 
            FROM Review r  
            JOIN r.user u  
            WHERE r.counsel.expert.id = :expertId
            """)
    List<RecentReviewRecord> findReviewRecordsByExpertId(@Param("expertId") Integer expertId);


}
