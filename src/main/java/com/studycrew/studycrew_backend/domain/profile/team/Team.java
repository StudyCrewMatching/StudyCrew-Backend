package com.studycrew.studycrew_backend.domain.profile.team;

import com.studycrew.studycrew_backend.domain.profile.team.dto.TeamRegistrationDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

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

    // TODO: 태그(enum)로 변경 예정
    private String meetingFormat;

    private Integer weeklyMeetingCount;

    private String location;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer teamSize;

    // TODO: 태그(enum)로 변경 예정
    private String status;

    @Builder(access = AccessLevel.PRIVATE)
    private Team(Long ownerId, String name, String description, String meetingFormat, Integer weeklyMeetingCount, String location, LocalDate startDate, LocalDate endDate, Integer teamSize, String status) {
        this.ownerId = null;
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

    public static Team of(TeamRegistrationDto teamRegistrationDto) {
        return Team.builder()
                .ownerId(teamRegistrationDto.getOwnerId())
                .name(teamRegistrationDto.getName())
                .description(teamRegistrationDto.getDescription())
                .meetingFormat(teamRegistrationDto.getMeetingFormat())
                .weeklyMeetingCount(teamRegistrationDto.getWeeklyMeetingCount())
                .location(teamRegistrationDto.getLocation())
                .startDate(teamRegistrationDto.getStartDate())
                .endDate(teamRegistrationDto.getEndDate())
                .teamSize(teamRegistrationDto.getTeamSize())
//                .status()
                .build();
    }
}
