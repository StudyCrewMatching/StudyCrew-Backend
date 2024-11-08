package com.studycrew.studycrew_backend.domain.profile.user.dto;

import java.util.List;

import com.studycrew.studycrew_backend.domain.profile.user.User;
import com.studycrew.studycrew_backend.domain.tag.SkillSet;

import lombok.AccessLevel;
import lombok.Builder;

public class UserResponseDto {

	private String email;

	private String username;

	private String info;

	private List<SkillSet> skills;

	@Builder(access = AccessLevel.PRIVATE)
	private UserResponseDto(String email, String username, String info, List<SkillSet> skills) {
		this.email = email;
		this.username = username;
		this.info = info;
		this.skills = skills;
	}

	public static UserResponseDto of(User user) {
		return UserResponseDto.builder()
			.email(user.getEmail())
			.username(user.getUsername())
			.info(user.getInfo())
			.skills(user.getSkills())
			.build();
	}
}
