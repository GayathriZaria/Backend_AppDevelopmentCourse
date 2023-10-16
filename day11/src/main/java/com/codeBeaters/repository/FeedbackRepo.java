package com.codeBeaters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeBeaters.entity.Feedback;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer>{

}
