package com.studycrew.studycrew_backend.domain.profile.user.dto;

import java.util.List;

import com.studycrew.studycrew_backend.domain.tag.skill.SkillType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserRegistrationDto {

	@NotBlank(message = "이메일 입력은 필수입니다.")
	@Email(message = "올바른 이메일 형식으로 작성")
	@Schema(description = "등록할 이메일 주소", defaultValue = "user@test.com")
	private String email;

	@NotBlank(message = "사용자 이름은 필수입니다.")
	@Schema(description = "사용자 이름", defaultValue = "김철수")
	private String username;

	@Schema(description = "자기 소개")
	private String info;

	@Schema(description = "사용하는 기술 스택 (Java, Kotlin, JavaScript...)",
		example = "[\"JAVA\", \"REACT\", \"DOCKER\"]",
		type = "array")
	private List<SkillType> skills;
}
