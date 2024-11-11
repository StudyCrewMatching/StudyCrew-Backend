package com.studycrew.studycrew_backend.domain.profile.team.dto;

import com.studycrew.studycrew_backend.domain.profile.team.Team;
import com.studycrew.studycrew_backend.domain.profile.user.User;
import com.studycrew.studycrew_backend.domain.profile.user.dto.UserSimpleProfileDto;
import com.studycrew.studycrew_backend.domain.tag.position.PositionType;
import com.studycrew.studycrew_backend.domain.tag.skill.SkillType;
import com.studycrew.studycrew_backend.domain.tag.status.TeamStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class TeamDto {

    private UserSimpleProfileDto leader;

    private List<UserSimpleProfileDto> members;

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
    private TeamDto(UserSimpleProfileDto leader, List<UserSimpleProfileDto> members,
                    String name, String description, String meetingMode,
                    Integer weeklyMeetingCount, String location, LocalDate startDate, LocalDate endDate,
                    Integer teamSize, TeamStatus teamStatus,
                    List<PositionType> positions, List<SkillType> skills) {
        this.leader = leader;
        this.members = members;
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
                .leader(UserSimpleProfileDto.of(team.getLeader()))
                .members(convertUserSimpleProfileDtos(team.getMembers()))
                .name(team.getName())
                .description(team.getDescription())
                .meetingMode(String.valueOf(team.getMeetingMode()))
                .weeklyMeetingCount(team.getWeeklyMeetingCount())
                .location(team.getLocation())
                .startDate(team.getStartDate())
                .endDate(team.getEndDate())
                .teamSize(team.getTeamSize())
                .teamStatus(team.getStatus())
                .positions(team.getPositions())
                .skills(team.getSkills())
                .build();
    }

    private static List<UserSimpleProfileDto> convertUserSimpleProfileDtos(List<User> members) {
        return Optional.ofNullable(members)
                .map(list -> list.stream()
                        .map(UserSimpleProfileDto::of)
                        .toList())
                .orElse(new ArrayList<>());
    }
}
