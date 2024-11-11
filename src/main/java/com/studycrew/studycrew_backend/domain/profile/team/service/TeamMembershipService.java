package com.studycrew.studycrew_backend.domain.profile.team.service;

import com.studycrew.studycrew_backend.domain.profile.team.Team;
import com.studycrew.studycrew_backend.domain.profile.team.dto.TeamLeaderChangeRequestDto;
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
        member.assignTeam(team);
    }

    @Transactional
    public void removeMemberFromTeam(Long teamId, Long memberId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        User member = userRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        team.removeMember(member);
        member.assignTeam(null);
    }

    // TODO: 팀 변경 권한은 리더만 가능 -> security 적용 여부 확인
    @Transactional
    public void changeLeader(Long teamId, TeamLeaderChangeRequestDto teamLeaderChangeRequestDto) {
        Long nowLeaderId = teamLeaderChangeRequestDto.getNowLeaderId();
        Long newLeaderId = teamLeaderChangeRequestDto.getNewLeaderId();

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        if(!team.getLeader().getId().equals(nowLeaderId)) {
            throw new IllegalArgumentException("Only the leader has the authority to change the leader");
        }

        User newLeader = userRepository.findById(newLeaderId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        team.changeLeader(newLeader);
    }
}
