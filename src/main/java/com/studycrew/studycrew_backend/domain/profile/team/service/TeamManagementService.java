package com.studycrew.studycrew_backend.domain.profile.team.service;

import com.studycrew.studycrew_backend.domain.profile.team.Team;
import com.studycrew.studycrew_backend.domain.profile.team.dto.TeamDto;
import com.studycrew.studycrew_backend.domain.profile.team.repo.TeamRepository;
import com.studycrew.studycrew_backend.domain.profile.team.dto.TeamRegistrationDto;
import com.studycrew.studycrew_backend.domain.profile.user.User;
import com.studycrew.studycrew_backend.domain.profile.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamManagementService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Transactional
    public TeamDto register(TeamRegistrationDto teamRegistrationDto) {
        User leader = userRepository.findById(teamRegistrationDto.getLeaderId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Team team = Team.of(teamRegistrationDto, leader);
        teamRepository.save(team);
        return TeamDto.of(team);
    }

    public TeamDto findTeamById(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        return TeamDto.of(team);
    }

    @Transactional
    public void delete(Long teamId, Long leaderId) {
        Team team = teamRepository.findByIdAndLeaderId(teamId, leaderId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        teamRepository.delete(team);
    }
}
