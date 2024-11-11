package com.studycrew.studycrew_backend.domain.profile.team.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TeamLeaderChangeRequestDto {

    @NotNull(message = "기존 팀 리더 아이디는 필수입니다.")
    @Min(value = 1, message = "사용자 아이디는 1 이상이어야 합니다.")
    @Schema(description = "현재 팀 리더 ID", defaultValue = "1")
    private Long nowLeaderId;

    @NotNull(message = "새로운 팀 리더 아이디는 필수입니다.")
    @Min(value = 1, message = "사용자 아이디는 1 이상이어야 합니다.")
    @Schema(description = "새로운 팀 리더 ID (크루 ID)", defaultValue = "2")
    private Long newLeaderId;
}
