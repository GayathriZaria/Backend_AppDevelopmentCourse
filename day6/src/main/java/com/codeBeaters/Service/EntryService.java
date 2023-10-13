package com.codeBeaters.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codeBeaters.DTO.AuthenticationRequest;
import com.codeBeaters.DTO.AuthenticationResponse;
import com.codeBeaters.DTO.RegisterRequest;
import com.codeBeaters.Modal.User;
import com.codeBeaters.Modal.Enumerate.Role;
import com.codeBeaters.Repository.UserRepository;
import com.codeBeaters.Util.JwtService;

@Service
public class EntryService {

	@Autowired
	UserRepository uRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationResponse validateUser(AuthenticationRequest request) {

		try {
		
			authenticationManager.authenticate(

					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

			var user = uRepo.findByEmail(request.getEmail()).orElseThrow();

			var jwtToken = jwtService.generateToken(user);

			return AuthenticationResponse.builder().token(jwtToken).build();
		}
		catch(Exception e) {
			

			return AuthenticationResponse.builder().token(null).build();
		}
		

	}

	public AuthenticationResponse CreateNewUser(RegisterRequest request) {

		var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER)
				.build();
		
		
			
			
			
					
			
			List<Integer> emailCountList = uRepo.isEmailExist(user.getEmail());
			
			if(emailCountList.get(0) !=  0) {
				return null;
			}
			
			
			else {
				
				uRepo.save(user);
				
				var jwtToken = jwtService.generateToken(user);
				
				return AuthenticationResponse.builder()
						.token(jwtToken)
						.build();
			}
		
		
	}
}
