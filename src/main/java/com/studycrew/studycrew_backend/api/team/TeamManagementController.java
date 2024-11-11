package com.studycrew.studycrew_backend.api.team;

import com.studycrew.studycrew_backend.domain.profile.team.dto.TeamDto;
import com.studycrew.studycrew_backend.domain.profile.team.dto.TeamRegistrationDto;
import com.studycrew.studycrew_backend.domain.profile.team.service.TeamManagementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/management")
@Validated
public class TeamManagementController {

    private final TeamManagementService teamManagementService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid TeamRegistrationDto teamRegistrationDto) {
        TeamDto teamDto = teamManagementService.register(teamRegistrationDto);
        return ResponseEntity.ok(teamDto);
    }

    @GetMapping("/teams/{teamId}")
    public ResponseEntity<?> findTeamById(@PathVariable @Positive(message = "팀 아이디는 1 이상이어야 합니다.") Long teamId) {
        TeamDto teamDto = teamManagementService.findTeamById(teamId);
        return ResponseEntity.ok(teamDto);
    }

    @PostMapping("/delete/{ownerId}/{teamId}")
    public ResponseEntity<?> delete(@PathVariable @Valid @Positive(message = "사용자 아이디는 1 이상이어야 합니다.") Long ownerId,
                                    @PathVariable @Valid @Positive(message = "팀 아이디는 1 이상이어야 합니다.") Long teamId) {
        teamManagementService.delete(ownerId, teamId);
        return ResponseEntity.ok().body("팀이 삭제되었습니다.");
    }
}
