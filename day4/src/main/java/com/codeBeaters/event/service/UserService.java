package com.codeBeaters.event.service;

import java.util.List;

import com.codeBeaters.event.dto.request.UserRequest;
import com.codeBeaters.event.dto.response.UserResponse;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUser(Long uid);

    UserResponse updateUser(UserRequest request, Long uid);

    boolean deleteDoctor(Long uid);

	

}
