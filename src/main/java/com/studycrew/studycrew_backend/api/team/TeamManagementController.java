package com.studycrew.studycrew_backend.api.team;

import com.studycrew.studycrew_backend.domain.profile.team.dto.TeamDto;
import com.studycrew.studycrew_backend.domain.profile.team.dto.TeamRegistrationDto;
import com.studycrew.studycrew_backend.domain.profile.team.service.TeamManagementService;
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

@Tag(name = "Team Management Controller", description = "팀 생성, 삭제 및 조회 API 제공")
@RestController
@RequiredArgsConstructor
@RequestMapping("/management")
@Validated
public class TeamManagementController {

    private final TeamManagementService teamManagementService;

    @Operation(summary = "팀 생성", description = "팀을 생성한 사용자가 팀 리더로 설정됩니다. (변경 가능)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 생성 성공", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "400", description = "알 수 없는 사용자이거나, 요청한 사용자가 이미 다른 팀 리더이면 팀 생성 불가", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid TeamRegistrationDto teamRegistrationDto) {
        TeamDto teamDto = teamManagementService.register(teamRegistrationDto);
        return ResponseEntity.ok(teamDto);
    }

    @Operation(summary = "팀 조회", description = "팀 ID로 팀 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 조회 성공", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 팀", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @GetMapping("/teams/{teamId}")
    public ResponseEntity<?> findTeamById(@PathVariable @Positive(message = "팀 아이디는 1 이상이어야 합니다.") Long teamId) {
        TeamDto teamDto = teamManagementService.findTeamById(teamId);
        return ResponseEntity.ok(teamDto);
    }

    @Operation(summary = "팀 삭제", description = "팀 리더만이 팀을 삭제할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팀 삭제 성공", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 팀이거나, 삭제를 요청한 사용자가 팀 리더가 아니면 팀 삭제 불가", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @DeleteMapping("/delete/{leaderId}/{teamId}")
    public ResponseEntity<?> delete(@PathVariable @Valid @Positive(message = "사용자 아이디는 1 이상이어야 합니다.") Long leaderId,
                                    @PathVariable @Valid @Positive(message = "팀 아이디는 1 이상이어야 합니다.") Long teamId) {
        teamManagementService.delete(leaderId, teamId);
        return ResponseEntity.ok().body("팀이 삭제되었습니다.");
    }
}
