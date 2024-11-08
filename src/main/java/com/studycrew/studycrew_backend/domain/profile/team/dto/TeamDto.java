package com.studycrew.studycrew_backend.domain.profile.team.dto;

import com.studycrew.studycrew_backend.domain.profile.team.Team;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDate;

public class TeamDto {

    private Long ownerId;

    private String name;

    private String description;

    private String meetingFormat;

    private Integer weeklyMeetingCount;

    private String location;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer teamSize;

    // TODO: 태그(enum)로 변경 예정
    private String status;

    @Builder(access = AccessLevel.PRIVATE)
    private TeamDto(Long ownerId, String name, String description, String meetingFormat, Integer weeklyMeetingCount, String location, LocalDate startDate, LocalDate endDate, Integer teamSize, String status) {
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
        this.meetingFormat = meetingFormat;
        this.weeklyMeetingCount = weeklyMeetingCount;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teamSize = teamSize;
        this.status = status;
    }

    public static TeamDto of(Team team) {
        return TeamDto.builder()
                .ownerId(team.getOwnerId())
                .name(team.getName())
                .description(team.getDescription())
                .meetingFormat(team.getMeetingFormat())
                .weeklyMeetingCount(team.getWeeklyMeetingCount())
                .location(team.getLocation())
                .startDate(team.getStartDate())
                .endDate(team.getEndDate())
                .teamSize(team.getTeamSize())
                .status(team.getStatus())
                .build();
    }
}
