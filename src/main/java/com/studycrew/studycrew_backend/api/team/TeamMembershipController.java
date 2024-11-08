package com.studycrew.studycrew_backend.api.team;

import com.studycrew.studycrew_backend.domain.profile.team.service.TeamMembershipService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/membership")
public class TeamMembershipController {

    private final TeamMembershipService teamMembershipService;

    @PostMapping("/register/{teamId}/{userId}")
    public ResponseEntity<?> addUserToTeam(@PathVariable @Valid @Positive(message = "팀 아이디는 1 이상이어야 합니다.") Long teamId,
                                           @PathVariable @Valid @Positive(message = "사용자 아이디는 1 이상이어야 합니다.") Long userId) {
        teamMembershipService.addUserToTeam(teamId, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/{teamId}/{userId}")
    public ResponseEntity<?> removeUserFromTeam(@PathVariable @Valid @Positive(message = "팀 아이디는 1 이상이어야 합니다.") Long teamId,
                                                @PathVariable @Valid @Positive(message = "사용자 아이디는 1 이상이어야 합니다.") Long userId) {
        teamMembershipService.removeUserFromTeam(teamId, userId);
        return ResponseEntity.ok().build();
    }
}
