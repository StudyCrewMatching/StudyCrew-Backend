package com.studycrew.studycrew_backend.domain.profile.team.dto;

import com.studycrew.studycrew_backend.domain.tag.position.PositionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class TeamRegistrationDto {

    @NotNull(message = "팀 리더 아이디는 필수입니다.")
    @Min(value = 1, message = "팀 리더 아이디는 1 이상이어야 합니다.")
    @Schema(description = "팀 리더 ID", defaultValue = "1")
    private Long leaderId;

    @NotBlank(message = "팀명은 필수입니다.")
    @Schema(description = "팀명", defaultValue = "크루 매칭 프로젝트 팀")
    private String name;

    @Schema(description = "팀 설명")
    private String description;

    @NotBlank(message = "온라인/오프라인 선택은 필수입니다.")
    @Schema(description = "미팅 모드 (온/오프라인)", defaultValue = "ONLINE")
    private String meetingMode;

    @NotNull(message = "주간 미팅 수는 필수입니다.")
    @Min(value = 1, message = "주간 미팅은 1회 이상이어야 합니다.")
    @Schema(description = "주간 미팅 횟수", defaultValue = "2")
    private Integer weeklyMeetingCount;

    @Schema(description = "지역 (오프라인)")
    private String location;

    // TODO: 유효성 검사 추가 (과거 불가능)
    @Schema(description = "팀 모집 시작일")
    private LocalDate startDate;

    // TODO: 유효성 검사 추가 (startDate 이후 날짜)
    @Schema(description = "팀 모집 마감일")
    private LocalDate endDate;

    @NotNull(message = "크루 총 인원은 필수입니다.")
    @Min(value = 1, message = "인원은 1명 이상이어야 합니다.")
    @Schema(description = "크루 총 인원", defaultValue = "3")
    private Integer teamSize;

    @Schema(description = "팀에서 원하는 포지션 (BE, FE...)",
            example = "[\"BACKEND\", \"FRONTEND\", \"IOS\"]",
            type = "array")
    private List<String> positions;

    @Schema(description = "팀에서 원하는 기술 스택 (Java, Kotlin, JavaScript...)",
            example = "[\"JAVA\", \"REACT\", \"DOCKER\"]",
            type = "array")
    private List<String> skills;
}
