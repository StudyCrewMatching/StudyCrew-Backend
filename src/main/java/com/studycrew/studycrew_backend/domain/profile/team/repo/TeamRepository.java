package com.studycrew.studycrew_backend.domain.profile.team.repo;

import com.studycrew.studycrew_backend.domain.profile.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select t from Team t where t.id=:id and t.leader.id=:leaderId")
    Optional<Team> findByIdAndLeaderId(@Param("id") Long id, @Param("leaderId") Long leaderId);
}
