package com.studycrew.studycrew_backend.domain.profile.user.dto;

import java.util.List;
import java.util.Set;

import org.springframework.validation.annotation.Validated;

import com.studycrew.studycrew_backend.domain.tag.SkillSet;

import lombok.Getter;

@Getter
public class UserRegistrationDto {

	private String email;

	private String username;

	private String info;

	private List<SkillSet> skills;
}
