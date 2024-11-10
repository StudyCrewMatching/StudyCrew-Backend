package com.studycrew.studycrew_backend.domain.profile.team;

import com.studycrew.studycrew_backend.domain.profile.team.dto.TeamRegistrationDto;
import com.studycrew.studycrew_backend.domain.profile.user.User;
import com.studycrew.studycrew_backend.domain.tag.meetingmode.MeetingModeType;
import com.studycrew.studycrew_backend.domain.tag.position.PositionType;
import com.studycrew.studycrew_backend.domain.tag.skill.SkillType;
import com.studycrew.studycrew_backend.domain.tag.status.TeamStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

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
    @OneToOne
    @JoinColumn(name = "leader_id")
    private User leader;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> members = new ArrayList<>();

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
    private Team(User leader, List<User> members, String name, String description, MeetingModeType meetingMode,
                 Integer weeklyMeetingCount, String location, LocalDate startDate, LocalDate endDate,
                 Integer teamSize, TeamStatus status, List<PositionType> positions, List<SkillType> skills) {
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
        this.status = status;
        this.positions = positions;
        this.skills = skills;
    }

    public static Team of(TeamRegistrationDto teamRegistrationDto, User leader) {
        return Team.builder()
                .leader(leader)
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

    @Transactional
    public void addMember(User user) {
        if(members.contains(user)) {
            throw new IllegalArgumentException("Already a member of this team");
        }
        if(members.size() + 1 == teamSize) {
            throw new IllegalArgumentException("The team is full");
        }
        members.add(user);
    }

    public void removeMember(User user) {
        if(!members.contains(user)) {
            throw new IllegalArgumentException("Not a member of this team");
        }
        members.remove(user);
    }
}
