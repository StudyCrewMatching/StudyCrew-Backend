package com.studycrew.studycrew_backend.domain.profile.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studycrew.studycrew_backend.domain.profile.user.User;
import com.studycrew.studycrew_backend.domain.profile.user.dto.UserRegistrationDto;
import com.studycrew.studycrew_backend.domain.profile.user.dto.UserResponseDto;
import com.studycrew.studycrew_backend.domain.profile.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public UserResponseDto register(UserRegistrationDto registrationDto) {
		User user = User.of(registrationDto);
		userRepository.save(user);
		return UserResponseDto.of(user);
	}

	@Transactional
	public void delete(Long userId) {
		userRepository.deleteById(userId);
	}
}
