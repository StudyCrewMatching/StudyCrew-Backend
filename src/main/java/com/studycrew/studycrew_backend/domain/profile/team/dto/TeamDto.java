package com.studycrew.studycrew_backend.domain.profile.team.dto;

import com.studycrew.studycrew_backend.domain.profile.team.Team;
import com.studycrew.studycrew_backend.domain.tag.position.PositionType;
import com.studycrew.studycrew_backend.domain.tag.skill.SkillType;
import com.studycrew.studycrew_backend.domain.tag.status.TeamStatus;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeamDto {

    private Long ownerId;

    private String name;

    private String description;

    private String meetingMode;

    private Integer weeklyMeetingCount;

    private String location;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer teamSize;

    private TeamStatus teamStatus;

    private List<PositionType> positions = new ArrayList<>();

    private List<SkillType> skills = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private TeamDto(Long ownerId, String name, String description, String meetingMode,
                    Integer weeklyMeetingCount, String location, LocalDate startDate, LocalDate endDate,
                    Integer teamSize, TeamStatus teamStatus,
                    List<PositionType> positions, List<SkillType> skills) {
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
        this.meetingMode = meetingMode;
        this.weeklyMeetingCount = weeklyMeetingCount;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teamSize = teamSize;
        this.teamStatus = teamStatus;
        this.positions = positions;
        this.skills = skills;
    }

    public static TeamDto of(Team team) {
        return TeamDto.builder()
                .ownerId(team.getOwnerId())
                .name(team.getName())
                .description(team.getDescription())
                .meetingMode(String.valueOf(team.getMeetingMode()))
                .weeklyMeetingCount(team.getWeeklyMeetingCount())
                .location(team.getLocation())
                .startDate(team.getStartDate())
                .endDate(team.getEndDate())
                .teamSize(team.getTeamSize())
                .teamStatus(team.getStatus())
                .build();
    }
}
