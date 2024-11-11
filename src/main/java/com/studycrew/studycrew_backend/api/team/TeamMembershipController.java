package com.studycrew.studycrew_backend.api.team;

import com.studycrew.studycrew_backend.domain.profile.team.dto.TeamLeaderChangeRequestDto;
import com.studycrew.studycrew_backend.domain.profile.team.service.TeamMembershipService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/membership")
@Validated
public class TeamMembershipController {

    private final TeamMembershipService teamMembershipService;

    @PostMapping("/register/{teamId}/{userId}")
    public ResponseEntity<?> addUserToTeam(@PathVariable @Valid @Positive(message = "팀 아이디는 1 이상이어야 합니다.") Long teamId,
                                           @PathVariable @Valid @Positive(message = "사용자 아이디는 1 이상이어야 합니다.") Long userId) {
        teamMembershipService.addMemberToTeam(teamId, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove/{teamId}/{userId}")
    public ResponseEntity<?> removeUserFromTeam(@PathVariable @Valid @Positive(message = "팀 아이디는 1 이상이어야 합니다.") Long teamId,
                                                @PathVariable @Valid @Positive(message = "사용자 아이디는 1 이상이어야 합니다.") Long userId) {
        teamMembershipService.removeMemberFromTeam(teamId, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/teams/{teamId}/leader")
    public ResponseEntity<?> changeTeamLeader(@PathVariable @Valid @Positive(message = "팀 아이디는 1 이상이어야 합니다.") Long teamId,
                                              @RequestBody @Valid TeamLeaderChangeRequestDto teamLeaderChangeRequestDto) {
        teamMembershipService.changeLeader(teamId, teamLeaderChangeRequestDto);
        return ResponseEntity.ok().build();
    }
}
