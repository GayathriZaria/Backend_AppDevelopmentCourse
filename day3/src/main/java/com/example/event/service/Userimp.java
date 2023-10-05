package com.example.event.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.event.dto.request.UserRequest;
import com.example.event.dto.response.UserResponse;
import com.example.event.model.UserModel;
import com.example.event.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Service
@Transactional
@RequiredArgsConstructor
public class Userimp implements UserService{

	@Autowired
	private UserRepository userRepository;


	@Override
	public List<UserResponse> getAllUsers() {
		// TODO Auto-generated method stub
		List<UserModel> userList = userRepository.findAll();
        return userList.stream()
               
                .map(this::mapUserModelToUserResponse)
                .collect(Collectors.toList());
	}

	@Override
	public UserResponse getUser(Long uid) {
		// TODO Auto-generated method stub
		UserModel user = userRepository.findByUid(uid);
        return mapUserModelToUserResponse(user);
	}

	@Override
	public UserResponse updateUser(UserRequest request, Long uid) {
		// TODO Auto-generated method stub
		UserModel user = userRepository.findByUid(uid);
        UserModel newUser = new UserModel();
        if (user != null) {
            newUser = UserModel
            		.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .isEnabled(request.getIsEnabled())
                    .build();
            userRepository.save(newUser);
        }
        return mapUserModelToUserResponse(newUser);
	}

	
	private UserResponse mapUserModelToUserResponse(UserModel newUser) {
		// TODO Auto-generated method stub
		return UserResponse.builder()
                .uid(newUser.getUid())
                .name(newUser.getUsername())
                .email(newUser.getEmail())
                .isEnabled(newUser.isEnabled())
                .build();
	}

	
	
	@Override
    public boolean deleteProduct(Long uid) {
        UserModel user = userRepository.findByUid(uid);

        if (user != null) {
            
            userRepository.deleteByUid(uid);
            return true;
        } else {
            return false;
        }
    }

	

}
