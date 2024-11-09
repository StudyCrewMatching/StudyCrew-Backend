package com.studycrew.studycrew_backend.domain.profile.user.dto;

import java.util.List;

import com.studycrew.studycrew_backend.domain.tag.skill.SkillType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public class UserRegistrationDto {

	private String email;

	private String username;

	private String info;

	@Enumerated(EnumType.STRING)
	private List<SkillType> skills;
}
