package com.example.event.service;

import java.util.List;

import com.example.event.dto.request.UserRequest;
import com.example.event.dto.response.UserResponse;
import com.example.event.model.UserModel;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUser(Long uid);

    UserResponse updateUser(UserRequest request, Long uid);

    

	

	

	boolean deleteProduct(Long uid);



}