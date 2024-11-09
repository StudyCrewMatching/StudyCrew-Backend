package com.studycrew.studycrew_backend.domain.profile.user;

import java.util.ArrayList;
import java.util.List;

import com.studycrew.studycrew_backend.domain.profile.user.dto.UserRegistrationDto;
import com.studycrew.studycrew_backend.domain.tag.skill.SkillType;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String username;

	private String info;

	@ElementCollection(targetClass = SkillType.class)
	@Enumerated(EnumType.STRING)
	private List<SkillType> skills = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "teamId")
	private Team team;

	@Builder(access = AccessLevel.PRIVATE)
	private User(Long id, String email, String username, String info, List<SkillType> skills, Team team) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.info = info;
		this.skills = skills;
		this.team = team;
	}

	public static User of(UserRegistrationDto userRegistrationDto) {
		return User.builder()
			.email(userRegistrationDto.getEmail())
			.username(userRegistrationDto.getUsername())
			.info(userRegistrationDto.getInfo())
			.skills(userRegistrationDto.getSkills())
			.build();
	}
}
