package com.studycrew.studycrew_backend.domain.profile.team.service;

import com.studycrew.studycrew_backend.domain.profile.team.Team;
import com.studycrew.studycrew_backend.domain.profile.team.dto.TeamDto;
import com.studycrew.studycrew_backend.domain.profile.team.repo.TeamRepository;
import com.studycrew.studycrew_backend.domain.profile.team.dto.TeamRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamManagementService {

    private final TeamRepository teamRepository;

    public TeamDto register(TeamRegistrationDto request) {
        Team team = Team.of(request);
        teamRepository.save(team);
        return TeamDto.of(team);
    }

    public void delete(Long ownerId, Long teamId) {
//        user findById

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

//        teamRepository.deleteByOwnerIdAndTeamId
    }
}
