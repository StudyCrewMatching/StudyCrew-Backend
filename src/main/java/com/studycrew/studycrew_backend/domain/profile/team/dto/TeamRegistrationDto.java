package com.studycrew.studycrew_backend.domain.profile.team.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class TeamRegistrationDto {

    @NotNull(message = "사용자 아이디는 필수입니다.")
    @Min(value = 1, message = "사용자 아이디는 1 이상이어야 합니다.")
    private Long ownerId;

    @NotBlank(message = "팀명은 필수입니다.")
    private String name;

    private String description;

    @NotBlank(message = "온라인/오프라인 선택은 필수입니다.")
    private String meetingMode;

    @NotNull(message = "주간 미팅 수는 필수입니다.")
    @Min(value = 1, message = "주간 미팅은 1회 이상이어야 합니다.")
    private Integer weeklyMeetingCount;

    private String location;

    // TODO: 유효성 검사 추가 (과거 불가능)
    private LocalDate startDate;

    // TODO: 유효성 검사 추가 (startDate 이후 날짜)
    private LocalDate endDate;

    @NotNull(message = "크루 총 인원은 필수입니다.")
    @Min(value = 1, message = "인원은 1명 이상이어야 합니다.")
    private Integer teamSize;

    private List<String> positions;

    private List<String> skills;
}
