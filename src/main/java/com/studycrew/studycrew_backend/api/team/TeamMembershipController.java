package com.studycrew.studycrew_backend.api.team;

import com.studycrew.studycrew_backend.domain.profile.team.dto.TeamLeaderChangeRequestDto;
import com.studycrew.studycrew_backend.domain.profile.team.service.TeamMembershipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Team Membership Controller", description = "팀 가입, 탈퇴 및 팀 리더 변경 API 제공")
@RestController
@RequiredArgsConstructor
@RequestMapping("/membership")
@Validated
public class TeamMembershipController {

    private final TeamMembershipService teamMembershipService;

    @Operation(summary = "팀 가입", description = "크루 인원이 모두 차지 않았다면 팀 가입이 가능합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 가입 성공", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "400", description = "크루 인원이 모두 채워졌거나, 해당 사용자가 다른 팀에 가입된 상태라면 팀 가입 불가", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @PostMapping("/register/{teamId}/{userId}")
    public ResponseEntity<?> addUserToTeam(@PathVariable @Valid @Positive(message = "팀 아이디는 1 이상이어야 합니다.") Long teamId,
                                           @PathVariable @Valid @Positive(message = "사용자 아이디는 1 이상이어야 합니다.") Long userId) {
        teamMembershipService.addMemberToTeam(teamId, userId);
        return ResponseEntity.ok().body("팀에 추가되었습니다.");
    }

    @Operation(summary = "팀 탈퇴", description = "크루이면서 팀 리더가 아니면 팀 탈퇴가 가능합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 탈퇴 성공", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "400", description = "팀 리더이거나 팀 멤버가 아니면 팀 탈퇴 불가", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @DeleteMapping("/delete/{teamId}/{userId}")
    public ResponseEntity<?> removeUserFromTeam(@PathVariable @Valid @Positive(message = "팀 아이디는 1 이상이어야 합니다.") Long teamId,
                                                @PathVariable @Valid @Positive(message = "사용자 아이디는 1 이상이어야 합니다.") Long userId) {
        teamMembershipService.removeMemberFromTeam(teamId, userId);
        return ResponseEntity.ok().body("팀을 탈퇴했습니다.");
    }

    @Operation(summary = "팀 리더 변경", description = "팀 리더만이 리더 변경 권한을 가지고 있으며, 팀 리더가 아닌 크루로 팀 리더 변경이 가능합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 리더 변경 성공", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "400", description = "팀 리더의 요청이 아니거나, 새로운 리더가 크루가 아니면 팀 리더 변경 불가", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @PutMapping("/teams/{teamId}/leader")
    public ResponseEntity<?> changeTeamLeader(@PathVariable @Valid @Positive(message = "팀 아이디는 1 이상이어야 합니다.") Long teamId,
                                              @RequestBody @Valid TeamLeaderChangeRequestDto teamLeaderChangeRequestDto) {
        teamMembershipService.changeLeader(teamId, teamLeaderChangeRequestDto);
        return ResponseEntity.ok().body("팀 리더가 변경되었습니다.");
    }
}
