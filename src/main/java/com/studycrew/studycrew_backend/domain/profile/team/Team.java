package com.studycrew.studycrew_backend.domain.profile.team;

import com.studycrew.studycrew_backend.domain.profile.team.dto.TeamRegistrationDto;
import com.studycrew.studycrew_backend.domain.tag.meetingmode.MeetingModeType;
import com.studycrew.studycrew_backend.domain.tag.position.PositionType;
import com.studycrew.studycrew_backend.domain.tag.skill.SkillType;
import com.studycrew.studycrew_backend.domain.tag.status.TeamStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO user 타입으로 변경 예정
    private Long ownerId;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private MeetingModeType meetingMode;

    private Integer weeklyMeetingCount;

    private String location;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer teamSize;

    @Enumerated(EnumType.STRING)
    private TeamStatus status;

    @ElementCollection(targetClass = PositionType.class)
    @Enumerated(EnumType.STRING)
    private List<PositionType> positions = new ArrayList<>();

    @ElementCollection(targetClass = SkillType.class)
    @Enumerated(EnumType.STRING)
    private List<SkillType> skills = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private Team(Long ownerId, String name, String description, MeetingModeType meetingMode,
                 Integer weeklyMeetingCount, String location, LocalDate startDate, LocalDate endDate,
                 Integer teamSize, TeamStatus status,
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
        this.status = status;
        this.positions = positions;
        this.skills = skills;
    }

    public static Team of(TeamRegistrationDto teamRegistrationDto) {
        return Team.builder()
                .ownerId(teamRegistrationDto.getOwnerId())
                .name(teamRegistrationDto.getName())
                .description(teamRegistrationDto.getDescription())
                .meetingMode(MeetingModeType.valueOf(teamRegistrationDto.getMeetingMode()))
                .weeklyMeetingCount(teamRegistrationDto.getWeeklyMeetingCount())
                .location(teamRegistrationDto.getLocation())
                .startDate(teamRegistrationDto.getStartDate())
                .endDate(teamRegistrationDto.getEndDate())
                .teamSize(teamRegistrationDto.getTeamSize())
                .status(TeamStatus.OPEN)
                .positions(convertPositionType(teamRegistrationDto.getPositions()))
                .skills(convertSkillType(teamRegistrationDto.getSkills()))
                .build();
    }

    private static List<PositionType> convertPositionType(List<String> positions) {
        return positions.stream()
                .map(PositionType::valueOf)
                .toList();
    }

    private static List<SkillType> convertSkillType(List<String> skills) {
        return skills.stream()
                .map(SkillType::valueOf)
                .toList();
    }
}
