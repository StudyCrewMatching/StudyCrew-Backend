package com.studycrew.studycrew_backend.api.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studycrew.studycrew_backend.domain.profile.user.dto.UserRegistrationDto;
import com.studycrew.studycrew_backend.domain.profile.user.dto.UserResponseDto;
import com.studycrew.studycrew_backend.domain.profile.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@Tag(name = "User Controller", description = "유저 생성, 삭제 및 조회 API 제공")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@Operation(summary = "사용자 생성", description = "사용자를 등록합니다")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "사용자 생성 성공", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
		@ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
		@ApiResponse(responseCode = "500", description = "서버 오류 발생", content = @Content(schema = @Schema(implementation = Exception.class)))
	})
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserRegistrationDto registrationDto) {
		UserResponseDto userResponseDto = userService.register(registrationDto);
		return ResponseEntity.ok(userResponseDto);
	}

	@Operation(summary = "사용자 삭제", description = "팀에 가입되어있지 않은 경우 사용자를 삭제합니다")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "사용자 삭제 성공", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
		@ApiResponse(responseCode = "400", description = "존재하지 않는 사용자이거나 팀에 가입되어 있는 경우 사용자 삭제 불가", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
		@ApiResponse(responseCode = "500", description = "서버 오류 발생", content = @Content(schema = @Schema(implementation = Exception.class)))
	})
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> delete(@PathVariable @Positive(message = "유저 아이디는 1 이상이어야 합니다.") Long userId) {
		userService.delete(userId);
		return ResponseEntity.ok().body("탈퇴 완료 되었습니다.");
	}
}
