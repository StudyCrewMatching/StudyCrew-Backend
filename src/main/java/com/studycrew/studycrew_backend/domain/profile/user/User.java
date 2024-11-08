package com.studycrew.studycrew_backend.domain.profile.user;

import java.util.List;
import java.util.Set;

import com.studycrew.studycrew_backend.domain.profile.user.dto.UserRegistrationDto;
import com.studycrew.studycrew_backend.domain.tag.SkillSet;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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

	private List<SkillSet> skills;

	// @OneToMany(mappedBy = "user")
	// private List<Team> teamsOwned;
	//
	// @ManyToMany
	// private List<Team> teamsJoined;

	@Builder(access = AccessLevel.PRIVATE)
	private User(Long id, String email, String username, String info, List<SkillSet> skills) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.info = info;
		this.skills = skills;
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
