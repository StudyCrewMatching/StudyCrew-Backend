package com.studycrew.studycrew_backend.domain.profile.team.service;

import com.studycrew.studycrew_backend.domain.profile.team.Team;
import com.studycrew.studycrew_backend.domain.profile.team.repo.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamMembershipService {

    private final TeamRepository teamRepository;

    @Transactional
    public void addUserToTeam(Long teamId, Long userId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        // user findById
    }

    @Transactional
    public void removeUserFromTeam(Long teamId, Long userId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        // user findById
    }
}
