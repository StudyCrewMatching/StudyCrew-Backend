package com.studycrew.studycrew_backend.domain.profile.team.repo;

import com.studycrew.studycrew_backend.domain.profile.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByIdAndLeaderId(Long id, Long leaderId);
}
