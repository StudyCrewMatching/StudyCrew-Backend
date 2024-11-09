package com.studycrew.studycrew_backend.api.user;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studycrew.studycrew_backend.domain.profile.user.dto.UserRegistrationDto;
import com.studycrew.studycrew_backend.domain.profile.user.dto.UserResponseDto;
import com.studycrew.studycrew_backend.domain.profile.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserRegistrationDto registrationDto) {
		UserResponseDto userResponseDto = userService.register(registrationDto);
		return ResponseEntity.ok(userResponseDto);
	}

	@PostMapping("/delete/{userId}")
	public ResponseEntity<?> delete(@PathVariable Long userId) {
		userService.delete(userId);
		return ResponseEntity.ok().build();
	}
}
