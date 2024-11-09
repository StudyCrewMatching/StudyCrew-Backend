package com.studycrew.studycrew_backend.domain.profile.user.dto;

import java.util.List;

import com.studycrew.studycrew_backend.domain.profile.user.User;
import com.studycrew.studycrew_backend.domain.tag.skill.SkillType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {

	private String email;

	private String username;

	private String info;

	@Enumerated(EnumType.STRING)
	private List<SkillType> skills;

	@Builder(access = AccessLevel.PRIVATE)
	private UserResponseDto(String email, String username, String info, List<SkillType> skills) {
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
