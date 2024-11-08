package com.studycrew.studycrew_backend.domain.profile.user;

import java.util.Set;

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

	@ElementCollection
	private Set<String> skills;

	@OneToMany(mappedBy = "user")
	private List<Team> teamsOwned;

	@ManyToMany
	private List<Team> teamsJoined;

	@Builder(access = AccessLevel.PRIVATE)
	public User(Long id, String email, String username, String info, Set<String> skills) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.info = info;
		this.skills = skills;
	}
}
