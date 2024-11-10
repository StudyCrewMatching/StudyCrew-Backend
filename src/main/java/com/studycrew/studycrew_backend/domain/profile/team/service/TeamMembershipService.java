package com.studycrew.studycrew_backend.domain.profile.team.service;

import com.studycrew.studycrew_backend.domain.profile.team.Team;
import com.studycrew.studycrew_backend.domain.profile.team.repo.TeamRepository;
import com.studycrew.studycrew_backend.domain.profile.user.User;
import com.studycrew.studycrew_backend.domain.profile.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamMembershipService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addMemberToTeam(Long teamId, Long memberId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        User member = userRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        team.addMember(member);
        // member.setTeam(team);
    }

    @Transactional
    public void removeMemberFromTeam(Long teamId, Long memberId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        User member = userRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        team.removeMember(member);
        // member.setTeam(null);
    }
}
